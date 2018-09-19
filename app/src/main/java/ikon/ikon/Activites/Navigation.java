package ikon.ikon.Activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.util.Base64;
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
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.Fragments.GuesFragment;
import ikon.ikon.Model.Cart;
import ikon.ikon.Viewes.CountView;
import ikonNNN.ikonN.R;

public class Navigation extends AppCompatActivity
        implements CountView , NavigationView.OnNavigationItemSelectedListener {
    Fragment fr;
    private int mCurrentSelectedPosition = 0;
    NavigationView navigationView;
    private Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    SharedPreferences shared;
    SharedPreferences.Editor sharededit;
    private List<Cart> liscart=new LinkedList<>();
    public static TextView T_Cart;
    ImageView imgdots;
    SharedPreferences.Editor Shared;
    SharedPreferences.Editor shareRole;
    SharedPreferences.Editor share;
    String User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared=getSharedPreferences("Language",MODE_PRIVATE);
        sharededit=getSharedPreferences("Language",MODE_PRIVATE).edit();
        String Lan=shared.getString("Lann",null);
        if(Lan!=null) {
            Locale locale = new Locale(Lan);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        setContentView(R.layout.activity_navigation);
        imgdots=findViewById(R.id.dotss);
         toolbar = findViewById(R.id.toolbarnavigation);
        Shared=getSharedPreferences("login",MODE_PRIVATE).edit();
        shareRole=getSharedPreferences("Role",MODE_PRIVATE).edit();

        setSupportActionBar(toolbar);
//        btncart=findViewById(R.id.btncart);
//        T_Cart=findViewById(R.id.T_Cart);
        if(isRTL()){
            imgdots.setBackgroundResource(R.drawable.united);
        }else {
            imgdots.setBackgroundResource(R.drawable.saudi);
        }



        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));


         drawer = findViewById(R.id.drawer_layout);
        imgdots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRTL()){
                    imgdots.setBackgroundResource(R.drawable.saudi);
                    sharededit.putString("Lann","en");
                    sharededit.commit();

                    startActivity(new Intent(Navigation.this,Navigation.class));
                    finish();

                }else {
                    imgdots.setBackgroundResource(R.drawable.united);
                    sharededit.putString("Lann","ar");
                    sharededit.commit();

                    startActivity(new Intent(Navigation.this,Navigation.class));
                    finish();

                }

//                PopupMenu popup = new PopupMenu(Navigation.this, imgdots);
                //Inflating the Popup using xml file

//                Context wrapper = new ContextThemeWrapper(Navigation.this, R.style.Popmenu);
//                PopupMenu popup = new PopupMenu(wrapper, view);
//                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
//
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()){
//                            case R.id.english:
//                              sharededit.putString("Lann","en");
//                              sharededit.commit();
//
//                              startActivity(new Intent(Navigation.this,Navigation.class));
//                              finish();
//                            return true;
//                            case R.id.arabic:
//                                sharededit.putString("Lann","ar");
//                                sharededit.commit();
//
//                                startActivity(new Intent(Navigation.this,Navigation.class));
//                                finish();
//                                return true;
//                        }
//                        return true;
//                    }
//                });
//
//                popup.show();//showing popup menu
            }


        });


        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        User=getIntent().getStringExtra("username");
        Snackbar.make(drawer,"Welcome "+User,1500).show();


    }
    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }
    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // This is required to make the drawer toggle work
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }

    /*
     * if you have other menu items in your activity/toolbar
     * handle them here and return true
     */

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }
    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            if (fr instanceof GuesFragment) {
//                super.onBackPressed();
//            } else if (mCurrentSelectedPosition == 1) {
//                navigationView.setCheckedItem(R.id.home);
//
//                fr = new GuesFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fr).commit();
//
//            } else if (mCurrentSelectedPosition == 2) {
//                navigationView.setCheckedItem(R.id.home);
//                fr = new GuesFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fr).commit();
//
//            }
//        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void Count(String count) {
        T_Cart.setText(count);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.home:
                mCurrentSelectedPosition = 0;
                fr = new GuesFragment();
                break;
            case R.id.logout:
                mCurrentSelectedPosition = 1;

                shareRole.putString("Role",null);
                shareRole.commit();
                Shared.putString("logggin",null);
                Shared.apply();
                startActivity(new Intent(Navigation.this,MainActivity.class));
                finish();
                break;
//            case R.id.about:
//
//
//                return true;
//            case R.id.nav_setting:
//                showSettingdialog();




            default:
                mCurrentSelectedPosition = 0;

        }
        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        item.setChecked(true);


        FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContent,fr);
        transaction.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        share=getSharedPreferences("count",MODE_PRIVATE).edit();
        share.putString("count",null);
        share.commit();

        finish();
    }
}
