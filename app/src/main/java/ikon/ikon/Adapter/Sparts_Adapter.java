package ikon.ikon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ikon.ikon.Activites.ShowProduct;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.Spart;
import ikon.ikonN.R;


import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/8/2018.
 */

public class Sparts_Adapter extends RecyclerView.Adapter<Sparts_Adapter.MyViewHolder>implements Filterable {


    public static List<Spart> filtered = new ArrayList<>();
    private List<Spart> mArrayList;
    public List<Spart> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    private List<Cart> liscart=new LinkedList<>();
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Discrption,T_Model,T_Price,count;
        ImageView mobile;
        ProgressBar progressBar;
        public ImageView plus,minus;
        ImageView btncart;
        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
//            T_Discrption = view.findViewById(R.id.T_Discrption);
//            T_Model = view.findViewById(R.id.T_Model);
            mobile=view.findViewById(R.id.Image_Phone);
            progressBar=view.findViewById(R.id.progrossimage);
//            btncart=view.findViewById(R.id.btncard);
//            count=view.findViewById(R.id.contuner);
//            plus=view.findViewById(R.id.plus);
//            minus=view.findViewById(R.id.minus);


        }

    }

    public Sparts_Adapter(List<Spart> phon,Context context){
        filteredList=phon;
        this.con=context;
         mArrayList=phon;
    }


    @Override
    public Sparts_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sparts, parent, false);
        return new Sparts_Adapter.MyViewHolder(itemView);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                filtered.clear();
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = mArrayList;
                } else {
                    for (Spart androidVersion : mArrayList) {
                        if (androidVersion.getProductsName().toLowerCase().contains(charString)) {
                            filtered.add(androidVersion);}}
                    filteredList = filtered;}
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (List<Spart>) filterResults.values;

                notifyDataSetChanged();
            }
        };

    }
    @Override
    public void onBindViewHolder(final Sparts_Adapter.MyViewHolder holder, final int position) {


        holder.T_Name.setText(filteredList.get(position).getProductsName());
//        holder.T_Model.setText(filteredList.get(position).getProductsModel());
//        String a = filteredList.get(position).getProductsDescription();
//        holder.T_Discrption.setText(a.replace("<p>","").replace("</p>",""));

//        holder.T_Price.setText(filteredList.get(position).getProductsPrice()+"SR");
        String i = filteredList.get(position).getProductsImage();
        Uri u = Uri.parse(i);
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext())
                .load("https://ikongo.com/"+u)
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
//        holder.T_Model.setTypeface(typeface);
//        holder.T_Discrption.setTypeface(typeface);
//        holder.T_Price.setTypeface(typeface);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inty=new Intent(con, ShowProduct.class);

                inty.putExtra("id",String.valueOf(filteredList.get(position).getProductsId()));
                inty.putExtra("photo",filteredList.get(position).getProductsImage());
                inty.putExtra("name",filteredList.get(position).getProductsName());
                inty.putExtra("discrption",filteredList.get(position).getProductsDescription());
                inty.putExtra("price",filteredList.get(position).getProductsPrice());
                inty.putExtra("Dissapear","disappear");

                con.startActivity(inty);

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

