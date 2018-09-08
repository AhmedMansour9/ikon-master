package ikon.ikon.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ikon.ikon.Adapter.Phones_Adapter;
import ikon.ikon.Model.phonesResponse;
import ikon.ikon.PreSenter.GetPhonesPresenter;
import ikon.ikon.R;
import ikon.ikon.Viewes.PhonesView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Phones extends Fragment implements PhonesView,SwipeRefreshLayout.OnRefreshListener{


    public Phones() {
        // Required empty public constructor
    }
     GetPhonesPresenter phons;
     View view;
     RecyclerView recyclerView;
     SharedPreferences shared;
     Phones_Adapter adapter;
     String Lan;
     SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_phones, container, false);
       phons=new GetPhonesPresenter(getContext(),this);
        shared=getActivity().getSharedPreferences("Language",MODE_PRIVATE);
         Lan=shared.getString("Lann",null);
         Recyclview();
        SwipRefresh();
        if(Lan!=null) {
            phons.GetPhones(Lan);
        }else {
            phons.GetPhones("en");
        }
          mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
              @Override
              public void onRefresh() {
              phons.GetPhones("ar");
              }
          });

        return view;
    }

    @Override
    public void getPhones(List<ikon.ikon.Model.Phones> phone) {
        Toast.makeText(getContext(), ""+phone.size(), Toast.LENGTH_SHORT).show();
        adapter = new Phones_Adapter(phone,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mSwipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void Errorphones() {

    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_phones);
        recyclerView.setHasFixedSize(true);
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
                if(Lan!=null) {
                    phons.GetPhones(Lan);
                }else {
                    phons.GetPhones("en");
                }

            }
        });
    }

    @Override
    public void onRefresh() {
        if(Lan!=null) {
            phons.GetPhones(Lan);
        }else {
            phons.GetPhones("en");
        }
    }
}
