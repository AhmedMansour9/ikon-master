package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Locale;

import ikon.ikon.Adapter.ListOrderShopping_Adapter;
import ikon.ikon.Adapter.Products_id_Adapter;
import ikon.ikon.Model.ShowOrdersyid;
import ikon.ikon.PreSenter.CounterPresenter;
import ikon.ikon.PreSenter.ListOrderShopping_Presenter;
import ikon.ikon.PreSenter.ShowOrdersByid_Presenter;
import ikon.ikon.Viewes.ListOrderShoppingView;
import ikon.ikon.Viewes.ShowProductsView;
import ikon.ikonN.R;

public class ShowProductsId extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,ShowProductsView {
    View view;
    RecyclerView recyclerView;
    SharedPreferences shared;
    Products_id_Adapter adapter;
    String Lan;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    CounterPresenter cn;
    ShowOrdersByid_Presenter listorderPresenter;
    String Id,Addresss,FirstName,price,Latitude,longetude;
   Button getlocationn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products_id);
        listorderPresenter = new ShowOrdersByid_Presenter(this, this);
        getlocationn=findViewById(R.id.getlocationn);
        shared = getSharedPreferences("Language", MODE_PRIVATE);
        Lan = shared.getString("Lann", null);
        String use=getIntent().getStringExtra("iduseer");
        if(use!=null){
            getlocationn.setVisibility(View.GONE);
        }
        GetData();
        Recyclview();
        SwipRefresh();

        getlocationn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inty=new Intent(ShowProductsId.this, OrderLocation.class);
                inty.putExtra("address",Addresss);
                inty.putExtra("firstname",FirstName);
                inty.putExtra("latitude",String.valueOf(Latitude));
                inty.putExtra("longitude",String.valueOf(longetude));
                startActivity(inty);

            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setEnabled(true);
                if (Lan != null) {
                    listorderPresenter.GetListOrder(Lan,Id);
                } else {

                    if (isRTL()) {
                        listorderPresenter.GetListOrder("ar",Id);
                    } else {
                        listorderPresenter.GetListOrder("en",Id);
                    }
                }
            }
        });

    }
    public void GetData(){
        Id=getIntent().getStringExtra("id");
        Addresss=getIntent().getStringExtra("address");
        FirstName=getIntent().getStringExtra("firstname");
        price=getIntent().getStringExtra("price");
        Latitude=getIntent().getStringExtra("latitude");
        longetude=getIntent().getStringExtra("longitude");
    }

    public void Recyclview() {
        recyclerView = findViewById(R.id.recycler_ordersshoppingid);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh() {
        mSwipeRefreshLayout = findViewById(R.id.swimmp);
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
                    listorderPresenter.GetListOrder(Lan,Id);
                } else {

                    if (isRTL()) {
                        listorderPresenter.GetListOrder("ar",Id);
                    } else {
                        listorderPresenter.GetListOrder("en",Id);
                    }
                }

            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setEnabled(true);
        if (Lan != null) {
            listorderPresenter.GetListOrder(Lan,Id);
        } else {

            if (isRTL()) {
                listorderPresenter.GetListOrder("ar",Id);
            } else {
                listorderPresenter.GetListOrder("en",Id);
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
    public void GetListOrderShopping(List<ShowOrdersyid> list) {
        adapter = new Products_id_Adapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mSwipeRefreshLayout.setEnabled(false);

    }

    @Override
    public void Errorlistorder() {
        mSwipeRefreshLayout.setEnabled(false);
    }
}