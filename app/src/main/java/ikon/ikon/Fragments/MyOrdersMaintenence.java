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

import ikon.ikon.Adapter.ListOrdersMaintenence_Adapter;
import ikon.ikon.Model.MaintenanceOrder;
import ikon.ikon.PreSenter.CounterPresenter;
import ikon.ikon.PreSenter.GetPhonesPresenter;
import ikon.ikon.PreSenter.ListorderPresenter;
import ikon.ikon.Viewes.ListOrderView;
import ikon.ikonN.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersMaintenence extends Fragment implements SwipeRefreshLayout.OnRefreshListener,ListOrderView {


    public MyOrdersMaintenence() {
        // Required empty public constructor
    }
    GetPhonesPresenter phons;
    RecyclerView recyclerView;
    SharedPreferences shared;
    ListOrdersMaintenence_Adapter adapter;
    String Lan;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    CounterPresenter cn;
    ListorderPresenter listorderPresenter;
    String logi;
    SharedPreferences share;
    TextView noOrders;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my_orders_maintenence, container, false);
        listorderPresenter=new ListorderPresenter(getActivity(),this);
        share =getActivity().getSharedPreferences("login", MODE_PRIVATE);
        noOrders=view.findViewById(R.id.noOrders);
        logi = share.getString("logggin", null);
        shared =getActivity().getSharedPreferences("Language", MODE_PRIVATE);
        Lan = shared.getString("Lann", null);
        Recyclview();
        SwipRefresh();
        if(logi==null){
            noOrders.setVisibility(View.VISIBLE);

        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setEnabled(true);
                if (Lan != null) {
                    listorderPresenter.GetListOrder(Lan,logi);
                } else {

                    if (isRTL()) {
                        listorderPresenter.GetListOrder("ar",logi);
                    } else {
                        listorderPresenter.GetListOrder("en",logi);
                    }
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setEnabled(true);
        if (Lan != null) {
            listorderPresenter.GetListOrder(Lan,logi);
        } else {

            if (isRTL()) {
                listorderPresenter.GetListOrder("ar",logi);
            } else {
                listorderPresenter.GetListOrder("en",logi);
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
    public void GetListOrderShopping(List<MaintenanceOrder> list) {
        if(list.isEmpty()){
            noOrders.setVisibility(View.VISIBLE);

        }
        adapter = new ListOrdersMaintenence_Adapter(list,getActivity(),"aaa");
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
    public void Errorlistorder() {
        mSwipeRefreshLayout.setEnabled(false);
    }

    public void Recyclview() {
        recyclerView =view.findViewById(R.id.recycler_Listorder);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh() {
        mSwipeRefreshLayout =view.findViewById(R.id.swipea);
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
                    listorderPresenter.GetListOrder(Lan,logi);
                } else {

                    if (isRTL()) {
                        listorderPresenter.GetListOrder("ar",logi);
                    } else {
                        listorderPresenter.GetListOrder("en",logi);
                    }
                }

            }
        });
    }
}
