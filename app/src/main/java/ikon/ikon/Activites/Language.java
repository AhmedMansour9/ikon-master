package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ikonNNN.ikonN.R;


/**
 * Created by ic on 9/5/2018.
 */

public class Language extends AppCompatActivity {
     Button btnEng,btnAtabic;
     SharedPreferences.Editor share;
     SharedPreferences shared;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared=getSharedPreferences("Language",MODE_PRIVATE);
       String Lan=shared.getString("Lann",null);
        if(Lan!=null){
            startActivity(new Intent(Language.this,Navigation.class));
            finish();
        }
        setContentView(R.layout.language);
        btnAtabic=findViewById(R.id.arabic);
        btnEng=findViewById(R.id.eng);
        share=getSharedPreferences("Language",MODE_PRIVATE).edit();


        btnAtabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent y=new Intent(Language.this,Navigation.class);
                share.putString("Lann","ar");
                share.commit();
                startActivity(y);
                finish();
            }
        });

        btnEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent y=new Intent(Language.this,Navigation.class);
                share.putString("Lann","en");
                share.commit();
                startActivity(y);
                finish();

            }
        });

    }
}
