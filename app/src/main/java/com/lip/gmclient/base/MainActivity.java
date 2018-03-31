package com.lip.gmclient.base;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lip.gmclient.R;
import com.lip.gmclient.fragment.MineFragment;
import com.lip.gmclient.fragment.PlantFragment;
import com.lip.gmclient.fragment.TaskFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private int FirstSelectedPosition =0;
    private BottomNavigationBar bottomNavigationBar;

    private TaskFragment taskFragment=null;
    private PlantFragment plantFragment=null;
    private MineFragment mineFragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView(){
        setContentView(R.layout.activity_main);
        bottomNavigationBar=(BottomNavigationBar) findViewById(R.id.main_bottomnav);

        // 初始化底部导航栏
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_DEFAULT)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setActiveColor("#FF107FFD")
                .setInActiveColor("#e9e6e6")
                .setBarBackgroundColor("#1ccbae");
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.nav_task,"任务"))
                .addItem(new BottomNavigationItem(R.drawable.nav_plant,"植物"))
                .addItem(new BottomNavigationItem(R.drawable.nav_mine,"我的"))
                .setFirstSelectedPosition(FirstSelectedPosition)
                .initialise();
        setDefaultFragment();
    }

    private void setDefaultFragment(){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        taskFragment=new TaskFragment();
        fragmentTransaction.replace(R.id.main_framlayout,taskFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
       switch (position){
           case 0:
               if(taskFragment==null) taskFragment=new TaskFragment();
               fragmentTransaction.replace(R.id.main_framlayout,taskFragment);
               break;
           case 1:
               if(plantFragment==null) plantFragment=new PlantFragment();
               fragmentTransaction.replace(R.id.main_framlayout,plantFragment);
               break;
           case 2:
               if (mineFragment==null) mineFragment=new MineFragment();
               fragmentTransaction.replace(R.id.main_framlayout,mineFragment);
               break;
       }
        fragmentTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
    }
    @Override
    public void onTabReselected(int position) {
    }
}
