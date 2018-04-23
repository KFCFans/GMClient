package com.lip.gmclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lip.gmclient.R;
import com.lip.gmclient.base.GlideApp;
import com.lip.gmclient.domain.ActivityListBean;
import com.lip.gmclient.domain.TaskBean;
import com.lip.gmclient.utils.Constant;
import com.lip.gmclient.utils.DateUtil;

public class ActivityListViewAdapter extends BaseAdapter {

    private Context context;
    private ActivityListBean activityListBean;

    public ActivityListViewAdapter(Context context, ActivityListBean activityListBean) {
        this.context = context;
        this.activityListBean = activityListBean;
    }

    @Override
    public int getCount() {
        return activityListBean.getData().size();
    }

    @Override
    public ActivityListBean.DataBean getItem(int position) {
        return activityListBean.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ActivityListViewAdapter.ViewHolder viewHolder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.fragment_activity_item,null);
            viewHolder=new ActivityListViewAdapter.ViewHolder();
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.fragment_activity_item_image);
            viewHolder.tv_place=(TextView)convertView.findViewById(R.id.fragment_activity_item_place);
            viewHolder.tv_time=(TextView)convertView.findViewById(R.id.fragment_activity_item_time);
            viewHolder.tv_title=(TextView)convertView.findViewById(R.id.fragment_activity_item_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ActivityListViewAdapter.ViewHolder) convertView.getTag();
        }

        // 根据位置得到数据
        ActivityListBean.DataBean bean = activityListBean.getData().get(position);
        viewHolder.tv_title.setText(bean.getAvname());
        viewHolder.tv_place.setText(bean.getAvplace());
        viewHolder.tv_time.setText(DateUtil.getDateToString(bean.getAvstime(),"yyyy年MM月dd日"));
        String final_url= Constant.URL_IMGHEAD+"/activity/"+bean.getAvpic();
        GlideApp.with(context)
                .load(final_url)
                .placeholder(R.drawable.mine_background_default)
                .fitCenter()
                .into(viewHolder.imageView);

        return convertView;
    }

    static class  ViewHolder{
        ImageView imageView;
        TextView tv_title;
        TextView tv_place;
        TextView tv_time;
    }
}
