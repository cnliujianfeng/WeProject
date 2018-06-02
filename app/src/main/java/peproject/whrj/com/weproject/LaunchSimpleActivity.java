package peproject.whrj.com.weproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import adapter.peproject.whrj.com.weproject.LaunchSimpleAdapter;

public class LaunchSimpleActivity  extends AppCompatActivity{
    //声明引导页的图片数组
    private SharedPreferences pref;
    private int[] launchimageArray={R.drawable.guide_bg1,R.drawable.guide_bg2,R.drawable.guide_bg3,R.drawable.guide_bg4};

    protected void onCreate(Bundle saveInstanceState)
    {

        super.onCreate(saveInstanceState);

        pref=getSharedPreferences("logtime",MODE_PRIVATE);
        if(pref.getInt("logtime",0)==0)
        {
            setContentView(R.layout.activity_launch);
            //从布局视图获取叫vp_launchd的翻页视图
            ViewPager vp_launch=findViewById(R.id.vp_launch);
            //构建一个翻页适配器
            LaunchSimpleAdapter adapter=new LaunchSimpleAdapter(this,launchimageArray);
            //给vp_launch设置页面适配器
            vp_launch.setAdapter(adapter);
            //设置vp_launch默认显示第一个页面
            vp_launch.setCurrentItem(0);
        }
        else
        {
            setContentView(R.layout.item_launch_1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(LaunchSimpleActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },2000);


        }









    }



}
