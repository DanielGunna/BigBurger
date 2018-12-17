package com.gunna.bigburger.androidapp.ui.activity;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.gunna.bigburger.androidapp.R;
import com.gunna.bigburger.androidapp.databinding.ActivityMainBinding;
import com.gunna.bigburger.androidapp.ui.BaseActivity;
import com.gunna.bigburger.androidapp.ui.fragments.MenuListFragment;

import static com.gunna.bigburger.androidapp.app.Constants.FRAGMENT_MENU_TAG;
import static com.gunna.bigburger.androidapp.ui.activity.MainSelectedStateEnum.MENU;
import static com.gunna.bigburger.androidapp.ui.activity.MainSelectedStateEnum.NONE;

public class MainActivity extends BaseActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {


    private ActivityMainBinding mViewBinding;
    private MainSelectedStateEnum mCurrentSelection = NONE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupToolbar(mViewBinding.toolbarBinding.toolbar, true);
        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        mViewBinding.bottomNavView.setOnNavigationItemSelectedListener(this);
    }


    private void replaceFragment(Fragment fragment, String tag) {
//        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.setCustomAnimations(getEnterAnimation(tag), getExitAnimation(tag));
            ft.replace(R.id.fragmentContainer, fragment, tag);
            ft.commit();
//        }
    }

    private int getExitAnimation(String tag) {
        return 0;
    }

    private int getEnterAnimation(String tag) {
        return 0;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_menu:
                onSelectMenu();
                break;
            case R.id.nav_cart:
                break;
            case R.id.nav_offers:
                break;
            case R.id.nav_orders:
                break;
        }
        return true;
    }

    private void onSelectMenu() {
        replaceFragment(MenuListFragment.newInstance(), FRAGMENT_MENU_TAG);
        mCurrentSelection = MENU;
    }
}
