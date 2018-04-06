package com.lip.gmclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lip.gmclient.R;
import com.lip.gmclient.base.GlideApp;
import com.lip.gmclient.domain.PlantListBean;

import org.w3c.dom.Text;

public class PlantRecyclerViewAdapter extends RecyclerView.Adapter<PlantRecyclerViewAdapter.PlantViewHolder> {

    private Context context;
    private PlantListBean plantListBean;
    private OnItemClickListener mOnItemClickListener;

    public PlantRecyclerViewAdapter(Context context, PlantListBean plantListBean) {
        this.context = context;
        this.plantListBean = plantListBean;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PlantViewHolder plantViewHolder=new PlantViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_plant_item,parent,false));
        return plantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, final int position) {
        holder.textView.setText(plantListBean.getData().get(position).getPname());
        GlideApp.with(context)
                .load(plantListBean.getData().get(position).getPimg())
                .placeholder(R.drawable.task_item_default)
                .error(R.drawable.loading_error)
                .fitCenter()
                .into(holder.imageView);

        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return plantListBean.getData().size();
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public PlantViewHolder(View itemView) {
            super(itemView);

            textView=(TextView)itemView.findViewById(R.id.fragment_plant_recyclerview_item_textview);
            imageView=(ImageView)itemView.findViewById(R.id.fragment_plant_recyclerview_item_imageview);
        }
    }

    public interface OnItemClickListener{
        void onClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
}
