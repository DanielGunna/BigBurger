package com.gunna.bigburger.androidapp.ui;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.gunna.bigburger.androidapp.R;
import com.gunna.bigburger.androidapp.databinding.ActivitySnackListBinding;
import com.gunna.bigburger.androidapp.presentation.snacks.SnackListViewModel;

public class SnackListActivity extends BaseActivity {

    private SnackListViewModel mViewModel;
    private ActivitySnackListBinding mViewBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SnackListViewModel.class);
        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_snack_list);
    }
}
