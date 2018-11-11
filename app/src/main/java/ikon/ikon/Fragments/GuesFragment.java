package ikon.ikon.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import ikon.ikon.Activites.ListOrdersShopping;
import ikon.ikon.Activites.Maintaince;
import ikon.ikon.Activites.Navigation;
import ikon.ikon.Activites.Shoping;
import ikon.ikon.Activites.ShowProduct;
import ikon.ikon.Activites.listordermaintenence;
import ikon.ikon.Adapter.Banner_Adapter;
import ikon.ikon.Adapter.Sparts_Adapter;
import ikon.ikon.Model.Banner;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.Count;
import ikon.ikon.PreSenter.BannerPresenter;
import ikon.ikon.PreSenter.CounterPresenter;
import ikon.ikon.Viewes.BannerView;
import ikon.ikon.Viewes.CountView;
import ikon.ikonN.R;


import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuesFragment extends Fragment implements BannerView {
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
    View view;
    SharedPreferences shareRole;
    String role;
    SharedPreferences.Editor share;
    private List<Cart> filteredList=new ArrayList<>();
    BannerPresenter baner;
    List<Banner> bannr;
    Banner_Adapter banerAdapter;
    RecyclerView recyclerView;
    List<Banner> bnnr=new ArrayList<>();
    int position;
    Boolean end;
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
        Recyclview();
        role=shareRole.getString("Role",null);
        bannr=new ArrayList<>();
        baner=new BannerPresenter(getActivity(),this);
        if(isRTL()){
            baner.GetBanner("ar");
        }else {
            baner.GetBanner("en");
        }

        GoTo_Maintaince();
        GoTo_Shooping();


        viewPager =view.findViewById(R.id.view_pager);
//        dotsLayout =view.findViewById(R.id.layoutDots);
//        layouts = new int[]{
//                R.layout.sliderfour,
//                R.layout.sliderthree
//        };

//        addBottomDots(0);

//        Viewpadger();


        return view;
    }
    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }
    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
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
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_banner);
        recyclerView.setHasFixedSize(true);
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
//                addBottomDots(position);
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
    public void getBanner(List<Banner> banners) {
        bannr=banners;
        banerAdapter = new Banner_Adapter(banners,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
//
//        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(banerAdapter);
        banerAdapter.notifyDataSetChanged();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new GuesFragment.AutoScrollTask(), 1000, 2000);

    }
    private class AutoScrollTask extends TimerTask {
        @Override
        public void run() {
            if(position == bannr.size() -1){
                end = true;
            } else if (position == 0) {
                end = false;
            }
            if(!end){
                position++;
            } else {
                position--;
            }
            recyclerView.smoothScrollToPosition(position);
        }
    }

    @Override
    public void Errorbaner() {

    }


    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            View view = layoutInflater.inflate(layouts[position], container, false);
//            container.addView(view);

            View itemView = layoutInflater.inflate(R.layout.banner, container, false);

            ImageView imageView = itemView.findViewById(R.id.viewPagerItem_image1);
//            String imagee="http://ikongo.com/site/public/images/mobile_banner/"+bannr.get(position).getImage();
//            imageView.setTag(imagee);


            Picasso.with(getActivity())
                    .load("http://ikongo.com/site/public/images/mobile_banner/7566641914197734949_1537717070.jpeg")
                    .into(imageView);

            container.addView(itemView);
            myViewPagerAdapter.notifyDataSetChanged();
            return view;
        }

        @Override
        public int getCount() {
            if(bannr != null){
                return bannr.size();
            }
            return 0;
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
//        handler.postDelayed(runnable, delay);
    }
    @Override
    public void onPause() {
        super.onPause();
//        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        share=getActivity().getSharedPreferences("count",MODE_PRIVATE).edit();
        share.putString("count","");
        share.commit();

    }

}


