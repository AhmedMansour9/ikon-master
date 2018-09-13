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
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ikon.ikon.Activites.Navigation;
import ikon.ikon.Activites.Shoping;
import ikon.ikon.Activites.ShowProduct;
import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.Bussiness.items;
import ikon.ikon.Fragments.GuesFragment;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.Items;
import ikon.ikon.Model.Phone;
import ikon.ikon.Model.Phones;
import ikonNNN.ikonN.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/7/2018.
 */

public class Phones_Adapter extends RecyclerView.Adapter<Phones_Adapter.MyViewHolder> {


    public static List<items> itemcart= new ArrayList<>();
    public List<Phones> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    private List<Cart> liscart=new LinkedList<>();
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Discrption,T_Model,T_Price,count;
        ImageView mobile;
        ProgressBar progressBar;
        ImageView btncart;
        public ImageView plus,minus;

        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
            T_Discrption = view.findViewById(R.id.T_Discrption);
            T_Model = view.findViewById(R.id.T_Model);
            T_Price = view.findViewById(R.id.T_Price);
            mobile=view.findViewById(R.id.Image_Phone);
          progressBar=view.findViewById(R.id.progrossimage);
            btncart=view.findViewById(R.id.btncard);
//            count=view.findViewById(R.id.contuner);
//            plus=view.findViewById(R.id.plus);
//            minus=view.findViewById(R.id.minus);

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
//        holder.T_Model.setText(filteredList.get(position).getProductsModel());
//        String a = filteredList.get(position).getProductsDescription();
//        holder.T_Discrption.setText(a.replace("<p>","").replace("</p>",""));



        holder.T_Price.setText(filteredList.get(position).getProductsPrice()+"SR");
        String i = filteredList.get(position).getProductsImage();

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
//        holder.T_Model.setTypeface(typeface);
//        holder.T_Discrption.setTypeface(typeface);
        holder.T_Price.setTypeface(typeface);
//        holder.btncart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int a=Integer.parseInt(holder.count.getText().toString());
//                Cart car=new Cart(String.valueOf(a),String.valueOf(filteredList.get(position).getProductsId()),filteredList.get(position).getProductsName()
//                        ,filteredList.get(position).getProductsDescription(),filteredList.get(position).getProductsPrice()
//                ,filteredList.get(position).getProductsImage());
//                liscart.add(car);
//                Navigation.T_Cart.setText(String.valueOf(liscart.size()));
//                ListItemCart lisst=new ListItemCart();
//                lisst.Listitem(car);
//
//
//            }
//        });
//        holder.plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int a=Integer.parseInt(holder.count.getText().toString());
//                a++;
//                holder.count.setText(String.valueOf(a));
//
//            }
//        });
//        holder.minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int a=Integer.parseInt(holder.count.getText().toString());
//                if(a>1) {
//                    a--;
//                    holder.count.setText(String.valueOf(a));
//                }
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Items i=new Items(String.valueOf(filteredList.get(position).getProductsId()),filteredList.get(position).getProductsName(),
//                        filteredList.get(position).getProductsDescription(),filteredList.get(position).getProductsPrice());

                Intent inty=new Intent(con, ShowProduct.class);
                inty.putExtra("id",String.valueOf(filteredList.get(position).getProductsId()));
                inty.putExtra("photo",filteredList.get(position).getProductsImage());
                inty.putExtra("name",filteredList.get(position).getProductsName());
                inty.putExtra("discrption",filteredList.get(position).getProductsDescription());
                inty.putExtra("price",filteredList.get(position).getProductsPrice());
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


