package peproject.whrj.com.weproject;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopeer.cardstack.CardStackView;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;

import adapter.peproject.whrj.com.weproject.GoodsPagerAdapter;
import adapter.peproject.whrj.com.weproject.WeiboDialogUtils;


public class   MainActivity extends AppCompatActivity implements CardStackView.ItemExpendListener
{

    private Dialog mWeiboDialog;
    private Context context=this;
    private DrawerLayout mDrawerLayout;
    //内容视图
    private ViewPager vp_content;//翻页视图对象
    private TabLayout tab_title;//标签布局对象
    private ArrayList<String> mTitleArray = new ArrayList<String>(); //标题图标
    private int REQUEST_CODE_SCAN = 111;
    private CardStackView mCardStack;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    break;
            }
        }
    };

    private long exitTime = 0;
     @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) {
             if (keyCode == KeyEvent.KEYCODE_BACK
                     && event.getAction() == KeyEvent.ACTION_DOWN) {
                     if ((System.currentTimeMillis() - exitTime) > 2000) {

                                mDrawerLayout.closeDrawers();


                                //弹出提示，可以有多种方式
                                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                                exitTime = System.currentTimeMillis();

                         } else {
                             finish();
                         }
                     return true;
                 }

             return super.onKeyDown(keyCode, event);
         }
    //跳转应用管理
    public void Test(View v)
    {
        mWeiboDialog = WeiboDialogUtils.createLoadingDialog(MainActivity.this, "加载中...");
        mHandler.sendEmptyMessageDelayed(1, 1000);
        Intent intent =  new Intent(MainActivity.this,TwoActivity.class);
        startActivity(intent);
    }
    //跳转二维码
    public void Sm(View v)
    {
        AndPermission.with(this).permission(Permission.CAMERA,Permission.READ_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                        ZxingConfig config=new ZxingConfig();
                        config.setPlayBeep(true);//扫描声音
                        config.setShake(true);//是否振动
                        config.setDecodeBarCode(false);//是否扫描条形码
                        config.setReactColor(R.color.white);
                        config.setFrameLineColor(R.color.white);
                        config.setFullScreenScan(false);
                        intent.putExtra(Constant.INTENT_ZXING_CONFIG,config);
                        startActivityForResult(intent,REQUEST_CODE_SCAN);
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List <String> permissions) {
                Uri packageURI = Uri.parse("package:" + getPackageName());
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

                Toast.makeText(MainActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
            }
        }).start();


    }
    public void Sc(View v)
    {
        Intent intent =  new Intent(MainActivity.this,ErWeiMaSc.class);
        startActivity(intent);
    }
    //跳转表情包制作
    public void ZZ(View view)
    {
        Intent intent =  new Intent(MainActivity.this,BiaoQingActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar替换默认ActiviBar
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View decorview = getWindow().getDecorView();
        setStatus();//状态栏沉浸


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
    //悬浮框点击事件


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
