package adapter.peproject.whrj.com.weproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


import peproject.whrj.com.weproject.R;
import peproject.whrj.com.weproject.SaveImgActivity;

public class BiaoQingAdapter extends RecyclerView.Adapter<BiaoQingAdapter.ViewHolder>{
    private Context mContext;
    private List<BiaoQing> biaoQingList;

    public BiaoQingAdapter(List<BiaoQing> data)
    {
        super();
        this.biaoQingList=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null)
        {
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.biaoqingbao_item,parent,false);

        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                BiaoQing biaoQing=biaoQingList.get(position);



                Intent intent=new Intent(mContext, SaveImgActivity.class);
                intent.putExtra("Biaoqing",biaoQing.getImageView());
                mContext.startActivity(intent);


            }
        });

        return  holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BiaoQing biaoQing=biaoQingList.get(position);
        Glide.with(mContext).load(biaoQing.getImageView()).override(80,80).into(holder.cardImg);

    }

    @Override
    public int getItemCount() {
        return biaoQingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
       public ImageView cardImg;

        public ViewHolder(View itemView) {
            super(itemView);
            cardImg=(ImageView) itemView.findViewById(R.id.img_card);
            cardView=(CardView)itemView;
        }
    }
}
