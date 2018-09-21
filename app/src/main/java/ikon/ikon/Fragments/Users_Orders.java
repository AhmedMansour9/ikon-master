package ikon.ikon.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import ikon.ikon.Adapter.AdapterMyOrders;
import ikon.ikon.Adapter.ListOrderShopping_Adapter;
import ikon.ikon.Model.MyorderShoping;
import ikon.ikon.Model.MyorderShopingResponse;
import ikon.ikon.Model.Myordershop;
import ikon.ikon.PreSenter.CounterPresenter;
import ikon.ikon.PreSenter.ListOrderShopping_Presenter;
import ikon.ikon.PreSenter.MyOrderShoppingPresenter;
import ikon.ikon.Viewes.MyOrderShopingView;
import ikonNNN.ikonN.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Users_Orders extends Fragment implements SwipeRefreshLayout.OnRefreshListener,MyOrderShopingView  {


    public Users_Orders() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    SharedPreferences shared;
    AdapterMyOrders adapter;
    String Lan;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    CounterPresenter cn;
    MyOrderShoppingPresenter listorderPresenter;
    View view;
    String logi;
    SharedPreferences share;
    TextView noOrderss;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_users__orders, container, false);
        listorderPresenter = new MyOrderShoppingPresenter(getActivity(), this);
        share =getActivity().getSharedPreferences("login", MODE_PRIVATE);
         logi = share.getString("logggin", null);
        noOrderss=view.findViewById(R.id.noOrderss);
        shared = getActivity().getSharedPreferences("Language", MODE_PRIVATE);
        Lan = shared.getString("Lann", null);
        Recyclview();
        SwipRefresh();
        if(logi==null){
            noOrderss.setVisibility(View.VISIBLE);

        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setEnabled(true);
                if (Lan != null) {
                    listorderPresenter.GetListOrderShoping(Lan,logi);
                } else {

                    if (isRTL()) {
                        listorderPresenter.GetListOrderShoping("ar",logi);
                    } else {
                        listorderPresenter.GetListOrderShoping("en",logi);
                    }
                }
            }
        });


        return view;
    }

    public void Recyclview() {
        recyclerView = view.findViewById(R.id.recycler_myorders);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh() {
        mSwipeRefreshLayout=view.findViewById(R.id.swis);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setEnabled(true);
                if (Lan != null) {
                    listorderPresenter.GetListOrderShoping(Lan,logi);
                } else {

                    if (isRTL()) {
                        listorderPresenter.GetListOrderShoping("ar",logi);
                    } else {
                        listorderPresenter.GetListOrderShoping("en",logi);
                    }
                }

            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setEnabled(true);
        if (Lan != null) {
            listorderPresenter.GetListOrderShoping(Lan,logi);
        } else {

            if (isRTL()) {
                listorderPresenter.GetListOrderShoping("ar",logi);
            } else {
                listorderPresenter.GetListOrderShoping("en",logi);
            }
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
    public void OrderShopping(List<Myordershop> order) {
        if(order.isEmpty()){
            noOrderss.setVisibility(View.VISIBLE);

        }
        adapter = new AdapterMyOrders(order,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mSwipeRefreshLayout.setEnabled(false);

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setEnabled(false);
    }
}