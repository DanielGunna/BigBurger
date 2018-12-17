package com.gunna.bigburger.androidapp;


import com.gunna.bigburger.androidapp.data.NoInternetException;
import com.gunna.bigburger.androidapp.data.remote.factory.SnackResponseFactory;

import com.gunna.bigburger.androidapp.data.remote.network.NetworkStatus;
import com.gunna.bigburger.androidapp.data.remote.repository.MenuListRepository;
import com.gunna.bigburger.androidapp.domain.interactor.MenuListInteractor;
import com.gunna.bigburger.androidapp.domain.interactor.MenuListInteractorImpl;

import io.reactivex.Flowable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class MenuListInteractorTest {

    @Mock
    MenuListRepository mRepository;

    @Mock
    NetworkStatus mNetWorkStatus;

    MenuListInteractor mInteractor;

    @Before
    public void setupTest() {
        MockitoAnnotations.initMocks(this);
        mInteractor = new MenuListInteractorImpl(mRepository);
    }

    @Test
    public void whenGetListSnacksIsEmpty() {
        when(mRepository.getSnacksList())
                .thenReturn(Flowable.just(new ArrayList<>()));
        mInteractor.getSnacks()
                .test()
                .assertEmpty();
    }


    @Test
    public void whenGetListSnackAndIsOffline() {
        when(mNetWorkStatus.isOnline()).thenReturn(Flowable.just(false));
        when(mRepository.getSnacksList()).thenReturn(Flowable.just(SnackResponseFactory.getSnacks()));

    }


}
