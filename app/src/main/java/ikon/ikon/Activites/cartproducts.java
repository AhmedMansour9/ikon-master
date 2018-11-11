package ikon.ikon.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import ikon.ikon.Adapter.Cart_Adapter;
import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.GPSTracker;
import ikon.ikonN.R;


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
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(cartproducts.this,Shoping.class));
      finish();
    }

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
                Intent inty=new Intent(cartproducts.this, Dialogee.class);

                inty.putExtra("totalpric",String.valueOf(res));


                startActivity(inty);

            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
