package ikon.ikon.Activites;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.Locale;

import ikon.ikon.Adapter.Accessories_Adapter;
import ikon.ikon.Adapter.SubCaetgory_Adapter;
import ikon.ikon.Model.AccessorysubCategory;
import ikon.ikon.PreSenter.AccessorySubCategories;
import ikon.ikon.PreSenter.GetAccessoriesPresenter;
import ikon.ikon.Viewes.SubCaetgories;
import ikonNNN.ikonN.R;

import static com.facebook.FacebookSdk.getApplicationContext;
import static java.security.AccessController.getContext;

public class Accessoriescategoryid extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,SubCaetgories {

    AccessorySubCategories getAccessoriesP;
    View view;
    RecyclerView recyclerView;
    SharedPreferences shared;
    SubCaetgory_Adapter adapter;
    String Lan;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    String Categoryid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessoriescategoryid);
        getAccessoriesP=new AccessorySubCategories(this,this);
        shared=getSharedPreferences("Language",MODE_PRIVATE);
        Lan=shared.getString("Lann",null);
         Categoryid=getIntent().getStringExtra("id");
        Recyclview();
        SwipRefresh();


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setEnabled(true);
                if(Lan!=null) {
                    getAccessoriesP.GetAccessories(Lan,Categoryid);
                }else {

                    if(isRTL()){
                        getAccessoriesP.GetAccessories("ar",Categoryid);
                    }else {
                        getAccessoriesP.GetAccessories("en",Categoryid);
                    }
                }
            }
        });
    }
    public void Recyclview(){
        recyclerView =findViewById(R.id.recycler_Accessoriessupcategory);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =findViewById(R.id.swipe_contain);
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
                if(Lan!=null) {
                    getAccessoriesP.GetAccessories(Lan,Categoryid);
                }else {

                    if(isRTL()){
                        getAccessoriesP.GetAccessories("ar",Categoryid);
                    }else {
                        getAccessoriesP.GetAccessories("en",Categoryid);
                    }
                }

            }
        });
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
    public void onRefresh() {

    }

    @Override
    public void GetSubCaetgories(List<AccessorysubCategory> list) {
        adapter = new SubCaetgory_Adapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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
    public void ErrorSubCategories() {

    }
}
