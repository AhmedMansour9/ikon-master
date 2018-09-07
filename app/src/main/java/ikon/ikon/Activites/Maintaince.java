package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Model.Issue;
import ikon.ikon.Model.IssueType;
import ikon.ikon.Model.Product;
import ikon.ikon.Model.Products;
import ikon.ikon.PreSenter.GetIssueResponse;
import ikon.ikon.PreSenter.GetPricePresenter;
import ikon.ikon.PreSenter.GetProductsPresenter;
import ikon.ikon.R;
import ikon.ikon.Viewes.GetPriceView;
import ikon.ikon.Viewes.IssueTybeView;
import ikon.ikon.Viewes.ProductView;

public class Maintaince extends AppCompatActivity implements ProductView,AdapterView.OnItemSelectedListener,IssueTybeView,GetPriceView{
    Button btn_ShowPrice;
    Spinner spin_Service,Spin_Model,Spin_Color,Spin_Issue;
    EditText Edit_OtherIssue;
    GetProductsPresenter getlist;
    GetIssueResponse getIssue;
    SharedPreferences shared;
    SharedPreferences shareLanguage;
     ProgressBar progressBar;
    ArrayAdapter<String> dataAd;
    ArrayAdapter<Products> ListProduct;
    ArrayAdapter<IssueType> ListIssue;
    String service,model,color,issue,otherissue;
    int Service_id,Model_id,Color_id;
    String Issue_id;
    Products y;
    GetPricePresenter getprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintaince);
        progressBar=findViewById(R.id.progressBarMaintenence);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        init();
        getprice=new GetPricePresenter(this,(GetPriceView)this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        shared=getSharedPreferences("Language",MODE_PRIVATE);
        String Lan=shared.getString("Lann",null);
//        shareLanguage=getSharedPreferences("login",MODE_PRIVATE);
//        String logi=shareLanguage.getString("logggin",null);
        otherissue=Edit_OtherIssue.getText().toString();

        getIssue=new GetIssueResponse(this,(IssueTybeView) this);
        getlist=new GetProductsPresenter(this,(ProductView)this);
        progressBar.setVisibility(View.VISIBLE);
        getlist.GetProducts(Lan);
        getIssue.GetIssuetybe(Lan);
        Get_price();
        Spin_Service();

        Spin_Color();
    }
   public void Spin_Service(){

       List<String> categories = new ArrayList<String>();
       categories.add(getResources().getString(R.string.Visit));
       categories.add(getResources().getString(R.string.maintennce));
       dataAd = new ArrayAdapter<String>(getApplicationContext(), R.layout.textcolorspinner, categories) {
           @Override
           public View getDropDownView(int position, View convertView, ViewGroup parent) {
               TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
               textView.setTextColor(Color.BLACK);
               return textView;
           }
       };
       dataAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spin_Service.setOnItemSelectedListener(this);
       spin_Service.setAdapter(dataAd);
       spin_Service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               if(spin_Service.getSelectedItem().toString().equals("زيارة")){
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

    public void Spin_Color(){

        List<String> categories = new ArrayList<String>();
        categories.add(getResources().getString(R.string.White));
        categories.add(getResources().getString(R.string.black));
        categories.add(getResources().getString(R.string.silver));

        dataAd = new ArrayAdapter<String>(getApplicationContext(), R.layout.textcolorspinner, categories) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.BLACK);
                return textView;
            }
        };
        dataAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spin_Color.setOnItemSelectedListener(this);
        Spin_Color.setAdapter(dataAd);
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
                   getprice.GetProducts(String.valueOf(Model_id),Issue_id,service);
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
                   textView.setTextColor(Color.BLACK);
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
                textView.setTextColor(Color.BLACK);
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
                for(i = 0; i<a.size(); i++){
                    if(a.get(i).getNameTypeAr().equals(issue)){
                        Issue_id=a.get(i).getId();
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
        inty.putExtra("tybe",service);
        inty.putExtra("Product_id",String.valueOf(Model_id));
        inty.putExtra("color",color);
        inty.putExtra("issue_id",Issue_id);
        inty.putExtra("otherissue",otherissue);
        inty.putExtra("price",Price);
        startActivity(inty);


    }

    @Override
    public void ErrorPrice() {

    }
}
