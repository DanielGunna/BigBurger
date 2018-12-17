package com.gunna.bigburger.androidapp.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gunna.bigburger.androidapp.app.RxJavaUtils;
import com.gunna.bigburger.androidapp.databinding.FragmentMenuListBinding;
import com.gunna.bigburger.androidapp.domain.model.Snack;
import com.gunna.bigburger.androidapp.presentation.ViewState;
import com.gunna.bigburger.androidapp.presentation.ViewStateEnum;
import com.gunna.bigburger.androidapp.presentation.menu.MenuListViewModel;
import com.gunna.bigburger.androidapp.ui.BaseFragment;
import com.gunna.bigburger.androidapp.ui.adapter.OnSelectSnackListItemListener;
import com.gunna.bigburger.androidapp.ui.adapter.SnackListAdapter;

import java.util.List;

import static com.gunna.bigburger.androidapp.presentation.ViewStateEnum.LOADING;

public class MenuListFragment extends BaseFragment implements OnSelectSnackListItemListener {

    private FragmentMenuListBinding mViewBinding;
    private MenuListViewModel mViewModel;
    private SnackListAdapter mSnacksAdapter;
    private ViewStateEnum mCurrentState;

    public static MenuListFragment newInstance() {
        Bundle args = new Bundle();
        MenuListFragment fragment = new MenuListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = FragmentMenuListBinding.inflate(inflater, container, false);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MenuListViewModel.class);
        setupList();
        return mViewBinding.getRoot();
    }

    private void setupList() {
        mSnacksAdapter = new SnackListAdapter(this);
        mViewBinding.listSnacks.setAdapter(mSnacksAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!RxJavaUtils.hasSubscriptions(mSubscriptions)) {
            addObserverSnackListState();
            mViewModel.getSnacksList();
        }
    }

    private void addObserverSnackListState() {
        mSubscriptions.add(mViewModel
                .observeSnackListState()
                .subscribe(this::handleSnackListState, this::handleErrors)
        );
    }

    private void handleSnackListState(ViewState<List<Snack>> listViewState) {
        mCurrentState = listViewState.status;
        switch (listViewState.status) {
            case ERROR:
                handleErrors(listViewState.error);
                break;
            case SUCCESS:
                setAdapterData(listViewState.data);
                break;
            case LOADING:
                handleLoading();
                break;
        }
    }

    private void handleLoading() {
        if (mCurrentState == LOADING) {
            mViewModel.isLoading.set(false);
        } else {
            mViewModel.isLoading.set(true);
        }
    }

    private void setAdapterData(List<Snack> data) {
        mSnacksAdapter.addData(data);
    }


    @Override
    public void onSelectSnack(Snack snack) {

    }
}
