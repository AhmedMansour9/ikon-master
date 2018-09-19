package ikon.ikon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Activites.OrderLocation;
import ikon.ikon.Model.ShopOrder;
import ikon.ikon.Model.ShowOrdersyid;
import ikon.ikon.Viewes.CountView;
import ikonNNN.ikonN.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/17/2018.
 */

public class Products_id_Adapter extends RecyclerView.Adapter<Products_id_Adapter.MyViewHolder> {



    private List<ShowOrdersyid> filteredList=new ArrayList<>();

    CountView count;

    View itemView;
    Context con;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Address,T_MonileNum,T_Price,T_Order,T_EMail;
        ProgressBar progressBar;
        Button getorders;
        ImageView imagespare;
        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
            progressBar=view.findViewById(R.id.progrossimage);
            T_Price=view.findViewById(R.id.T_Price);
            imagespare=view.findViewById(R.id.imgproductid);
        }


    }

    public Products_id_Adapter(List<ShowOrdersyid> phon, Context context){
        filteredList=phon;
        this.con=context;
    }


    @Override
    public Products_id_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemhowroductid, parent, false);
        return new Products_id_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Products_id_Adapter.MyViewHolder holder, final int position) {


        holder.T_Name.setText(filteredList.get(position).getProductsName());


        holder.T_Price.setText(filteredList.get(position).getFinalPrice()+"SR");
//        holder.T_Discrption.setText(a.replace("<p>","").replace("</p>",""));

        String i = filteredList.get(position).getProductsImage();

        Uri u = Uri.parse(i);
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext())
                .load("http://ikongo.com/site/"+u)
                .resize(500,500)
                .into(holder.imagespare, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });

        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/no.otf");
        holder.T_Name.setTypeface(typeface);
        holder.T_Price.setTypeface(typeface);

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


