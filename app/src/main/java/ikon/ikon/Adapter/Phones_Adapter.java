package ikon.ikon.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Fragments.Phones;

/**
 * Created by ic on 9/7/2018.
 */

public class Phones_Adapter extends RecyclerView.Adapter<Phones_Adapter.MyViewHolder> {


    //    private ArrayList<Friendsetandget> mArrayList=new ArrayList<>();
    Context context;
    int  lastPosition=-1;
    public Boolean bollean;

    public List<Phones> filteredList=new ArrayList<>();
    View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;


        public MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.textfriendd);
            photoo=view.findViewById(R.id.imgfriend);
           );           viw.setOnClickListener(this);


        }
        @Override
        public void onClick(View view) {
            if (clickListene != null) clickListene.onClick(view, getLayoutPosition());
        }
    }

   c void setClickListener(ItemClickListener itemClickListener) {
   his.clickListene = itemClickListener;

   c void setClickButton(btnclickinterface btnclic){
   his.btnclick=btnclic;

   c MoviesAdapter(ArrayList<Friendsetandget> moviesList,Context context ) {
   ilteredList=moviesList;
   his.context=context;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itsmrecyclefriends, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Friendsetandget movie = filteredList.get(position);
        FragmentFriends.check=false;

        if (movie.getUsername() != null) {
            holder.title.setText(movie.getUsername());
        } else if (movie.getUsername() == null) {
            holder.title.setText(movie.GetEmail());
        }

        String i = movie.getPhoto();
        Uri u = Uri.parse(i);

        Picasso.with(getApplicationContext())
                .load(u)
                .placeholder(R.drawable.emptyprofile)
                .into(holder.photoo);


        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnclick.onClickCallback(view,holder.getAdapterPosition());
            }
        });



        bollean=movie.getOnline();
        if(bollean==true){
            holder.onlinee.setVisibility(View.VISIBLE);
        }else {
            holder.onlinee.setVisibility(View.INVISIBLE);
        }

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.recycle_up_from_bottom
                        : R.anim.recycle_down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;



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


