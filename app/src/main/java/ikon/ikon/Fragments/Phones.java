package ikon.ikon.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ikon.ikon.Activites.Navigation;
import ikon.ikon.Activites.Shoping;
import ikon.ikon.Activites.ShowProduct;
import ikon.ikon.Adapter.Phones_Adapter;
import ikon.ikon.CheckgbsAndNetwork;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.Count;
import ikon.ikon.Model.phonesResponse;
import ikon.ikon.PreSenter.CounterPresenter;
import ikon.ikon.PreSenter.GetPhonesPresenter;
import ikon.ikon.Viewes.PhonesView;
import ikon.ikonN.R;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class Phones extends Fragment implements PhonesView,SwipeRefreshLayout.OnRefreshListener,Count{

    private List<Cart> liscart=new LinkedList<>();
    public Phones() {
        // Required empty public constructor
    }
    EditText product;
     GetPhonesPresenter phons;
     View view;
     RecyclerView recyclerView;
     SharedPreferences shared;
     Phones_Adapter adapter;
     String Lan;
     SwipeRefreshLayout mSwipeRefreshLayout;
     GridLayoutManager gridLayoutManager;
    CounterPresenter cn;
    CheckgbsAndNetwork checkNetWork;
    RelativeLayout RelativePhone;
    SharedPreferences share;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_phones, container, false);
        product =view.findViewById(R.id.findyourproduct);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        phons=new GetPhonesPresenter(getContext(),this);
        shared=getActivity().getSharedPreferences("Language",MODE_PRIVATE);
        RelativePhone=view.findViewById(R.id.RelativePhone);
         Lan=shared.getString("Lann",null);
        cn=new CounterPresenter(getContext(),this);
        checkNetWork=new CheckgbsAndNetwork(getApplicationContext());
         Recyclview();
        SwipRefresh();
        share=getActivity().getSharedPreferences("count",MODE_PRIVATE);
        String count=share.getString("count",null);
        if(count!=null){
            Shoping.T_Cartshop.setText(count);
            Shoping.T_Cartshop.setBackgroundResource(R.drawable.circlecart);
        }



        return view;
    }

    @Override
    public void getPhones(List<ikon.ikon.Model.Phones> phone) {
        adapter = new Phones_Adapter(phone,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mSwipeRefreshLayout.setEnabled(false);
        RecycleviewSerach();
    }

    @Override
    public void Errorphones() {

    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_phones);
        recyclerView.setHasFixedSize(true);
    }
    public void RecycleviewSerach(){
        product.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
                adapter.notifyDataSetChanged();

            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(checkNetWork.isNetworkAvailable(getApplicationContext())){
                mSwipeRefreshLayout.setEnabled(true);
                mSwipeRefreshLayout.setRefreshing(true);
                if(Lan!=null) {
                    phons.GetPhones(Lan);
                }else {
                    if(isRTL()){
                        phons.GetPhones("ar");
                    }else {
                        phons.GetPhones("en");
                    }
                }

            }else {
                    Snackbar.make(RelativePhone,R.string.internet,1000).show();
                }
            }
        });
    }

    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }
    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }
    @Override
    public void onRefresh() {
        if(checkNetWork.isNetworkAvailable(getApplicationContext())) {
            mSwipeRefreshLayout.setEnabled(true);
            if (Lan != null) {
                phons.GetPhones(Lan);
            } else {
                if (isRTL()) {
                    phons.GetPhones("ar");
                } else {
                    phons.GetPhones("en");
                }
            }
        }else {
            Snackbar.make(RelativePhone,R.string.internet,1000).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void count(String con) {
//        Navigation.T_Cart.setText(con);
//        Shoping.T_Cartshop.setText(con);

    }
}
