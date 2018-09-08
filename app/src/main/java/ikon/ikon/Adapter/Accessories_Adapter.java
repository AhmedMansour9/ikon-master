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

import ikon.ikon.Model.Accessory;
import ikon.ikon.Model.Phones;
import ikon.ikon.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/8/2018.
 */

public class Accessories_Adapter extends RecyclerView.Adapter<Accessories_Adapter.MyViewHolder> {



    public List<Accessory> filteredList=new ArrayList<>();
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

    public Accessories_Adapter(List<Accessory> phon,Context context){
        filteredList=phon;
        this.con=context;
    }


    @Override
    public Accessories_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemaccessories, parent, false);
        return new Accessories_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Accessories_Adapter.MyViewHolder holder, final int position) {


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


