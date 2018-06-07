package peproject.whrj.com.weproject;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopeer.cardstack.CardStackView;

import java.util.ArrayList;

import adapter.peproject.whrj.com.weproject.GoodsPagerAdapter;


public class MainActivity extends AppCompatActivity implements CardStackView.ItemExpendListener {



    private DrawerLayout mDrawerLayout;
    //内容视图
    private ViewPager vp_content;//翻页视图对象
    private TabLayout tab_title;//标签布局对象
    private ArrayList<String> mTitleArray = new ArrayList<String>(); //标题图标

    private CardStackView mCardStack;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar替换默认ActiviBar
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);



        setSupportActionBar(toolbar);
        View decorview = getWindow().getDecorView();
        setStatus();
//        if(Build.VERSION.SDK_INT>=21){//5.0以上的系统支持
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE;//表示让应用主题内容占据系统状态栏的空间
//            decorview.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.parseColor("#00ffffff"));//设置状态栏颜色为透明
//
//        }


        mTitleArray.add("商品");
        mTitleArray.add("详情");
        vp_content=findViewById(R.id.vp_content);//从布局文件中获取vp_content的翻页视图

        initTabLayout();
        initTabViewPager();





        //获取滑动布局

        mDrawerLayout=(DrawerLayout)findViewById(R.id.dr_layout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //获取NavigationView控件
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
       // navView.setCheckedItem(R.id.nav_call);
     navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_call: Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel: "));
                    startActivity(intent);
                    break;
                    case R.id.nav_friend:
                        Uri uri = ContactsContract.Contacts.CONTENT_URI;
                        Intent intent1 = new Intent(Intent.ACTION_PICK,uri);
                        startActivityForResult(intent1,0);
                        overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_bottom);

                        break;
                    case R.id.find:
                        Intent intent2=new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.baidu.com"));
                        startActivity(intent2);
                        overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_bottom);
                        break;
                    case R.id.mail:
                        Intent intent3=new Intent(Intent.ACTION_SENDTO).setData(Uri.parse("mailto:"));
                        startActivity(intent3);
                        overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_bottom);
                        break;
                    case R.id.set:

                        Intent intent4 =  new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent4);
                        overridePendingTransition(R.anim.design_snackbar_in,
                                R.anim.design_snackbar_out);
                        break;
                }

                mDrawerLayout.closeDrawers();
                return  true;
            }
        });




    }
    public void onItemExpend(boolean expend) {

    }


    private void setStatus() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    //初始化标签
    private void initTabLayout() {
        //从布局文件中获取标签布局
        tab_title=findViewById(R.id.tab_title);
        tab_title.addTab(tab_title.newTab().setCustomView(R.layout.item_toolbar1));
        ImageView imageView=(ImageView)findViewById(R.id.iv_point1) ;
        imageView.setImageResource(R.drawable.home);

        tab_title.addTab(tab_title.newTab().setCustomView(R.layout.item_toolbar2));
        ImageView imageView1=(ImageView)findViewById(R.id.iv_point2) ;
        imageView1.setImageResource(R.drawable.gongju);
        // 给tab_title添加标签选中监听器，该监听器默认绑定了翻页视图vp_content
        tab_title.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp_content));

    }

    private void initTabViewPager() {
        // 构建一个商品信息的翻页适配器
        GoodsPagerAdapter adapter = new GoodsPagerAdapter(
                getSupportFragmentManager(), mTitleArray);
        // 给vp_content设置商品翻页适配器
        vp_content.setAdapter(adapter);
        // 给vp_content添加页面变更监听器
        vp_content.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 选中tab_title指定位置的标签
                tab_title.getTabAt(position).select();
            }
        });
    }

    //加载菜单栏布局··
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.toolbar,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);//在菜单中找到对应控件的item


        return true;
    }
    //菜单栏点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            //当点击控制滑动的自定义按钮时，按钮名是规定好的
            case android.R.id.home:
            {
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            }

            case R.id.back:
                finish();//当点击返回后结束当前活动
                break;
            case R.id.settings:
                Toast.makeText(this,"You clicked Setting",Toast.LENGTH_SHORT).show();
                break;
                default:
        }
        return true;
    }

}
