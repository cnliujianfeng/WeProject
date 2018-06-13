package peproject.whrj.com.weproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class SaveImgActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_img);

        Intent intent=getIntent();
        ImageView imageView=(ImageView)findViewById(R.id.select_img);
        imageView.setImageResource(intent.getIntExtra("Biaoqing",0));


    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_save,menu);
        MenuItem menuItem = menu.findItem(R.id.save_btn);//在菜单中找到对应控件的item


        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            //当点击控制滑动的自定义按钮时，按钮名是规定好的


            case R.id.save_btn:
                finish();//当点击返回后结束当前活动
                break;
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
