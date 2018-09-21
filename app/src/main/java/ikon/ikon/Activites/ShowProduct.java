package ikon.ikon.Activites;

import android.app.*;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.Fragments.GuesFragment;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.Count;
import ikon.ikon.PreSenter.CounterPresenter;
import ikonNNN.ikonN.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/10/2018.
 */

public class ShowProduct extends AppCompatActivity implements Count{
    String Name,Discrp,Price,photo,id,count="";
    TextView T_Name,T_Discrp,T_Price;
    ImageView Imgphone;
    public static List<Cart> liscart=new ArrayList<>();
    TextView counter;
    Button btncart;
    ImageView plus,minus;
    Count cont;
    CounterPresenter contpresen;
    String countt="1";
    String disappear;
    SharedPreferences.Editor share;
    RelativeLayout RelativeProduct;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showproduct);
        Imgphone=findViewById(R.id.Image_Phone);
        RelativeProduct=findViewById(R.id.RelativeProduct);
        T_Name=findViewById(R.id.T_name);
        T_Discrp=findViewById(R.id.T_Discrp);
        T_Price=findViewById(R.id.T_Price);
        btncart=findViewById(R.id.btncard);
        share=getSharedPreferences("count",MODE_PRIVATE).edit();
//        plus=findViewById(R.id.plus);
//        counter=findViewById(R.id.contuner);
//        minus=findViewById(R.id.minus);
        contpresen=new CounterPresenter(this,this);
        id=getIntent().getStringExtra("id");
      Name=getIntent().getStringExtra("name");
      Discrp=getIntent().getStringExtra("discrption");
      Price=getIntent().getStringExtra("price");
      photo=getIntent().getStringExtra("photo");
      disappear=getIntent().getStringExtra("Dissapear");
      T_Name.setText(Name);
      T_Price.setText(Price);
      if(disappear!=null){
          btncart.setVisibility(View.GONE);
      }


        T_Discrp.setText(Discrp.replace("<p>","").replace("</p>",""));
        Imgphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(ShowProduct.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.imageshopping);

                ImageView imgshop=dialog.findViewById(R.id.Image_Shoping);
                Picasso.with(getApplicationContext())
                        .load("http://ikongo.com/site/"+photo)
                        .into(imgshop);


                dialog.show();
            }
        });


//        plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int a=Integer.parseInt(counter.getText().toString());
//                a++;
//                counter.setText(String.valueOf(a));
//
//            }
//        });
//        minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int a=Integer.parseInt(counter.getText().toString());
//                if(a>1) {
//                    a--;
//                    counter.setText(String.valueOf(a));
//                }
//            }
//        });
        Picasso.with(getApplicationContext())
                .load("http://ikongo.com/site/"+photo)
                .resize(500,500)
                .into(Imgphone);

        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cart car=new Cart(countt,id,Name,Discrp,Price,photo);
                liscart.add(car);
                contpresen.GetCount(String.valueOf(liscart.size()));
                Snackbar.make(RelativeProduct,R.string.addcart,1500).show();
                ListItemCart lisst=new ListItemCart();
                share.putString("count",String.valueOf(String.valueOf(liscart.size())));
                share.commit();

                Shoping.T_Cartshop.setText(String.valueOf(liscart.size()));
                lisst.Listitem(car);
                startActivity(new Intent(ShowProduct.this,Shoping.class));
                finish();

            }
        });


    }

    @Override
    public void count(String con) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
