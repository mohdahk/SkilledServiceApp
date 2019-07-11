package com.servicenow.skilledservice.view.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;
import com.servicenow.skilledservice.utils.AppConstants;
import com.servicenow.skilledservice.view.Fragments.BaseFragment;
import com.servicenow.skilledservice.view.Fragments.SearchWorkersFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import skilledservice.servicenow.com.R;

public class ActivityLaunch extends BaseActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar mToolBar;
    private BaseFragment mLaunchedFragment;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch);
        initViews();
    }

    private void initViews() {

        navigationView = (NavigationView) findViewById(R.id.navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(new NavigationItemClickListener());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_background);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.openDrawer,
                R.string.closeDrawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        drawerLayout.addDrawerListener(mActionBarDrawerToggle);

        drawerLayout.openDrawer(Gravity.LEFT);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        LaunchSendRequestsView();
    }



    private BaseFragment showFragment(BaseFragment fragment) {

        if (!isFinishing()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, fragment, fragment.getClass().getCanonicalName());
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(AppConstants.KEY_SEARCHFRAG);
            transaction.commit();
        }
        return fragment;
    }
    private void LaunchSendRequestsView(){
        mLaunchedFragment = SearchWorkersFragment.newInstance();
        showFragment(mLaunchedFragment);
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    class NavigationItemClickListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.action_sendrequests:
                    LaunchSendRequestsView();
                    break;
                case R.id.action_receivedrequest:
                    break;
            }
            return  false;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(mActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
        } else {
            finish();
        }
    }
}
