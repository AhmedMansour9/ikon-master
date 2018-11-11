package ikon.ikon.Activites;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.Locale;

import ikon.ikon.Adapter.ListOrdersMaintenence_Adapter;
import ikon.ikon.Adapter.Phones_Adapter;
import ikon.ikon.Model.MaintenanceOrder;
import ikon.ikon.PreSenter.CounterPresenter;
import ikon.ikon.PreSenter.GetAccessoriesPresenter;
import ikon.ikon.PreSenter.GetPhonesPresenter;
import ikon.ikon.PreSenter.ListorderPresenter;
import ikon.ikon.Viewes.AccessoriesView;
import ikon.ikon.Viewes.ListOrderView;
import ikon.ikonN.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class listordermaintenence extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,ListOrderView{

    GetPhonesPresenter phons;
    View view;
    RecyclerView recyclerView;
    SharedPreferences shared;
    ListOrdersMaintenence_Adapter adapter;
    String Lan;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    CounterPresenter cn;
    ListorderPresenter listorderPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listordermaintenence);
        listorderPresenter=new ListorderPresenter(this,this);
        shared = getSharedPreferences("Language", MODE_PRIVATE);
        Lan = shared.getString("Lann", null);
        Recyclview();
        SwipRefresh();


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setEnabled(true);
                if (Lan != null) {
                    listorderPresenter.GetListOrder(Lan);
                } else {

                    if (isRTL()) {
                        listorderPresenter.GetListOrder("ar");
                    } else {
                        listorderPresenter.GetListOrder("en");
                    }
                }
            }
        });

    }

    public void Recyclview() {
        recyclerView = findViewById(R.id.recycler_Listorders);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh() {
        mSwipeRefreshLayout = findViewById(R.id.swipe);
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
                    listorderPresenter.GetListOrder(Lan);
                } else {

                    if (isRTL()) {
                        listorderPresenter.GetListOrder("ar");
                    } else {
                        listorderPresenter.GetListOrder("en");
                    }
                }

            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setEnabled(true);
        if (Lan != null) {
            listorderPresenter.GetListOrder(Lan);
        } else {

            if (isRTL()) {
                listorderPresenter.GetListOrder("ar");
            } else {
                listorderPresenter.GetListOrder("en");
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
        adapter = new ListOrdersMaintenence_Adapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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
}
