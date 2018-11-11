package ikon.ikon.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import ikon.ikon.Model.Banner;
import ikon.ikon.Model.Cart;
import ikon.ikon.Viewes.CountView;
import ikon.ikonN.R;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ic on 9/23/2018.
 */

public class Banner_Adapter extends RecyclerView.Adapter<Banner_Adapter.MyViewHolder>{

    private List<Banner> filteredList=new ArrayList<>();
    SharedPreferences.Editor share;
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
        ImageView imggg;
        public MyViewHolder(View view) {
            super(view);
//            T_Name = view.findViewById(R.id.T_Name);
            imggg=view.findViewById(R.id.viewPagerItem_image1);

        }


    }

    public Banner_Adapter(List<Banner> list,Context context){
        this.filteredList=list;
        this.con=context;
    }


    @Override
    public Banner_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner, parent, false);
        return new Banner_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Banner_Adapter.MyViewHolder holder, final int position) {


        String i = filteredList.get(position).getImage();
        Uri u = Uri.parse(i);
//        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(getApplicationContext())
                .load("https://ikongo.com/public/images/mobile_banner/"+u)
                .into(holder.imggg, new Callback() {
                    @Override
                    public void onSuccess() {
//                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
//                        holder.progressBar.setVisibility(View.GONE);
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
