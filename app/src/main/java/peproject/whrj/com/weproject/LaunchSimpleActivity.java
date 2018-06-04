package peproject.whrj.com.weproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import adapter.peproject.whrj.com.weproject.LaunchSimpleAdapter;

public class LaunchSimpleActivity  extends AppCompatActivity{

    private SharedPreferences pref;
    //声明引导页的图片数组，将启动页要展示的图片放入
    private int[] launchimageArray={R.drawable.guide_bg1,R.drawable.guide_bg2,R.drawable.guide_bg3,R.drawable.guide_bg4};

    protected void onCreate(Bundle saveInstanceState)
    {

        super.onCreate(saveInstanceState);
        //读取配置文件信息决定要不要进入启动页界面
        pref=getSharedPreferences("logtime",MODE_PRIVATE);
        View decorview = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT>=21){//5.0以上的系统支持
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE;//表示让应用主题内容占据系统状态栏的空间
            decorview.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.parseColor("#00ffffff"));//设置状态栏颜色为透明

        }
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
