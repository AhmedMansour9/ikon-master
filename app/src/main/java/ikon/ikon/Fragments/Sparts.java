package ikon.ikon.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ikon.ikon.Activites.Navigation;
import ikon.ikon.Activites.Shoping;
import ikon.ikon.Adapter.Accessories_Adapter;
import ikon.ikon.Adapter.Sparts_Adapter;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.Count;
import ikon.ikon.Model.Spart;
import ikon.ikon.PreSenter.GetAccessoriesPresenter;
import ikon.ikon.PreSenter.GetSpartspresenter;
import ikon.ikon.Viewes.SpartsView;
import ikonNNN.ikonN.R;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sparts extends Fragment implements SpartsView,SwipeRefreshLayout.OnRefreshListener,Count{

    GetSpartspresenter getSpartspresenter;
    RecyclerView recyclerView;
    SharedPreferences shared;
    Sparts_Adapter adapter;
    String Lan;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    EditText product;
    public Sparts() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_charges, container, false);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        product =view.findViewById(R.id.findyourproduct);
        getSpartspresenter=new GetSpartspresenter(getContext(),this);
        shared=getActivity().getSharedPreferences("Language",MODE_PRIVATE);
        Lan=shared.getString("Lann",null);
        Recyclview();
        SwipRefresh();
        RecycleviewSerach();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setEnabled(true);
                if(Lan!=null) {
                    getSpartspresenter.GetSparts(Lan);
                }else {
                    if(isRTL()){
                        getSpartspresenter.GetSparts("ar");
                    }
                    else {
                        getSpartspresenter.GetSparts("en");
                    }
                }
            }
        });


        return view;
    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_Sparts);
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
    @Override
    public void GetSparts(List<Spart> list) {
        adapter = new Sparts_Adapter(list,getContext());
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
    }

    @Override
    public void ErrorSparts() {

    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_con);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setEnabled(true);
                if(Lan!=null) {
                    getSpartspresenter.GetSparts(Lan);
                }else {
                    if (isRTL()) {
                        getSpartspresenter.GetSparts("ar");
                    } else {
                        getSpartspresenter.GetSparts("en");
                    }
                }

            }
        });
    }


    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setEnabled(true);
        if(Lan!=null) {
            getSpartspresenter.GetSparts(Lan);
        }else {
            if(isRTL()){
                getSpartspresenter.GetSparts("ar");
            }
            else {
                getSpartspresenter.GetSparts("en");
            }
        }
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
    public void onResume() {
        super.onResume();
//        Shoping.T_Cartshop.setText(String.valueOf(liscart.size()));
    }

    @Override
    public void count(String con) {
//        Navigation.T_Cart.setText(con);
//        Shoping.T_Cartshop.setText(con);

    }
}
