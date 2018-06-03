package peproject.whrj.com.weproject;


import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;

import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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



    //加载菜单栏布局··
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
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
