package com.soulreaverq.magic;

import android.support.v4.widget.ViewDragHelper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initDrawerLayout();
    }

    public void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    public void initDrawerLayout() {
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        try {
            Field mDragger = mDrawerLayout.getClass().getDeclaredField(
                    "mLeftDragger");
            mDragger.setAccessible(true);
            ViewDragHelper draggerObj = (ViewDragHelper) mDragger
                    .get(mDrawerLayout);
            Field mEdgeSize = draggerObj.getClass().getDeclaredField(
                    "mEdgeSize");
            mEdgeSize.setAccessible(true);
            int edge = mEdgeSize.getInt(draggerObj);
            mEdgeSize.setInt(draggerObj, edge * 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // Update the main content by replacing fragments

        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();
        switch (position) {
            default:
            case 0:
                mFragment = new MyFragment1();
                break;
            case 1:
                mFragment = new FeedFragment();
                break;
            case 2:
                mFragment = new MyFragment3();
                break;
            case 4:
                mFragment = new MyFragment4();
                break;
            case 5:
                mFragment = new MyFragment5();
                break;
        }

        mFragmentManager.beginTransaction()
                .replace(R.id.container, mFragment)
                .commit();
    }

   /* public void restoreActionBar() {

        ActionBar actionBar = getSupportActionBar();
        // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show mItems in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);

            // restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }
        */
        return super.onOptionsItemSelected(item);
    }
}
