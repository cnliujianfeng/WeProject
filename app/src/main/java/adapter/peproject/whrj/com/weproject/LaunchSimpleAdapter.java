package adapter.peproject.whrj.com.weproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import peproject.whrj.com.weproject.MainActivity;
import peproject.whrj.com.weproject.R;


public class LaunchSimpleAdapter extends PagerAdapter {

    private Context mContext;//声明一个上下文对象
    private ArrayList<View> mViewList=new ArrayList<>();//引导页的视图队列

    public LaunchSimpleAdapter(final Context context, int[] imageArray){

        mContext=context;
        for (int i=0;i<imageArray.length;i++)
        {
            //导入布局文件生成view对象
            View view= LayoutInflater.from(context).inflate(R.layout.item_launch,null);
            ImageView iv_launch=view.findViewById(R.id.iv_launch);
            RadioGroup rg_indicate=view.findViewById(R.id.rg_indicate);
            Button btn_start=view.findViewById(R.id.btn_start);
            //设置引导页的全屏图片
            iv_launch.setImageResource(imageArray[i]);
            //每一张图片添加各自的单选按钮
            for(int j=0;j<imageArray.length;j++)
            {
                RadioButton radio=new RadioButton(mContext);
                radio.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
                radio.setButtonDrawable(R.drawable.launch_guide);
                radio.setPadding(10,10,10,10);
                //把单选添加到底部指示器的单选组
                rg_indicate.addView(radio);

            }
            //当前位置单选按钮高亮显示通过for循环i的值在多个页面上保持高亮选择
            ((RadioButton)rg_indicate.getChildAt(i)).setChecked(true);
            //如果是最后一个引导页,显示入口按钮
            if(i==imageArray.length-1)
            {
                btn_start.setVisibility(View.VISIBLE);
                btn_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Activity a=(Activity)context;
                        a.finish();
                        SharedPreferences.Editor editor= context.getSharedPreferences("logtime",context.MODE_PRIVATE).edit();
                        editor.putInt("logtime",1);
                        editor.apply();
                        Intent intent=new Intent(context, MainActivity.class);
                         context.startActivity(intent);

                    }
                });
            }
            // 把该图片对应的引导页添加到引导页的视图队列
            mViewList.add(view);
        }
    }

    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0==arg1;
    }
    //从容器中销毁指定位置到页面
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView(mViewList.get(position));
    }
    //获取页面项个数
    public int getCount()
    {
        return mViewList.size();
    }
    //实例化制定位置的页面，并将其添加到容器中
    public Object instantiateItem(ViewGroup container,int position)
    {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }
}
