package peproject.whrj.com.weproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.peproject.whrj.com.weproject.Data;
import adapter.peproject.whrj.com.weproject.YingYongAdapter;

public class TwoActivity extends AppCompatActivity {

    private List<Data> datalist;
    private YingYongAdapter adapter;
    private RecyclerView mrecyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState/*, @Nullable PersistableBundle persistentState*/) {
        super.onCreate(savedInstanceState/*, persistentState*/);//在5.0以上如果不把这个注释掉跳转页面后时空白。
        setContentView(R.layout.yingyongxinxi);
        datalist = new ArrayList<Data>();

        datalist.add(new Data("QQ","com.tent", "20MB",R.drawable.lianxi));
        datalist.add(new Data("100L","Cindy", "20",R.drawable.lianxi));
        datalist.add(new Data("100L","Cindy", "20",R.drawable.lianxi));
        datalist.add(new Data("100L","Cindy", "20",R.drawable.lianxi));
        datalist.add(new Data("100L","Cindy", "20",R.drawable.lianxi));
        datalist.add(new Data("100L","Cindy", "20",R.drawable.lianxi));
        datalist.add(new Data("100L","Cindy", "20",R.drawable.lianxi));
        datalist.add(new Data("100L","Cindy", "20",R.drawable.lianxi));


        adapter = new YingYongAdapter(datalist);

        mrecyclerView = (RecyclerView) findViewById(R.id.reclcler_view);

        // 设置LinearLayoutManager
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mrecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        // 为mRecyclerView设置适配器
        mrecyclerView.setAdapter(adapter);
    }





}
