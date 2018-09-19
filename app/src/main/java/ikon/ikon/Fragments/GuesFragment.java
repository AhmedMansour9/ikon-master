package ikon.ikon.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ikon.ikon.Activites.ListOrdersShopping;
import ikon.ikon.Activites.Maintaince;
import ikon.ikon.Activites.Navigation;
import ikon.ikon.Activites.Shoping;
import ikon.ikon.Activites.ShowProduct;
import ikon.ikon.Activites.listordermaintenence;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.Count;
import ikon.ikon.PreSenter.CounterPresenter;
import ikon.ikon.Viewes.CountView;
import ikonNNN.ikonN.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuesFragment extends Fragment implements Count {
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private int page = 0;
    private Handler handler;
    private int delay = 2000; //milliseconds
    ImageView main,shoping;
    public static TextView T_Cart;
    CounterPresenter cn;
    View view;
    SharedPreferences shareRole;
    String role;
    SharedPreferences.Editor share;
    private List<Cart> filteredList=new ArrayList<>();
    public GuesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_gues, container, false);
        handler = new Handler();
        shareRole=getActivity().getSharedPreferences("Role",MODE_PRIVATE);
        role=shareRole.getString("Role",null);
        cn=new CounterPresenter(getContext(),this);
       GoTo_Maintaince();
       GoTo_Shooping();


        viewPager =view.findViewById(R.id.view_pager);
        dotsLayout =view.findViewById(R.id.layoutDots);
        layouts = new int[]{
                R.layout.sliderfour,
                R.layout.sliderthree
        };

        addBottomDots(0);
        Viewpadger();



        return view;
    }
    public void GoTo_Maintaince(){
        main=view.findViewById(R.id.main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(role!=null){
                    startActivity(new Intent(getActivity(),listordermaintenence.class));
                }else {
                    startActivity(new Intent(getActivity(),Maintaince.class));
                }

            }
        });
    }
    public void GoTo_Shooping(){
        shoping=view.findViewById(R.id.shoping);
        shoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(role!=null){
                    startActivity(new Intent(getActivity(),ListOrdersShopping.class));
                }else {
                    startActivity(new Intent(getActivity(),Shoping.class));
                }

            }
        });
    }

    private void Viewpadger() {
        //	viewpager change listener
        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
                page = position;

                // changing the next button text 'NEXT' / 'GOT IT'
//                if (position == layouts.length - 1) {
//                    // last page. make button text to GOT IT
//
//                } else {
//                    // still pages are left
//                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        };
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

    }

    private Runnable runnable = new Runnable() {
        public void run() {
            if (myViewPagerAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };
    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);

    }

    @Override
    public void count(String con) {

//        Shoping.T_Cartshop.setText(con);

    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        share=getActivity().getSharedPreferences("count",MODE_PRIVATE).edit();
        share.putString("count","");
        share.commit();

    }

}


