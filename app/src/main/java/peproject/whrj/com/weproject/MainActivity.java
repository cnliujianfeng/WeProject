package peproject.whrj.com.weproject;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.peproject.whrj.com.weproject.LaunchSimpleAdapter;

public class MainActivity extends AppCompatActivity {

    private final static String TAG="TabCustmoActivity";
    private ViewPager vp_content;   //定义一个翻页视图
    private TableLayout tab_title;  //定义一个标签布局视
    private ArrayList<String> mTitleArray=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.back:
                finish();//当点击返回后结束当前活动
            case R.id.settings:
                Toast.makeText(this,"You clicked Setting",Toast.LENGTH_SHORT).show();

                default:

        }
        return true;
    }
}
