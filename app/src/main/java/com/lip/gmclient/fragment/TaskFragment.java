package com.lip.gmclient.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.domain.TaskBean;
import com.lip.gmclient.untils.Constant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.w3c.dom.Text;

public class TaskFragment extends Fragment {

    private Activity context;
    private ListView listView;
    public TaskBean taskBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_task,container,false);
        listView=(ListView)view.findViewById(R.id.fragment_task_listview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

        Log.i("zz", "initData: ------------------------------");
        OkGo.<String>get(Constant.URL_TASKLIST)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson=new Gson();
                        taskBean=gson.fromJson(response.body(),TaskBean.class);

                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.e(Constant.TAG,response.getException().getMessage());
                    }
                });

    }
    class TaskListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return taskBean.getData().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){
                convertView=View.inflate(context,R.layout.fragment_task_item,null);
            }else {

            }

            return convertView;
        }
    }

    static class  ViewHolder{
        ImageView imageView;
        TextView tv_title;
        TextView tv_place;
        TextView tv_time;
    }
}
