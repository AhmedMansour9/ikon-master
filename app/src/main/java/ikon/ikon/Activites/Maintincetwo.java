package ikon.ikon.Activites;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import ikon.ikon.GPSTracker;
import ikon.ikon.Gbs;
import ikon.ikon.R;

/**
 * Created by HP on 03/09/2018.
 */

public class Maintincetwo extends AppCompatActivity{
    Button location;
    GPSTracker gbs;
    String addres;
    List<Address> addresses;
    Gbs e;
    TextView T_address,T_Price,T_Phone;
    String tybe,Product_id,color,issue_id,otherissue,price;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintancetwo);
        gbs=new GPSTracker(this);
        T_address=findViewById(R.id.T_Address);
        T_Phone=findViewById(R.id.T_Phone);
        T_Price=findViewById(R.id.T_Price);
        e=new Gbs();
        get_Intent();
        GetLocation();
        T_Price.setText(price);



    }
    public void get_Intent(){
        tybe=getIntent().getStringExtra("tybe");
         Product_id=getIntent().getStringExtra("Product_id");
        color=getIntent().getStringExtra("color");
        issue_id=getIntent().getStringExtra("issue_id");
        otherissue=getIntent().getStringExtra("otherissue");
        price=getIntent().getStringExtra("price");
        Toast.makeText(this, ""+price, Toast.LENGTH_SHORT).show();
    }

    public void GetLocation(){
        location=findViewById(R.id.getlocat);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e.isLocationServicesAvailable(getApplicationContext())) {
                    try {
                        double latitude = gbs.getLatitude();
                        double longitude = gbs.getLongitude();
                        Geocoder geocoder = new Geocoder(getApplicationContext());
                        addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        addres = addresses.get(0).getAddressLine(0);
                        T_address.setText(addres + "");

                    } catch (IOException d) {
                        d.printStackTrace();
                    }
                }else {
                    gbs.showSettingsAlert();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        gbs.RemoveCallback();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gbs.RemoveCallback();
    }
}
