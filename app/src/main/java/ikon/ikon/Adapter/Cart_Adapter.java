package ikon.ikon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ikon.ikon.Activites.Shoping;
import ikon.ikon.Activites.ShowProduct;
import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.Model.Accessory;
import ikon.ikon.Model.Cart;

import ikon.ikon.Viewes.CountView;
import ikonNNN.ikonN.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/10/2018.
 */

public class Cart_Adapter  extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder> {



    private List<Cart> filteredList=new ArrayList<>();

    CountView count;
    public static String TotalPrice;
    View itemView;
    Context con;
    String prrice;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Discrption,T_Model,T_Price,count;
        ImageView mobile;
        ProgressBar progressBar;
        ImageView btncart;
        public ImageView plus,minus,delete;

        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
            T_Discrption = view.findViewById(R.id.T_Discrption);
            T_Model = view.findViewById(R.id.T_Model);
            T_Price = view.findViewById(R.id.T_Price);
            mobile=view.findViewById(R.id.Image_Phone);
            progressBar=view.findViewById(R.id.progrossimage);
            count=view.findViewById(R.id.contuner);
            delete=view.findViewById(R.id.Image_Delete);


        }


    }

    public Cart_Adapter(List<Cart> phon,Context context){
        filteredList=phon;
        this.con=context;
    }


    @Override
    public Cart_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemscart, parent, false);
        return new Cart_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Cart_Adapter.MyViewHolder holder, final int position) {



        holder.T_Name.setText(filteredList.get(position).getName());
        holder.count.setText(filteredList.get(position).getCount());
        String a = filteredList.get(position).getDiscroption();
        holder.T_Discrption.setText(a.replace("<p>","").replace("</p>",""));

        holder.T_Price.setText(filteredList.get(position).getPrice());
        String i = filteredList.get(position).getImage();

        Uri u = Uri.parse(i);
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext())
                .load("http://ikongo.com/site/"+u)
                .resize(500,500)
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

        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/no.otf");
        holder.T_Name.setTypeface(typeface);
        holder.T_Model.setTypeface(typeface);
        holder.T_Discrption.setTypeface(typeface);
        holder.T_Price.setTypeface(typeface);


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            filteredList.remove(position);
            notifyDataSetChanged();
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

