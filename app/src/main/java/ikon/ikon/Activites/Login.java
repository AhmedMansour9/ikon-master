package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import ikon.ikon.Model.UserRegister;
import ikon.ikon.PreSenter.RegisterFace_Presenter;
import ikon.ikon.PreSenter.LoginPresenter;
import ikon.ikon.PreSenter.Registergoogle;
import ikon.ikon.R;
import ikon.ikon.Viewes.RegisterFaceView;
import ikon.ikon.Viewes.LoginView;
import ikon.ikon.Viewes.RegistergoogleView;

public class Login extends AppCompatActivity implements LoginView,RegisterFaceView,RegistergoogleView{
    ImageView loginfac,google;
    CallbackManager mCallbackManager;
    private ProgressBar progressBar;
    public FirebaseAuth mAuth;
    public GoogleApiClient googleApiClient;
    public static final int RequestSignInCode = 7;
    GoogleSignInOptions googleSignInOptions;
    EditText E_Email,E_Password;
    Button signin;
    LoginPresenter logiin;
    RegisterFace_Presenter regist;
    UserRegister userface;
    LoginResult loginResu;
    Registergoogle Registergoogl;
    SharedPreferences.Editor Shared;
    TextView Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        E_Email=findViewById(R.id.Log_Email);
        E_Password=findViewById(R.id.Log_Password);
        signin=findViewById(R.id.signin);
        loginfac=findViewById(R.id.loginfac);
        Register=findViewById(R.id.Register);
        Shared=getSharedPreferences("login",MODE_PRIVATE).edit();

        google=findViewById(R.id.google);
        progressBar=findViewById(R.id.progressBarlogin);
        regist=new RegisterFace_Presenter(this,this);
        Registergoogl=new Registergoogle(this,this);
        mCallbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        logiin=new LoginPresenter(this,this);
        GoogleSignOpition();
        LoginGoogle();

        LoginFac();
        Loginhome();
        Register();

    }
    public void Register()
    {
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,MainActivity.class));
                finish();
            }
        });
    }
    private void Loginhome() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FUtilsValidation.isEmpty(E_Email, "please insert Mail");
                FUtilsValidation.isEmpty(E_Password, "please insert Password");

                if(!E_Email.getText().toString().equals("")&&!E_Password.getText().toString().equals("")) {
                    UserRegister user = new UserRegister();
                    user.setEmail(E_Email.getText().toString());
                    user.setPassword(E_Password.getText().toString());
                    progressBar.setVisibility(View.VISIBLE);

                    logiin.Login(user);
                }
            }
        });
    }


    private void LoginGoogle() {

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(AuthIntent, RequestSignInCode);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestSignInCode) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()) {
                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
                FirebaseUserAuth(googleSignInAccount);
            }
        }
        }



    public void GoogleSignOpition(){
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(Login.this)
                .enableAutoManage(Login.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

    }


    public void FirebaseUserAuth(final GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        mAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task AuthResultTask) {
                        if (AuthResultTask.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            final String useer=user.getDisplayName();
                            final String emaail=user.getEmail();
                            UserRegister use=new UserRegister();
                            String y =googleSignInAccount.getId();

                            use.setFirstName(useer);
                            use.setEmail(emaail);
                            use.setId(y);

                            progressBar.setVisibility(View.VISIBLE);
                            Registergoogl.Registergoogle(use);




                        } else {
//                            Toast.makeText(LoginPresenter.this, "Turn on Internet", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void LoginFac(){
        loginfac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("email", "public_profile", "user_friends"));
                    LoginManager.getInstance().registerCallback(mCallbackManager,
                            new FacebookCallback<LoginResult>() {
                                @Override
                                public void onSuccess(LoginResult loginResult) {
                                    Log.d("Success", "LoginPresenter");
                                    loginResu=loginResult;
                                    handleFacebookAccessToken(loginResult.getAccessToken());
                                }

                                @Override
                                public void onCancel() {
//                                Toast.makeText(loginmain.this, "LoginPresenter Cancel", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onError(FacebookException exception) {
                                    Toast.makeText(Login.this, ""+exception.toString(), Toast.LENGTH_LONG).show();
                                }
                            });

            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {

        progressBar.setVisibility(View.VISIBLE);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            GraphRequest data_request = GraphRequest.newMeRequest(
                                    loginResu.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(
                                                JSONObject object,
                                                GraphResponse response) {
                                            try {
                                                String facebook_id = object.getString("id");
                                                progressBar.setVisibility(View.GONE);
                                                FirebaseUser user = mAuth.getCurrentUser();

                                                final String useer=user.getDisplayName();
                                                final String emaail=user.getEmail();
                                                final String id=user.getUid();

                                                userface = new UserRegister();
                                                userface.setFirstName(useer);
                                                userface.setEmail(emaail);
                                                userface.setId(facebook_id);
                                                progressBar.setVisibility(View.VISIBLE);
                                                regist.RegisterFace(userface);

                                            } catch (JSONException e) {
                                                // TODO Auto-generated catch block
                                            }
                                        }
                                    });
                            Bundle permission_param = new Bundle();
                            permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
                            data_request.setParameters(permission_param);
                            data_request.executeAsync();
                            data_request.executeAsync();


                        }
                    }
                });
    }

    @Override
    public void openMain(String a) {
        Shared.putString("logggin",a);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        startActivity(new Intent(Login.this, Language.class));
        finish();

    }

    @Override
    public void showError(String error) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openMainFace(String a) {
        Shared.putString("logggin",a);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        startActivity(new Intent(Login.this, Language.class));
        finish();
    }

    @Override
    public void showErrorFace(String error) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openMainGoogle(String a) {
        Shared.putString("logggin",a);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        startActivity(new Intent(Login.this, Language.class));
        finish();
    }

    @Override
    public void showErrorGoogle(String error) {
        progressBar.setVisibility(View.GONE);
    }
}
