package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ikon.ikon.CheckgbsAndNetwork;
import ikon.ikon.Model.ColorResponse;
import ikon.ikon.Model.Colors;
import ikon.ikon.Model.IssueTybeEnglish;
import ikon.ikon.Model.IssueTybeen;
import ikon.ikon.Model.IssueType;
import ikon.ikon.Model.Phones;
import ikon.ikon.Model.Products;
import ikon.ikon.PreSenter.ColorPresenter;
import ikon.ikon.PreSenter.GetIssuePresenter;
import ikon.ikon.PreSenter.GetPhonesPresenter;
import ikon.ikon.PreSenter.GetPricePresenter;
import ikon.ikon.PreSenter.GetProductsPresenter;
import ikon.ikon.Viewes.ColorView;
import ikon.ikon.Viewes.GetPriceView;
import ikon.ikon.Viewes.IssueTybeView;
import ikon.ikon.Viewes.IssuetybeViewEnglish;
import ikon.ikon.Viewes.PhonesView;
import ikon.ikon.Viewes.ProductView;
import ikon.ikonN.R;


public class Maintaince extends AppCompatActivity implements ColorView,PhonesView,ProductView,AdapterView.OnItemSelectedListener,IssuetybeViewEnglish,IssueTybeView,GetPriceView{
    Button btn_ShowPrice;
    Spinner spin_Service,Spin_Model,Spin_Color,Spin_Issue,Spin_Phone;
    EditText Edit_OtherIssue;
    GetProductsPresenter getlist;
    GetIssuePresenter getIssue;
    SharedPreferences shared;
    SharedPreferences shareLanguage;
     ProgressBar progressBar;
    ArrayAdapter<String> dataAd;
    ArrayAdapter<Products> ListProduct;
    ArrayAdapter<IssueType> ListIssue;
    ArrayAdapter<Phones> listPhones;
    ArrayAdapter<ikon.ikon.Model.Color> Arraycolor;
    String service,model,color,issue,otherissue,T_spare,T_Phone;
    int Service_id,Model_id,Color_id,phoneid;
    String Issue_id;
    Products y;
    GetPricePresenter getprice;
    ArrayAdapter<IssueTybeEnglish> inssueneglish;
    ColorPresenter colorrespon;
    RelativeLayout RelativeMaintenence;
    CheckgbsAndNetwork checkNetWork;
    GetPhonesPresenter Getphones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintaince);
        progressBar=findViewById(R.id.progressBarMaintenence);
        colorrespon=new ColorPresenter(this,this);
        Getphones=new GetPhonesPresenter(this,this);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        init();
        Spin_Phone=findViewById(R.id.Spin_Phone);
        getprice=new GetPricePresenter(this,(GetPriceView)this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        RelativeMaintenence=findViewById(R.id.RelativeMaintenence);
        shared=getSharedPreferences("Language",MODE_PRIVATE);
        checkNetWork=new CheckgbsAndNetwork(getApplicationContext());
        String Lan=shared.getString("Lann",null);
//        shareLanguage=getSharedPreferences("login",MODE_PRIVATE);
//        String logi=shareLanguage.getString("logggin",null);


        getIssue=new GetIssuePresenter(this,(IssueTybeView) this,(IssuetybeViewEnglish)this);
        getlist=new GetProductsPresenter(this,(ProductView)this);

          if(checkNetWork.isNetworkAvailable(this)) {
              progressBar.setVisibility(View.VISIBLE);
              if (isRTL()) {
                  getIssue.GetIssuetybeArabice("ar");
                  colorrespon.GetColor("ar");
                  Getphones.GetotherPhones("ar");
              } else {

                  getIssue.GetIssuetybeEnglish("en");
                  colorrespon.GetColor("en");
                  Getphones.GetotherPhones("en");
              }
          }else {

              Snackbar snackbar;
              snackbar = Snackbar.make(RelativeMaintenence,R.string.internet, 1500);
              View snackBarView = snackbar.getView();
              snackBarView.setBackgroundColor(Color.RED);
              snackbar.show();

          }

        Get_price();
        Spin_Service();

    }
    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }
    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }
   public void Spin_Service(){

       List<String> categories = new ArrayList<String>();
       categories.add(getResources().getString(R.string.Visit));
       categories.add(getResources().getString(R.string.maintennce));
       dataAd = new ArrayAdapter<String>(getApplicationContext(), R.layout.textcolorspinner, categories) {
           @Override
           public View getDropDownView(int position, View convertView, ViewGroup parent) {
               TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
               textView.setTextColor(Color.WHITE);
               return textView;
           }
       };
       dataAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spin_Service.setOnItemSelectedListener(this);
       spin_Service.setAdapter(dataAd);
       spin_Service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               if(spin_Service.getSelectedItem().toString().equals("زيارة للفحص")){
                   service="visit";
               }else if(spin_Service.getSelectedItem().toString().equals("صيانة")){
                   service="maintenance";
               }else if(spin_Service.getSelectedItem().toString().equals("maintenance")){
                   service="maintenance";
               }
               else if(spin_Service.getSelectedItem().toString().equals("visit")){
                   service="visit";
               }

           }
           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

   }


    public void init(){
        btn_ShowPrice=findViewById(R.id.getprice);
        spin_Service=findViewById(R.id.spin_Service);
        Spin_Model=findViewById(R.id.Spin_Model);
        Spin_Color=findViewById(R.id.Spin_Color);
        Spin_Issue=findViewById(R.id.Spin_Issue);
        Edit_OtherIssue=findViewById(R.id.Spin_OtherIssue);

    }

    public void Get_price(){
        btn_ShowPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(service!=null&&Issue_id!=null){
                     progressBar.setVisibility(View.VISIBLE);
                   getprice.GetProducts(String.valueOf(phoneid),String.valueOf(Model_id),Issue_id,service);
                }


            }
        });
    }

    @Override
    public void GetProductsList(final List <Products> a) {
        progressBar.setVisibility(View.GONE);
           ListProduct = new ArrayAdapter<Products>(getApplicationContext(), R.layout.textcolorspinner,a) {
               @Override
               public View getDropDownView(int position, View convertView, ViewGroup parent) {
                   TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                   textView.setTextColor(Color.WHITE);
                   return textView;
               }
           };
           ListProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           Spin_Model.setOnItemSelectedListener(this);
           Spin_Model.setAdapter(ListProduct);
           Spin_Model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               model= Spin_Model.getSelectedItem().toString();

                   for(i = 0; i<a.size(); i++){
                       if(a.get(i).getProductsName().equals(model)){
                          Model_id=a.get(i).getProductsId();
                       }
                   }


               }
               @Override
               public void onNothingSelected(AdapterView<?> adapterView) {

               }
           });
//       }


    }


    @Override
    public void showErrorProductslist(String error) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void GetIssuetybe(final List<IssueType> a) {
        progressBar.setVisibility(View.GONE);
        ListIssue = new ArrayAdapter<IssueType>(getApplicationContext(), R.layout.textcolorspinner,a) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        ListIssue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spin_Issue.setOnItemSelectedListener(this);
        Spin_Issue.setAdapter(ListIssue);
        Spin_Issue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                issue=Spin_Issue.getSelectedItem().toString();
                if(issue!=null) {
                    for (i = 0; i < a.size(); i++) {
                        if (a.get(i).getNameTypeAr().equals(issue)) {
                            Issue_id = a.get(i).getId();
                        }
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public void showErrorIssuetybe(String error) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void Price(String Price) {
       progressBar.setVisibility(View.GONE);
        Intent inty=new Intent(Maintaince.this,Maintincetwo.class);
        otherissue=Edit_OtherIssue.getText().toString();
        inty.putExtra("tybe",service);
        inty.putExtra("Product_id",String.valueOf(Model_id));
        inty.putExtra("color",color);
        inty.putExtra("issue_id",issue);
        inty.putExtra("otherissue",otherissue);
        inty.putExtra("price",Price);
        inty.putExtra("model",model);
        inty.putExtra("phoneid",String.valueOf(phoneid));
        startActivity(inty);
        finish();


    }

    @Override
    public void ErrorPrice() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void GetissuetybeEnglish(final List<IssueTybeEnglish> list) {
        progressBar.setVisibility(View.GONE);
        inssueneglish = new ArrayAdapter<IssueTybeEnglish>(getApplicationContext(), R.layout.textcolorspinner,list) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        inssueneglish.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spin_Issue.setOnItemSelectedListener(this);
        Spin_Issue.setAdapter(inssueneglish);
        Spin_Issue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                issue=Spin_Issue.getSelectedItem().toString();
                if(issue!=null) {
                    for (i = 0; i < list.size(); i++) {
                        if (list.get(i).getNameTypeEn().equals(issue)) {
                            Issue_id = list.get(i).getId();
                        }
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    @Override
    public void ErrorIssuetybeenglish() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getColor(List<ikon.ikon.Model.Color> colo) {

        progressBar.setVisibility(View.GONE);

        Arraycolor = new ArrayAdapter<ikon.ikon.Model.Color>(getApplicationContext(), R.layout.textcolorspinner, colo) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        Arraycolor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spin_Color.setOnItemSelectedListener(this);
        Spin_Color.setAdapter(Arraycolor);
        Spin_Color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                color=Spin_Color.getSelectedItem().toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void ErrorColor() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getPhones(final List<Phones> phone) {
        progressBar.setVisibility(View.GONE);
        listPhones = new ArrayAdapter<Phones>(getApplicationContext(), R.layout.textcolorspinner,phone) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        listPhones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spin_Phone.setOnItemSelectedListener(this);
        Spin_Phone.setAdapter(listPhones);
        Spin_Phone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                T_Phone=Spin_Phone.getSelectedItem().toString();
                if(T_Phone!=null) {
                    for (i = 0; i < phone.size(); i++) {
                        if (phone.get(i).getProductsName().equals(T_Phone)) {
                            phoneid = phone.get(i).getProductsId();
                        }
                    }
                }
                progressBar.setVisibility(View.VISIBLE);
                if(isRTL()){
                    getlist.GetProducts("ar",String.valueOf(phoneid));
                }else {
                    getlist.GetProducts("en",String.valueOf(phoneid));
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public void Errorphones() {
        progressBar.setVisibility(View.GONE);
    }
}
