package adapter.peproject.whrj.com.weproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.TimeoutException;

import peproject.whrj.com.weproject.R;

public class YingYongAdapter extends RecyclerView.Adapter<YingYongAdapter.ViewHolder>{
    private List<Data> list;

    public YingYongAdapter(List<Data> data)
    {
        super();
        this.list=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.yingyong_item,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data data=list.get(position);
        holder.tubiao.setImageDrawable(data.getTubiao());
        holder.daxiao.setText(data.getDaxiao());
        holder.mingzi.setText(data.getMingzi());
        holder.baoming.setText(data.getBaoming());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mingzi;
        public TextView baoming;
        public TextView daxiao;
        public ImageView tubiao;
        public ViewHolder(View itemView) {
            super(itemView);
            mingzi=(TextView)itemView.findViewById(R.id.mz);
            baoming=(TextView)itemView.findViewById(R.id.bm);
            daxiao=(TextView)itemView.findViewById(R.id.dx);
            tubiao=(ImageView)itemView.findViewById(R.id.tb);
        }
    }
}
