package com.gunna.bigburger.androidapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.gunna.bigburger.androidapp.presentation.ViewModelFactory;
import com.gunna.bigburger.androidapp.presentation.menu.MenuListViewModel;
import dagger.Binds;
import dagger.Module;

import dagger.multibindings.IntoMap;

@Module
public abstract class PresentationModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(MenuListViewModel.class)
    abstract ViewModel bindsSnackListViewModel(MenuListViewModel viewModel);




}
