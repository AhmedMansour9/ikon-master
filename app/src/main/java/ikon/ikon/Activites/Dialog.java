package ikon.ikon.Activites;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.GPSTracker;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.OrderShop;
import ikon.ikon.PreSenter.OrderShoppinPresenter;
import ikon.ikon.Viewes.OrderView;
import ikonNNN.ikonN.R;

/**
 * Created by ic on 9/12/2018.
 */

public class Dialog  extends AppCompatActivity implements OrderView,OnMapReadyCallback, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    TextView T_address,T_Price,T_Phone;
    Button ordershoping;
    Button btnlastorder;
    Button location;
    LocationRequest locationReques;
    String addres;
    List<Address> addresses;
    final int REQUEST_LOCATION_CODE = 99;
    private GoogleMap googleMap;
    GoogleApiClient mGoogleApiClient;
    double latitude,longitude;
    private List<Cart> liscart=new LinkedList<>();
    OrderShoppinPresenter orderpresent;
    String lanuage;
    String Totalprice;

    String postalCode="";
    String city="";
    String country="";
    SharedPreferences share;
    ListItemCart list=new ListItemCart();
    ProgressBar progressBarorder;
    String y="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ordershopping);
        T_address=findViewById(R.id.T_Address);
        T_Phone=findViewById(R.id.T_Phone);
        T_Price=findViewById(R.id.T_Price);
        location=findViewById(R.id.getlocat);
        progressBarorder=findViewById(R.id.progressBarorder);
        Totalprice=getIntent().getStringExtra("totalpric");
        T_Price.setText(Totalprice);
        if(isRTL()){
            lanuage="ar";
        }else {
            lanuage="en";
        }
        ordershoping=findViewById(R.id.servicerequest);
        orderpresent=new OrderShoppinPresenter(this,this);
        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
        GetLocation();
        ordershoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share=getSharedPreferences("login",MODE_PRIVATE);
                y="";
                String phone=T_Phone.getText().toString();
                String logi=share.getString("logggin",null);
                if(logi==null) {
                    Toast.makeText(getBaseContext(), "Login First", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Dialog.this, Login.class));
                    finish();
                }else {

                    for(int i=0;i<ShowProduct.liscart.size();i++){
                        int a=ShowProduct.liscart.size()-1;

                        if(a==i){
                         y=y+ShowProduct.liscart.get(i).getId();
                        }else {
                            y = y + ShowProduct.liscart.get(i).getId() + ",";
                        }

                    }
                    if(ShowProduct.liscart.get(1).getId()!=null){

                    }
                    if(phone.equals("")){
                        T_Phone.setError("Enter Your Phone");
                    }
                    if(addres!=null||phone!=null){

                        OrderShop o=new OrderShop(addres,String.valueOf(latitude),String.valueOf(longitude),logi,lanuage
                                ,phone,Totalprice,y,postalCode,city,country);

                        orderpresent.Order(o);
                        progressBarorder.setVisibility(View.VISIBLE);

                    }

                }



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
    public void onConnected(@Nullable Bundle bundle) {
        locationReques = new LocationRequest();
        locationReques.setSmallestDisplacement(10);
        locationReques.setFastestInterval(10000);

        locationReques.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
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
                        resolvable.startResolutionForResult(Dialog.this,
                                REQUEST_LOCATION_CODE);
                    } catch (IntentSender.SendIntentException sendEx) {
                    }
                }
            }
        });
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
            List<Address> addresses  = geocoder.getFromLocation(latitude,longitude, 1);
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
              city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
             country = addresses.get(0).getCountryName();
             postalCode = addresses.get(0).getPostalCode();


            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            addres = addresses.get(0).getAddressLine(0);
            T_address.setText(addres + "");

        } catch (IOException d) {
            d.printStackTrace();
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
                        googleMap.setMyLocationEnabled(true);
                        break;
                    case Activity.RESULT_CANCELED:

                        break;
                    default:
                        break;
                }
                break;
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
    public void GetLocation(){

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildGoogleapiclint();

            }

        });
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


    @Override
    public void OrderSuccess() {
        Toast.makeText(this, "Your Order Acceepted Will Call You Soon", Toast.LENGTH_SHORT).show();
        progressBarorder.setVisibility(View.INVISIBLE);
    }

    @Override
    public void ErrorOrder() {
        progressBarorder.setVisibility(View.INVISIBLE);
    }

    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }
    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }
}
