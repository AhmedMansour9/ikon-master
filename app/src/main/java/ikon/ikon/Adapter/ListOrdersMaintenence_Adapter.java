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
import java.util.LinkedList;
import java.util.List;

import ikon.ikon.Activites.Accessoriescategoryid;
import ikon.ikon.Activites.OrderLocation;
import ikon.ikon.Model.Accessory;
import ikon.ikon.Model.AccessorysubCategory;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.MaintenanceOrder;
import ikon.ikon.Viewes.CountView;
import ikonNNN.ikonN.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/16/2018.
 */

public class ListOrdersMaintenence_Adapter  extends RecyclerView.Adapter<ListOrdersMaintenence_Adapter.MyViewHolder> {



    private List<MaintenanceOrder> filteredList=new ArrayList<>();

    CountView count;

    View itemView;
    Context con;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_tybe,T_Spare,T_Color,T_Issue,T_Note,T_Date,T_Price;
        ProgressBar progressBar;
       Button getlocationn;
       ImageView imagespare;
        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
            progressBar=view.findViewById(R.id.progrossimage);
            T_tybe=view.findViewById(R.id.T_tybe);
            T_Spare=view.findViewById(R.id.T_Spare);
            T_Color=view.findViewById(R.id.T_Color);
            T_Issue=view.findViewById(R.id.T_Issue);
            T_Note=view.findViewById(R.id.T_Note);
            T_Price=view.findViewById(R.id.T_Price);
            T_Date=view.findViewById(R.id.T_Date);
            getlocationn=view.findViewById(R.id.getlocationn);
            imagespare=view.findViewById(R.id.imagespare);

        }


    }

    public ListOrdersMaintenence_Adapter(List<MaintenanceOrder> phon, Context context){
        filteredList=phon;
        this.con=context;
    }


    @Override
    public ListOrdersMaintenence_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlistordersmaintenence, parent, false);
        return new ListOrdersMaintenence_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListOrdersMaintenence_Adapter.MyViewHolder holder, final int position) {

      if(filteredList.get(position).getCustomersLastname()!=null){
          holder.T_Name.setText(filteredList.get(position).getCustomersFirstname()+filteredList.get(position).getCustomersLastname());
      }else {
          holder.T_Name.setText(filteredList.get(position).getCustomersFirstname());
      }
        holder.T_tybe.setText(filteredList.get(position).getType());
        holder.T_Spare.setText(filteredList.get(position).getSparePart());
        holder.T_Color.setText(filteredList.get(position).getColor());
        holder.T_Issue.setText(filteredList.get(position).getIssue());
        holder.T_Note.setText(filteredList.get(position).getNote());
        holder.T_Date.setText(filteredList.get(position).getOrderDate());
        holder.T_Price.setText(filteredList.get(position).getPrice()+"SR");
//        holder.T_Discrption.setText(a.replace("<p>","").replace("</p>",""));
        String i = filteredList.get(position).getProductsImage();
        Uri u = Uri.parse(i);

        Picasso.with(getApplicationContext())
                .load("http://ikongo.com/site/"+u)
                .resize(500,500)
                .into(holder.imagespare, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });

        holder.getlocationn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inty=new Intent(con, OrderLocation.class);
                inty.putExtra("address",filteredList.get(position).getAddress());
                inty.putExtra("firstname",filteredList.get(position).getCustomersFirstname());
                inty.putExtra("lastname",filteredList.get(position).getCustomersLastname());
                inty.putExtra("latitude",String.valueOf(filteredList.get(position).getLatitude()));
                inty.putExtra("longitude",String.valueOf(filteredList.get(position).getLongitude()));
                con.startActivity(inty);
            }
        });



        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/no.otf");
        holder.T_Name.setTypeface(typeface);
        holder.T_tybe.setTypeface(typeface);
        holder.T_Spare.setTypeface(typeface);
        holder.T_Color.setTypeface(typeface);
        holder.T_Issue.setTypeface(typeface);
        holder.T_Note.setTypeface(typeface);
        holder.T_Date.setTypeface(typeface);
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


