package ikon.ikon.Adapter;

import android.content.Context;
import android.graphics.Typeface;
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

import ikon.ikon.Activites.Shoping;
import ikon.ikon.Model.Cart;
import ikon.ikon.Viewes.CountView;
import ikonNNN.ikonN.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/18/2018.
 */

public class adorder_Succesfull_Adapter extends RecyclerView.Adapter<adorder_Succesfull_Adapter.MyViewHolder> {



    private List<Cart> filteredList=new ArrayList<>();

    CountView count;
    public static String TotalPrice;
    View itemView;
    Context con;
    String prrice;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Price;
        ImageView mobile;
        ProgressBar progressBar;
        ImageView btncart;
        public ImageView plus,minus,delete;

        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
            T_Price = view.findViewById(R.id.T_Price);
            mobile=view.findViewById(R.id.Image_Product);
            progressBar=view.findViewById(R.id.progrossimage);


        }


    }

    public adorder_Succesfull_Adapter(List<Cart> phon,Context context){
        filteredList=phon;
        this.con=context;
    }


    @Override
    public adorder_Succesfull_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemorderfinished, parent, false);
        return new adorder_Succesfull_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final adorder_Succesfull_Adapter.MyViewHolder holder, final int position) {



        holder.T_Name.setText(filteredList.get(position).getName());
//        holder.count.setText(filteredList.get(position).getCount());

        holder.T_Price.setText(filteredList.get(position).getPrice());
        String i = filteredList.get(position).getImage();

        Uri u = Uri.parse(i);
//        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext())
                .load("http://ikongo.com/site/"+u)
                .resize(500,500)
                .into(holder.mobile, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

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

