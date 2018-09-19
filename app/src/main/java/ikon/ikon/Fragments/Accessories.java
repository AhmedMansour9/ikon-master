package ikon.ikon.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;
import java.util.Locale;

import ikon.ikon.Adapter.Accessories_Adapter;
import ikon.ikon.CheckgbsAndNetwork;
import ikon.ikon.Model.Accessory;
import ikon.ikon.Model.AccessorysubCategory;
import ikon.ikon.PreSenter.GetAccessoriesPresenter;
import ikon.ikon.Viewes.AccessoriesView;
import ikon.ikon.Viewes.CountView;
import ikonNNN.ikonN.R;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class Accessories extends Fragment implements AccessoriesView,SwipeRefreshLayout.OnRefreshListener,CountView{


    public Accessories() {
        // Required empty public constructor
    }
     GetAccessoriesPresenter getAccessoriesP;
      View view;
    RecyclerView recyclerView;
    SharedPreferences shared;
    Accessories_Adapter adapter;
    String Lan;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    CheckgbsAndNetwork checknetwork;
    RelativeLayout RelativePhone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_accessories, container, false);
       getAccessoriesP=new GetAccessoriesPresenter(getContext(),this);
        shared=getActivity().getSharedPreferences("Language",MODE_PRIVATE);
        checknetwork=new CheckgbsAndNetwork(getApplicationContext());
        RelativePhone=view.findViewById(R.id.RelativeAccessories);
        Lan=shared.getString("Lann",null);
        Recyclview();
        SwipRefresh();


      mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
              if(checknetwork.isNetworkAvailable(getApplicationContext())){
              mSwipeRefreshLayout.setRefreshing(true);
              mSwipeRefreshLayout.setEnabled(true);
              if(Lan!=null) {
                  getAccessoriesP.GetAccessories(Lan);
              }else {

                  if(isRTL()){
                      getAccessoriesP.GetAccessories("ar");
                  }else {
                      getAccessoriesP.GetAccessories("en");
                  }
              }
              }else {
                  Snackbar.make(RelativePhone,R.string.internet,1000).show();
              }
          }
      });

        return view;
    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_Accessories);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_contain);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(checknetwork.isNetworkAvailable(getApplicationContext())){
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setEnabled(true);
                if(Lan!=null) {
                    getAccessoriesP.GetAccessories(Lan);
                }else {

                    if(isRTL()){
                        getAccessoriesP.GetAccessories("ar");
                    }else {
                        getAccessoriesP.GetAccessories("en");
                    }
                }

            }else{
                    Snackbar.make(RelativePhone,R.string.internet,1000).show();
                }
            }
        });
    }

    @Override
    public void GetAccessories(List<Accessory> list) {
        adapter = new Accessories_Adapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mSwipeRefreshLayout.setEnabled(false);

    }

    @Override
    public void ErrorAccessories() {
        mSwipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void onRefresh() {
        if(checknetwork.isNetworkAvailable(getApplicationContext())) {
            mSwipeRefreshLayout.setEnabled(true);
            if (Lan != null) {
                getAccessoriesP.GetAccessories(Lan);
            } else {

                if (isRTL()) {
                    getAccessoriesP.GetAccessories("ar");
                } else {
                    getAccessoriesP.GetAccessories("en");
                }
            }

        }else{
            Snackbar.make(RelativePhone,R.string.internet,1000).show();
        }
    }
    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }
    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

    @Override
    public void Count(String count) {}

    @Override
    public void onResume() {
        super.onResume();
//        Shoping.T_Cartshop.setText(String.valueOf(liscart.size()));
    }
}
