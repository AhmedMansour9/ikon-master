package ikon.ikon.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Model.Phone;
import ikon.ikon.Model.Phones;
import ikon.ikon.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/7/2018.
 */

public class Phones_Adapter extends RecyclerView.Adapter<Phones_Adapter.MyViewHolder> {



    public List<Phones> filteredList=new ArrayList<>();
    View itemView;
    Context con;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Discrption,T_Model,T_Price;
        ImageView mobile;
        ProgressBar progressBar;
        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
            T_Discrption = view.findViewById(R.id.T_Discrption);
            T_Model = view.findViewById(R.id.T_Model);
            T_Price = view.findViewById(R.id.T_Price);
            mobile=view.findViewById(R.id.Image_Phone);
          progressBar=view.findViewById(R.id.progrossimage);


        }

    }

    public Phones_Adapter(List<Phones> phon,Context context){
        filteredList=phon;
        this.con=context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phone, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.T_Name.setText(filteredList.get(position).getProductsName());
        holder.T_Model.setText(filteredList.get(position).getProductsModel());
        holder.T_Discrption.setText(filteredList.get(position).getProductsDescription());
        holder.T_Price.setText(filteredList.get(position).getProductsPrice());
        String i = filteredList.get(position).getProductsImage();
       
        Uri u = Uri.parse(i);
       holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext())
                .load("http://ikongo.com/site/"+u)
                .into(holder.mobile, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });







    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}


