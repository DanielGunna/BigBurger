package com.gunna.bigburger.androidapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.gunna.bigburger.androidapp.presentation.ViewModelFactory;
import com.gunna.bigburger.androidapp.presentation.snacks.SnackListViewModel;
import dagger.Binds;
import dagger.Module;

import dagger.multibindings.IntoMap;

@Module
public abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(SnackListViewModel.class)
    abstract ViewModel bindsSnackListViewModel(SnackListViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);


}
