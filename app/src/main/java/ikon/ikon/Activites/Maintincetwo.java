package ikon.ikon.Activites;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;


import ikon.ikon.GPSTracker;
import ikon.ikon.Gbs;
import ikon.ikon.Model.OrderMaintenence;
import ikon.ikon.PreSenter.OrderPresenter;
import ikon.ikon.Viewes.OrderView;
import ikon.ikonN.R;

/**
 * Created by HP on 03/09/2018.
 */

public class Maintincetwo extends AppCompatActivity implements OrderView,OnMapReadyCallback, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    Button location;
    GPSTracker gbs;
    String addres;
    List<Address> addresses;
    final int REQUEST_LOCATION_CODE = 99;
    private GoogleMap googleMap;
    GoogleApiClient mGoogleApiClient;
    Location lastlocation;
    LocationRequest locationReques;
    Gbs e;
    TextView T_address,T_Price,T_Phone;
    String tybe,Product_id,color,issue_id,otherissue,price,Spare;
    Button btnorder;
    SharedPreferences share;
    double latitude,longitude;
    OrderPresenter ord;
    ProgressBar progressBar;
    String phoneid;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Maintincetwo.this,Maintaince.class));
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintancetwo);
         ord=new OrderPresenter(this,this);
        gbs=new GPSTracker(this);
        progressBar=findViewById(R.id.progressmaintenence);
        btnorder=findViewById(R.id.servicerequest);
        T_address=findViewById(R.id.T_Address);
        T_Phone=findViewById(R.id.T_Phone);
        T_Price=findViewById(R.id.T_Price);
        location=findViewById(R.id.getlocat);
        e=new Gbs();
        get_Intent();
        GetLocation();
        T_Price.setText(price);
        order();
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        checkLocationPermission();
    }
    public void order(){
        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share=getSharedPreferences("login",MODE_PRIVATE);
                String logi=share.getString("logggin",null);
                if(logi==null){
                    Toast.makeText(getBaseContext(), "Login First", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Maintincetwo.this,Login.class));
                    finish();
                }
                String phone=T_Phone.getText().toString();
                if(phone.isEmpty()){
                    T_Phone.setError("Enter Your phone Number");
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    OrderMaintenence order=new OrderMaintenence(phone,phoneid,Product_id,issue_id,tybe,color,otherissue,addres,
                            String.valueOf(latitude),String.valueOf(longitude),logi,price);

             ord.Order(order);
                }


            }
        });
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new android.app.AlertDialog.Builder(this)
                        .setTitle("info")
                        .setMessage("Enable gbs")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(Maintincetwo.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        REQUEST_LOCATION_CODE);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            }
            return false;
        } else {
            return true;
        }
    }

    public void get_Intent(){
        tybe=getIntent().getStringExtra("tybe");
         Product_id=getIntent().getStringExtra("Product_id");
        color=getIntent().getStringExtra("color");
        issue_id=getIntent().getStringExtra("issue_id");
        otherissue=getIntent().getStringExtra("otherissue");
        price=getIntent().getStringExtra("price");
        Spare=getIntent().getStringExtra("model");
        phoneid=getIntent().getStringExtra("phoneid");
    }

    public void GetLocation(){

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             buildGoogleapiclint();

            }

        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        gbs.RemoveCallback();
    }

    @Override
    public void OrderSuccess(String id) {
        progressBar.setVisibility(View.GONE);
        Intent inty=new Intent(Maintincetwo.this,RequestedSuccesfullyMaintenence.class);
        inty.putExtra("spare",Spare);
        inty.putExtra("price",price);
        inty.putExtra("id",id);
        startActivity(inty);
        finish();
    }

    @Override
    public void ErrorOrder() {
        Toast.makeText(getApplicationContext(), "Failed order", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationReques = new LocationRequest();
        locationReques.setSmallestDisplacement(10);
        locationReques.setFastestInterval(10000);

        locationReques.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationReques, this);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationReques);


            SettingsClient client = LocationServices.getSettingsClient(this);
            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

            task.addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(Maintincetwo.this,
                                    REQUEST_LOCATION_CODE);
                        } catch (IntentSender.SendIntentException sendEx) {
                        }
                    }
                }
            });
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        buildGoogleapiclint();
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }

                        break;
                    case Activity.RESULT_CANCELED:

                        break;
                    default:
                        break;
                }
                break;
        }

    }

    private synchronized void buildGoogleapiclint() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    @Override
    public void onConnectionSuspended(int i) {


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        CameraPosition currentPlace = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .bearing(240).tilt(30).zoom(17f).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(currentPlace));

        try {


                Geocoder geocoder = new Geocoder(getApplicationContext());
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                addres = addresses.get(0).getAddressLine(0);
                T_address.setText(addres + "");

        } catch (IOException d) {
            d.printStackTrace();
        }




    }

    @Override
    public void onMapReady(GoogleMap googleMas) {
        googleMap = googleMas;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        buildGoogleapiclint();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 99: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        buildGoogleapiclint();
                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationReques, this);                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }
}
