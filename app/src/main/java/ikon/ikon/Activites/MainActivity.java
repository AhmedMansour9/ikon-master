package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

import ikonNNN.ikonN.R;


public class MainActivity extends AppCompatActivity {
     Button btn_Register,btn_Login,btnGues;
     SharedPreferences.Editor share;
     SharedPreferences sha;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sha=getSharedPreferences("login",MODE_PRIVATE);
        String logi=sha.getString("logggin",null);
        if(logi!=null){
            startActivity(new Intent(MainActivity.this,Navigation.class));
            finish();
        }
        shared=getSharedPreferences("Language",MODE_PRIVATE);
        String Lan=shared.getString("Lann",null);
        if(Lan!=null) {
            Locale locale = new Locale(Lan);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }

        setContentView(R.layout.activity_main);
        Open_Login();
        Open_Register();
        Open_Gues();

    }

    private void Open_Gues() {
        btnGues=findViewById(R.id.btnguest);
        btnGues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Navigation.class));
                finish();
            }
        });
    }

    public void Open_Register(){
        btn_Register=findViewById(R.id.btnRegister);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register_Activity.class));
            }
        });

    }
    public void Open_Login(){
        btn_Login=findViewById(R.id.btnlogin);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Login.class));

            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        share=getSharedPreferences("count",MODE_PRIVATE).edit();
        share.putString("count","");
        share.commit();

    }
}
