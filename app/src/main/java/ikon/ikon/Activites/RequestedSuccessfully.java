package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ikon.ikon.Adapter.Cart_Adapter;
import ikon.ikon.Adapter.adorder_Succesfull_Adapter;
import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.GPSTracker;
import ikonNNN.ikonN.R;

public class RequestedSuccessfully extends AppCompatActivity {

    View view;
    RecyclerView recyclerView;
    adorder_Succesfull_Adapter adapter;
    ListItemCart list=new ListItemCart();
    Button btnorder;
    GPSTracker gbs;
    double total_price;
    double res = 0;
    ListItemCart listItemCar;
    TextView TextDone;
    SharedPreferences.Editor sharesss;
    ListItemCart listItemCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_successfully);
        recyclerView =findViewById(R.id.recycler_ordersuccesfull);
        sharesss=getSharedPreferences("count",MODE_PRIVATE).edit();
        TextDone=findViewById(R.id.TextDone);
        recyclerView.setHasFixedSize(true);
        listItemCart=new ListItemCart();
        adapter = new adorder_Succesfull_Adapter(list.getlist(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        TextDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent inty=new Intent(RequestedSuccessfully.this,Navigation.class);
                ListItemCart.item.clear();
                ShowProduct.liscart.clear();
                sharesss.putString("count","0");
                sharesss.commit();
                inty.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                inty.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inty);
                finish();
            }
        });
    }
}
