package com.lip.gmclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lip.gmclient.R;
import com.lip.gmclient.base.GlideApp;
import com.lip.gmclient.domain.TaskBean;
import com.lip.gmclient.utils.Constant;
import com.lip.gmclient.utils.DateUtil;

public class TaskListViewAdapter extends BaseAdapter {

    private Context context;
    private TaskBean taskBean;
    private String[] areainfo={"牡丹园","桂花园","西瓜园","樱花园","桔子园","小花园","大花园"};

    public TaskListViewAdapter(Context context, TaskBean taskBean) {
        this.context = context;
        this.taskBean = taskBean;
    }

    @Override
    public int getCount() {
        return taskBean.getData().size();
    }

    @Override
    public TaskBean.DataBean getItem(int position) {
        return taskBean.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.fragment_task_item,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.fragment_task_listview_item_image);
            viewHolder.tv_place=(TextView)convertView.findViewById(R.id.fragment_task_listview_item_place);
            viewHolder.tv_time=(TextView)convertView.findViewById(R.id.fragment_task_listview_item_endtime);
            viewHolder.tv_title=(TextView)convertView.findViewById(R.id.fragment_task_listview_item_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        TaskBean.DataBean bean = taskBean.getData().get(position);
        viewHolder.tv_title.setText(bean.getTname());
        viewHolder.tv_place.setText(areainfo[bean.getAid()]);
        viewHolder.tv_time.setText(DateUtil.getDateToString(bean.getEtime(),"yyyy年MM月dd日"));

        String final_url= Constant.URL_IMGHEAD+"/task/"+bean.getTpic();
        GlideApp.with(context).load(final_url).placeholder(R.drawable.task_item_default).fitCenter().into(viewHolder.imageView);

        return convertView;
    }
    static class  ViewHolder{
        ImageView imageView;
        TextView tv_title;
        TextView tv_place;
        TextView tv_time;
    }
}

