package ikon.ikon.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;

import ikon.ikon.Model.UserRegister;
import ikon.ikon.PreSenter.Register;
import ikon.ikon.Viewes.RegisterView;
import ikonNNN.ikonN.R;

public class Register_Activity extends AppCompatActivity implements RegisterView {
    String gender;
    Button register;
    EditText E_FirstName,E_LastName,E_Emai,E_Phone,E_Password,E_ConFirmpassword;
    Register Register_Presenter;
    ProgressBar Progrossregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        E_FirstName=findViewById(R.id.E_FirstName);
        E_LastName=findViewById(R.id.E_LastName);
        Progrossregister=findViewById(R.id.progressBarRegister);
        E_Emai=findViewById(R.id.E_Emai);
        E_Phone=findViewById(R.id.E_Phone);
        E_Password=findViewById(R.id.E_Password);
        E_ConFirmpassword=findViewById(R.id.E_ConFirmPassword);
        register=findViewById(R.id.Register);
        Register_Presenter=new Register(this,this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FUtilsValidation.isEmpty(address, "please insert Address");
                FUtilsValidation.isEmpty(E_Emai, "please insert Mail");
                FUtilsValidation.isEmpty(E_FirstName, "please insert First Name");
                FUtilsValidation.isEmpty(E_LastName, "please insert Last Name");
                FUtilsValidation.isEmpty(E_Phone,"please insert Phone");
                FUtilsValidation.isEmpty(E_Password, "please insert Password");
                FUtilsValidation.isEmpty(E_ConFirmpassword, "please insert ConFirm Password");
                FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16);
                FUtilsValidation.isLengthCorrect(E_ConFirmpassword.getText().toString(), 8, 16);

                if (!FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16))
                    E_Password.setError("password min 8 char");

                if (!FUtilsValidation.isLengthCorrect(E_ConFirmpassword.getText().toString(), 8, 16))
                    E_ConFirmpassword.setError("ConFIrm password min 8 char");

                if(!E_Password.getText().toString().equals(E_ConFirmpassword.getText().toString())){
                    Toast.makeText(Register_Activity.this, "Password Not Match ConFirm Password", Toast.LENGTH_SHORT).show();
                }

                if ( !E_Emai.getText().toString().equals("") && !E_FirstName.getText().toString().equals("")&& !E_LastName.getText().toString().equals("") && !E_Phone.getText().toString().equals("") &&
                        ( FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16)))
                {
                    UserRegister user = new UserRegister();


                    user.setEmail(E_Emai.getText().toString());
                    user.setFirstName(E_FirstName.getText().toString());
                    user.setLastName(E_LastName.getText().toString());
                    user.setPhone(E_Phone.getText().toString());
                    user.setPassword(E_Password.getText().toString());

                    Progrossregister.setVisibility(View.VISIBLE);
                    Register_Presenter.register(user);
                }
            }
        });

    }

    @Override
    public void openMain() {
        Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
        Progrossregister.setVisibility(View.GONE);
        startActivity(new Intent(Register_Activity.this,Login.class));
        finish();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Email Filed", Toast.LENGTH_SHORT).show();
    }
}
