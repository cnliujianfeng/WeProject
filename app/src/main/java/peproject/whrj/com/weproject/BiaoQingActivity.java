package peproject.whrj.com.weproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.peproject.whrj.com.weproject.BiaoQing;
import adapter.peproject.whrj.com.weproject.BiaoQingAdapter;

public class BiaoQingActivity extends AppCompatActivity {


    private BiaoQing[] biaoQings={new BiaoQing(R.drawable.one),new BiaoQing(R.drawable.two),new BiaoQing(R.drawable.three),new BiaoQing(R.drawable.four), new BiaoQing(R.drawable.five),
                                  new BiaoQing(R.drawable.six),new BiaoQing(R.drawable.seven),new BiaoQing(R.drawable.eight),new BiaoQing(R.drawable.night),new BiaoQing(R.drawable.ten),
            new BiaoQing(R.drawable.shiyi),new BiaoQing(R.drawable.shier),new BiaoQing(R.drawable.shisan),new BiaoQing(R.drawable.shisi),new BiaoQing(R.drawable.shiwu),
            new BiaoQing(R.drawable.shiliu),new BiaoQing(R.drawable.shiqi),new BiaoQing(R.drawable.shiqba),new BiaoQing(R.drawable.ershiyi),new BiaoQing(R.drawable.ershi),
            new BiaoQing(R.drawable.shijiu),new BiaoQing(R.drawable.ershier),new BiaoQing(R.drawable.ershisan),new BiaoQing(R.drawable.ershisi),new BiaoQing(R.drawable.erwu),
            new BiaoQing(R.drawable.erliu),new BiaoQing(R.drawable.erqi),new BiaoQing(R.drawable.erba),new BiaoQing(R.drawable.erjiu),new BiaoQing(R.drawable.sanshi),
            new BiaoQing(R.drawable.sanyi),new BiaoQing(R.drawable.saner),new BiaoQing(R.drawable.sansan),new BiaoQing(R.drawable.sansi),new BiaoQing(R.drawable.sanwu),
            new BiaoQing(R.drawable.sanliu),new BiaoQing(R.drawable.sanqi),new BiaoQing(R.drawable.sanba),new BiaoQing(R.drawable.sanjiu),new BiaoQing(R.drawable.sishi),
            new BiaoQing(R.drawable.siyi),new BiaoQing(R.drawable.sier),new BiaoQing(R.drawable.sisan),new BiaoQing(R.drawable.sisi),new BiaoQing(R.drawable.siwu),
            new BiaoQing(R.drawable.siliu),new BiaoQing(R.drawable.siqi),new BiaoQing(R.drawable.siba),new BiaoQing(R.drawable.sijiu),new BiaoQing(R.drawable.wushi),
            new BiaoQing(R.drawable.wuyi),new BiaoQing(R.drawable.wier),new BiaoQing(R.drawable.wusan),new BiaoQing(R.drawable.wusi),new BiaoQing(R.drawable.wuwu),
            new BiaoQing(R.drawable.wuliu),new BiaoQing(R.drawable.wuqi),new BiaoQing(R.drawable.wuba),new BiaoQing(R.drawable.wujiu),new BiaoQing(R.drawable.liushi),
            new BiaoQing(R.drawable.liuyi),new BiaoQing(R.drawable.liuer),new BiaoQing(R.drawable.liusan),new BiaoQing(R.drawable.liusi),new BiaoQing(R.drawable.liuwu),
            new BiaoQing(R.drawable.liuliu),new BiaoQing(R.drawable.liuqi),new BiaoQing(R.drawable.liuba),new BiaoQing(R.drawable.liujiu),new BiaoQing(R.drawable.qishi),
            new BiaoQing(R.drawable.qiyi),new BiaoQing(R.drawable.qier)};

    private List<BiaoQing> biaoQingList=new ArrayList<>();
    private BiaoQingAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biaoqingbao_rongqi);

        biaoQingList.clear();
        for (int i=0;i<=71;i++)
        {
            biaoQingList.add(biaoQings[i]);
        }

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.biaoqing_recyclerview);
        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new BiaoQingAdapter(biaoQingList);
        recyclerView.setAdapter(adapter);

    }
}
