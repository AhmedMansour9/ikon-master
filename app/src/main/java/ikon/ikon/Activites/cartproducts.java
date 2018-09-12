package ikon.ikon.Activites;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ikon.ikon.Adapter.Cart_Adapter;
import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.GPSTracker;
import ikon.ikon.Gbs;
import ikonNNN.ikonN.R;

public class cartproducts extends AppCompatActivity {

    View view;
    RecyclerView recyclerView;
    Cart_Adapter adapter;
    ListItemCart list=new ListItemCart();
   Button btnorder;
    GPSTracker gbs;
    double total_price;
    double res = 0;

    Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartproducts);
        btnorder=findViewById(R.id.orderr);
        recyclerView =findViewById(R.id.recycler_Cart);
        recyclerView.setHasFixedSize(true);
        adapter = new Cart_Adapter(list.getlist(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        for (int i = 0; i < list.getlist().size(); i++) {

            total_price = Double.parseDouble(list.getlist().get(i).getPrice())* Double.parseDouble(list.getlist().get(i).getCount());
            res += total_price;
        }


        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inty=new Intent(cartproducts.this, ikon.ikon.Activites.Dialog.class);

                inty.putExtra("totalpric",String.valueOf(res));


                startActivity(inty);

            }
        });


    }


}
