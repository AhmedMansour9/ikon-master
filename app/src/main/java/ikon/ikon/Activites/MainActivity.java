package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ikon.ikon.R;

public class MainActivity extends AppCompatActivity {
     Button btn_Register,btn_Login,btnGues;
     SharedPreferences share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        share=getSharedPreferences("login",MODE_PRIVATE);
        String logi=share.getString("loggg",null);
        if(logi!=null){
            startActivity(new Intent(MainActivity.this,Navigation.class));
            finish();
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
}
