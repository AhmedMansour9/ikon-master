package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ikonNNN.ikonN.R;

public class RequestedSuccesfullyMaintenence extends AppCompatActivity {
    SharedPreferences.Editor share;
    TextView text_spare,textPrice,textdone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_succesfully_maintenence);
        share=getSharedPreferences("count",MODE_PRIVATE).edit();
        text_spare=findViewById(R.id.T_Spare);
        textPrice=findViewById(R.id.T_Price);
        textdone=findViewById(R.id.TextDon);
        textdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inty=new Intent(RequestedSuccesfullyMaintenence.this,Navigation.class);
                share.putString("count","0");
                share.commit();
                ShowProduct.liscart.clear();

                inty.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                inty.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();

            }
        });

        text_spare.setText(getIntent().getStringExtra("spare"));
        textPrice.setText(getIntent().getStringExtra("price"));
    }
}
