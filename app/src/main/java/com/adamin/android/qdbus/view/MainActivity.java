package com.adamin.android.qdbus.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adamin.android.qdbus.R;
import com.adamin.android.qdbus.adapter.MainPagerAdapter;
import com.adamin.android.qdbus.api.ServiceGenerator;
import com.adamin.android.qdbus.api.service.BusLineService;
import com.adamin.android.qdbus.db.DBManager;
import com.adamin.android.qdbus.domain.BusLineDomain;
import com.adamin.android.qdbus.thirdparty.avloading.AvloadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.mian_viewpager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private MainPagerAdapter mainPagerAdapter;

    ImageView img_avator;
    TextView tv_usr_name;
    TextView tv_usr_email;
    RelativeLayout rv_uname_and_email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Bmob.initialize(this,"2607ad587aca57ebfc9ffc30aa74a120");
        init();
        initListener();

    }

    private void initListener() {
              rv_uname_and_email.setOnClickListener(this);
    }


    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("路线查询");
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_polymer_white_18dp).setText("路线");
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_bus).setText("站点");
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition(),true);
                    getSupportActionBar().setTitle(tab.getPosition()==0?"路线查询":"站点查询");


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        img_avator= (ImageView)navigationView.getHeaderView(0). findViewById(R.id.nav_image_avator);
        tv_usr_name= (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_tv_name);
        tv_usr_email= (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_tv_email);
        rv_uname_and_email= (RelativeLayout)navigationView. getHeaderView(0).findViewById(R.id.nav_relative_layout);

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

       drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nav_relative_layout:
                startActivity(new Intent(MainActivity.this,Activity_login.class));
                break;
        }

    }
}
