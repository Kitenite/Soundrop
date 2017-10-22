package com.yehyunryu.android.soundrop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.yehyunryu.android.soundrop.BuildConfig;
import com.yehyunryu.android.soundrop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    @BindView(R.id.main_nav_view) NavigationView mNavigationView;
    @BindView(R.id.main_drawer_layout) DrawerLayout mDrawerLayout;

    private View mNavHeader;
    private ImageView mHeaderBackgroundIV;
    private ImageView mProfileIV;
    private TextView mUserNameTV;
    private TextView mUserInfoTV;
    private Toolbar mToolbar;

    //index to identify current menu item
    public static int sNavItemIndex = 0;

    //tags used to attach the fragments
    private static final String TAG_EXPLORE = "explore";
    private static final String TAG_DISCOVER = "discover";
    private static final String TAG_LIBRARY = "library";
    public static String sCurrentTag = TAG_EXPLORE;

    //toolbar titles for selected nav menu item
    private String[] mActivityTitles;

    //flat to load explore fragment when user presses back button
    private boolean mShouldLoadExploreOnBackPress = true;
    private Handler mHandler;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //plant debug logging using timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        //bind views using butterknife
        ButterKnife.bind(this);

        //handles runnable objects and process them in a separate thread
        mHandler = new Handler();

        //intialize and bind views for header
        mToolbar = findViewById(R.id.main_toolbar);
        mNavHeader = mNavigationView.getHeaderView(0);
        mUserNameTV = (TextView) mNavHeader.findViewById(R.id.header_user_name);
        mUserInfoTV = (TextView) mNavHeader.findViewById(R.id.header_user_info);
        mHeaderBackgroundIV = (ImageView) mNavHeader.findViewById(R.id.header_image_background);
        mProfileIV = (ImageView) mNavHeader.findViewById(R.id.header_user_profile);

        //load toolbar titles from string resources
        mActivityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        //load profile image, user name, user info into header
        loadNavHeader();

        //initialize navigation menu
        setUpNavigationView();

        //use default values if first time opening
        if(savedInstanceState == null) {
            sNavItemIndex = 0;
            sCurrentTag = TAG_EXPLORE;
            loadFragment();
        }
    }

    //set profile picture, user name, user info into header
    private void loadNavHeader() {
        mUserNameTV.setText("Soundrop");
        mUserInfoTV.setText("Version 1.0");
    }

    //returns respected fragment that was selected by user from navigation menu
    private void loadFragment() {
        //check appropriate nav menu item
        mNavigationView.getMenu().getItem(sNavItemIndex).setChecked(true);

        //set toolbar title
        mToolbar.setTitle(mActivityTitles[sNavItemIndex]);

        //if selected menu is the current fragment, simply close the drawer
        if(getSupportFragmentManager().findFragmentByTag(sCurrentTag) != null) {
            mDrawerLayout.closeDrawers();
            return;
        }

        //transition from current fragment to selected fragment
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.main_frame, fragment, sCurrentTag);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        //add to pending runnable if not null
        if(mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //close drawer
        mDrawerLayout.closeDrawers();

        //refresh toolbar menu
        invalidateOptionsMenu();
    }

    //returns selected fragment
    private Fragment getFragment() {
        switch(sNavItemIndex) {
            case 0:
                return new ExploreFragment();
            case 1:
                return new DiscoverFragment();
            case 2:
                return new LibraryFragment();
            default:
                return new ExploreFragment();
        }
    }

    //initialize navigation menu
    private void setUpNavigationView() {
        //handles navigation item selection and changes index and tag to match selected menu
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_explore:
                        sNavItemIndex = 0;
                        sCurrentTag = TAG_EXPLORE;
                        break;
                    case R.id.nav_discover:
                        sNavItemIndex = 1;
                        sCurrentTag = TAG_DISCOVER;
                        break;
                    case R.id.nav_library:
                        sNavItemIndex = 2;
                        sCurrentTag = TAG_LIBRARY;
                        break;
                    case R.id.nav_settings:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_about_us:
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_logout:
                        //TODO: Logout
                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        return true;
                    default:
                        sNavItemIndex = 0;
                }

                //make selected item checked
                item.setChecked(true);

                //load selected fragment
                loadFragment();

                return true;
            }
        });

        //handles closing and opening of drawer
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        //set drawer toggle to drawer
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        //necessary for hamburger icon to show
        actionBarDrawerToggle.syncState();

        //set logout menu to red color
        MenuItem menuItem = mNavigationView.getMenu().findItem(R.id.nav_logout);
        SpannableString logoutTitle = new SpannableString(menuItem.getTitle());
        logoutTitle.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorAccent)), 0, logoutTitle.length(), 0);
        menuItem.setTitle(logoutTitle);
    }

    @Override
    public void onBackPressed() {
        //closes drawer if open
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }

        //displays explore fragment if in another fragment
        if(mShouldLoadExploreOnBackPress) {
            if(sNavItemIndex != 0) {
                sNavItemIndex = 0;
                sCurrentTag = TAG_EXPLORE;
                loadFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //initialize map attributes
        mMap = googleMap;;
    }
}