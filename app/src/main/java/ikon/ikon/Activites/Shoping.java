package ikon.ikon.Activites;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ikon.ikon.Fragments.Accessories;
import ikon.ikon.Fragments.Phones;
import ikon.ikon.R;

public class Shoping extends AppCompatActivity {
    public static TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping);
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

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
    }



    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());


        if (isRTL()) {
            adapter.addFragment(new Accessories(),getResources().getString(R.string.charges));
            adapter.addFragment(new Accessories(),getResources().getString(R.string.Accessories));
            adapter.addFragment(new Phones(), getResources().getString(R.string.phones));

        } else {
            // The view has LTR layout

            adapter.addFragment(new Phones(), getResources().getString(R.string.phones));
            adapter.addFragment(new Accessories(),getResources().getString(R.string.Accessories));
            adapter.addFragment(new Accessories(),getResources().getString(R.string.charges));

        }

        //phones //Accessores // Sparts

        viewPager.setCurrentItem(adapter.getCount() - 1);
        viewPager.setAdapter(adapter);
    }
    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
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

}