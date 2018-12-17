package com.gunna.bigburger.androidapp.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gunna.bigburger.androidapp.app.RxJavaUtils;
import com.gunna.bigburger.androidapp.databinding.FragmentOffersBinding;
import com.gunna.bigburger.androidapp.domain.model.Offer;
import com.gunna.bigburger.androidapp.presentation.ViewState;
import com.gunna.bigburger.androidapp.presentation.ViewStateEnum;
import com.gunna.bigburger.androidapp.presentation.offers.OffersViewModel;
import com.gunna.bigburger.androidapp.ui.BaseFragment;
import com.gunna.bigburger.androidapp.ui.adapter.offers.OffersAdapter;

import java.util.List;

import static com.gunna.bigburger.androidapp.presentation.ViewStateEnum.LOADING;

public class OffersFragment extends BaseFragment {

    private FragmentOffersBinding mViewBinding;
    private OffersViewModel mViewModel;
    private OffersAdapter mOffersAdapter;
    private ViewStateEnum mCurrentState;


    public static OffersFragment newInstance() {

        Bundle args = new Bundle();

        OffersFragment fragment = new OffersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = FragmentOffersBinding.inflate(inflater, container, false);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(OffersViewModel.class);
        mViewBinding.setViewModel(mViewModel);
        setupList();
        setupSwipe();
        return mViewBinding.getRoot();
    }

    private void setupSwipe() {
        mViewBinding.swipe.setOnRefreshListener(() -> {
            mOffersAdapter.clear();
            mViewModel.getOffersList();
        });
    }

    private void setupList() {
        mOffersAdapter = new OffersAdapter();
        mViewBinding.listOffers.setAdapter(mOffersAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!RxJavaUtils.hasSubscriptions(mSubscriptions)) {
            addObserverOffersListState();
            mViewModel.getOffersList();
        }
    }

    private void addObserverOffersListState() {
        mSubscriptions.add(mViewModel
                .observeOffersListState()
                .subscribe(this::handleOffersListState, this::handleErrors)
        );
    }

    private void handleOffersListState(ViewState<List<Offer>> listViewState) {
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
        if (mViewBinding.swipe.isRefreshing())
            mViewBinding.swipe.setRefreshing(false);
    }

    private void setAdapterData(List<Offer> data) {
        mOffersAdapter.addData(data);
    }


}
