package ikon.ikon.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ikon.ikon.Model.phonesResponse;
import ikon.ikon.PreSenter.GetPhonesPresenter;
import ikon.ikon.R;
import ikon.ikon.Viewes.PhonesView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Phones extends Fragment implements PhonesView{


    public Phones() {
        // Required empty public constructor
    }
     GetPhonesPresenter phons;
     View view;
     SharedPreferences shared;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_phones, container, false);
       phons=new GetPhonesPresenter(getContext(),this);
        shared=getActivity().getSharedPreferences("Language",MODE_PRIVATE);
        String Lan=shared.getString("Lann",null);
        if(Lan!=null) {
            phons.GetPhones(Lan);
        }else {
            phons.GetPhones("en");
        }



        return view;
    }

    @Override
    public void getPhones(List<ikon.ikon.Model.Phones> phone) {

    }

    @Override
    public void Errorphones() {

    }
}
