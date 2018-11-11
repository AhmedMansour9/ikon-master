package ikon.ikon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Activites.ShowProductsId;
import ikon.ikon.Model.Myordershop;
import ikon.ikon.Model.ShopOrder;
import ikon.ikon.Viewes.CountView;
import ikon.ikonN.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/20/2018.
 */

public class AdapterMyOrders  extends RecyclerView.Adapter<AdapterMyOrders.MyViewHolder> {



    private List<Myordershop> filteredList=new ArrayList<>();

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
            T_Address=view.findViewById(R.id.T_Address);
            T_MonileNum=view.findViewById(R.id.T_Phone);
            T_Order=view.findViewById(R.id.T_Order);
            T_Price=view.findViewById(R.id.T_Price);
            getorders=view.findViewById(R.id.getorders);
            imagespare=view.findViewById(R.id.imagespare);
            T_EMail=view.findViewById(R.id.T_Email);
        }


    }

    public AdapterMyOrders(List<Myordershop> phon, Context context){
        filteredList=phon;
        this.con=context;
    }


    @Override
    public AdapterMyOrders.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemordershopping, parent, false);
        return new AdapterMyOrders.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterMyOrders.MyViewHolder holder, final int position) {


        holder.T_Name.setText(filteredList.get(position).getCustomersName());
        holder.T_Address.setText(filteredList.get(position).getCustomersStreetAddress());
        holder.T_Name.setText(filteredList.get(position).getCustomersName());
        holder.T_MonileNum.setText(filteredList.get(position).getCustomersTelephone());
        holder.T_EMail.setText(filteredList.get(position).getEmail());
        if(filteredList.get(position).getOrderedSource().equals("1")){
            holder.T_Order.setText("Web");
        }else {
            holder.T_Order.setText("Mobile");
        }


        holder.T_Price.setText(filteredList.get(position).getOrderPrice()+"SR");
//        holder.T_Discrption.setText(a.replace("<p>","").replace("</p>",""));

        holder.getorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inty=new Intent(con, ShowProductsId.class);
                inty.putExtra("id",filteredList.get(position).getOrdersId());
                inty.putExtra("address",filteredList.get(position).getCustomersStreetAddress());
                inty.putExtra("firstname",filteredList.get(position).getCustomersName());
                inty.putExtra("price",filteredList.get(position).getOrderPrice());
                inty.putExtra("latitude",String.valueOf(filteredList.get(position).getLatitude()));
                inty.putExtra("longitude",String.valueOf(filteredList.get(position).getLongitude()));
                inty.putExtra("iduseer","userr");
                con.startActivity(inty);
            }
        });



        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/no.otf");
        holder.T_Name.setTypeface(typeface);
        holder.T_Address.setTypeface(typeface);
        holder.T_MonileNum.setTypeface(typeface);
        holder.T_Order.setTypeface(typeface);

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


