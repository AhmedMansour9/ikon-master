package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import ikon.ikon.Adapter.Banner_Adapter;
import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.Fragments.Accessories;
import ikon.ikon.Fragments.Phones;
import ikon.ikon.Fragments.Sparts;
import ikon.ikon.Model.Banner;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.Count;
import ikon.ikon.PreSenter.BannerPresenter;
import ikon.ikon.PreSenter.CounterPresenter;
import ikon.ikon.Viewes.BannerView;
import ikon.ikon.Viewes.CountView;
import ikon.ikonN.R;

public class Shoping extends AppCompatActivity implements BannerView {
    public static TabLayout tabLayout;
    private ViewPager viewPager;
   public static TextView T_Cartshop;
    private List<Cart> liscart=new LinkedList<>();
    ImageView btncartshop;
    Banner_Adapter banerAdapter;
    RecyclerView recyclerVie;
    BannerPresenter baner;
    SharedPreferences share;
    LinearLayoutManager linearLayoutManager;
    private RecyclerView rv_autoScroll;
    final int duration = 10;
    final int pixelsToMove = 30;
    int position;
    List<Banner> banne=new ArrayList<>();
    Boolean end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping);
        viewPager = findViewById(R.id.viewpager);
        T_Cartshop=findViewById(R.id.T_Cartshop);
        btncartshop=findViewById(R.id.btncartshop);
        Recyclview();
        baner=new BannerPresenter(this,this);
        if(isRTL()){
            baner.GetBanner("ar");
        }else {
            baner.GetBanner("en");
        }

//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new AutoScrollTask(), 2000, 5000);



        ;
        share=getSharedPreferences("count",MODE_PRIVATE);
        setupViewPager(viewPager);

        String count=share.getString("count",null);
        if(count!=null){
            T_Cartshop.setText(count);
            T_Cartshop.setBackgroundResource(R.drawable.circlecart);
        }

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(
                ColorStateList.valueOf(getResources().getColor(R.color.White)));
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#fffc00"));
              tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#fffc00"));

        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));

        if(isRTL()) {
            tabLayout.getTabAt(2).select();
        }else {
            tabLayout.getTabAt(0).select();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        btncartshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Shoping.this,cartproducts.class));
               finish();
            }
        });




    }

    private class AutoScrollTask extends TimerTask {
        @Override
        public void run() {
            if(position == banne.size() -1){
                end = true;
            } else if (position == 0) {
                end = false;
            }
            if(!end){
                position++;
            } else {
                position--;
            }
            rv_autoScroll.smoothScrollToPosition(position);
        }
    }

    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Shoping.this,Navigation.class));
        finish();
    }
    public void Recyclview(){
        rv_autoScroll = findViewById(R.id.recycler_banner2);
        rv_autoScroll.setHasFixedSize(true);
    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());


        if (isRTL()) {
            adapter.addFragment(new Sparts(),getResources().getString(R.string.charges));
            adapter.addFragment(new Accessories(),getResources().getString(R.string.Accessories));
            adapter.addFragment(new Phones(), getResources().getString(R.string.phones));

        } else {
            // The view has LTR layout

            adapter.addFragment(new Phones(), getResources().getString(R.string.phones));
            adapter.addFragment(new Accessories(),getResources().getString(R.string.Accessories));
            adapter.addFragment(new Sparts(),getResources().getString(R.string.charges));

        }

        //phones //Accessores // Sparts

//        viewPager.setCurrentItem(adapter.getCount() - 1);
        viewPager.setAdapter(adapter);
    }
    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }

    @Override
    public void getBanner(List<Banner> banners) {
        banne=banners;
        banerAdapter = new Banner_Adapter(banners,this);
         linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_autoScroll.setLayoutManager(linearLayoutManager);
        rv_autoScroll.setAdapter(banerAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new AutoScrollTask(), 1000, 2000);

//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rv_autoScroll.setItemAnimator(new DefaultItemAnimator());
//        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        recyclerView.setHasFixedSize(true);
//        rv_autoScroll.setLayoutManager(linearLayoutManager);
//
//        recyclerView.setHasFixedSize(true);
//        rv_autoScroll.setAdapter(banerAdapter);

    }

    @Override
    public void Errorbaner() {

    }


    static  class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}