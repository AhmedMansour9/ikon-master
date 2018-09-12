package ikon.ikon.Activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.Model.Cart;
import ikonNNN.ikonN.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/10/2018.
 */

public class ShowProduct extends AppCompatActivity{
    String Name,Discrp,Price,photo,id,count;
    TextView T_Name,T_Discrp,T_Price;
    ImageView Imgphone;
    private List<Cart> liscart=new LinkedList<>();
    Button btncart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showproduct);
        Imgphone=findViewById(R.id.Image_Phone);
        T_Name=findViewById(R.id.T_name);
        T_Discrp=findViewById(R.id.T_Discrp);
        T_Price=findViewById(R.id.T_Price);
        btncart=findViewById(R.id.btncard);
        count=getIntent().getStringExtra("count");
        id=getIntent().getStringExtra("id");
      Name=getIntent().getStringExtra("name");
      Discrp=getIntent().getStringExtra("discrption");
      Price=getIntent().getStringExtra("price");
      photo=getIntent().getStringExtra("photo");

      T_Name.setText(Name);
      T_Price.setText(Price);

        T_Discrp.setText(Discrp.replace("<p>","").replace("</p>",""));

        Picasso.with(getApplicationContext())
                .load("http://ikongo.com/site/"+photo)
                .resize(500,500)
                .into(Imgphone);

        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart car=new Cart(count,id,Name,Discrp,Price,photo);
                liscart.add(car);
                Shoping.T_Cart.setText(String.valueOf(liscart.size()));
                ListItemCart lisst=new ListItemCart();
                lisst.Listitem(car);
            }
        });


    }

}
