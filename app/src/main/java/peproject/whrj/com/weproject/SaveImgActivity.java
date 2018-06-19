package peproject.whrj.com.weproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import adapter.peproject.whrj.com.weproject.ScannerUtils;

public class SaveImgActivity extends AppCompatActivity {

    private String inputtext;
    private SeekBar seekBar, seekBar2, seekBar3;
    private int textsize = 25;
    private int X = 0, Y = 25;
    private Bitmap bitmap;
    private Bitmap bmp;
    private BitmapDrawable bd;
    private Drawable drawable;
    private ImageView imageView;
    private  EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_img);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);


        setSupportActionBar(toolbar);

        final Intent intent = getIntent();
        imageView = (ImageView) findViewById(R.id.select_img);
        imageView.setImageResource(intent.getIntExtra("Biaoqing", 0));

        editText = (EditText) findViewById(R.id.img_text);
        //给文本编辑框一个文本监听
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText != null) {
                    inputtext = editText.getText().toString();

                    drawable = ContextCompat.getDrawable(SaveImgActivity.this, intent.getIntExtra("Biaoqing", 0));
                    bd = (BitmapDrawable) drawable;
                    bmp = bd.getBitmap();
                    bitmap = careteWatermark(bmp, editText.getText().toString(), X, Y, textsize);
                    imageView.setImageBitmap(bitmap);
                    seekBar2.setProgress(X);
                    seekBar3.setProgress(Y);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {

                    Toast.makeText(SaveImgActivity.this,"HH",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        seekBar = (SeekBar) findViewById(R.id.textsize);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //当进度条改变时
                textsize = progress + 20;
                bitmap = careteWatermark(bmp, editText.getText().toString(), X, Y, textsize);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar2 = (SeekBar) findViewById(R.id.textX);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //当进度条改变时
                X = progress;
                bitmap = careteWatermark(bmp, editText.getText().toString(), X, Y, textsize);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar3 = (SeekBar) findViewById(R.id.textY);
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //当进度条改变时
                Y = progress;
                bitmap = careteWatermark(bmp, editText.getText().toString(), X, Y, textsize);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //保存按钮
        Button buttonsave=(Button)findViewById(R.id.save_btn);
        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有读取sd卡的权限
                if (ContextCompat.checkSelfPermission(SaveImgActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(SaveImgActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                }
                else {
                    imageView.buildDrawingCache(true);
                    imageView.buildDrawingCache();
                    Bitmap bitmap = careteWatermark(bmp, editText.getText().toString(), X, Y, textsize);
                    ScannerUtils.saveImageToGallery(SaveImgActivity.this, bitmap, ScannerUtils.ScannerType.RECEIVER);

                    Toast.makeText(SaveImgActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == 1)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                imageView.buildDrawingCache(true);
                imageView.buildDrawingCache();
                Bitmap bitmap = careteWatermark(bmp, editText.getText().toString(), X, Y, textsize);
                ScannerUtils.saveImageToGallery(SaveImgActivity.this, bitmap, ScannerUtils.ScannerType.RECEIVER);

                Toast.makeText(SaveImgActivity.this, "保存成功", Toast.LENGTH_SHORT).show();

            } else
            {
                // Permission Denied
                Toast.makeText(SaveImgActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }



    }



    //图片水印
    private Bitmap careteWatermark(Bitmap bitmap, String mark, int x, int y, int size) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bitmap, 0, 0, null);

        TextPaint mPaint = new TextPaint();
        Rect textBounds = new Rect();
        mPaint.setTextSize(size);

        mPaint.getTextBounds(mark, 0, mark.length(), textBounds);
        mPaint.setColor(Color.BLACK);
        StaticLayout layout = new StaticLayout(mark, 0, mark.length(), mPaint, (int) (w - size),
                Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.5F, true);
        canvas.translate(X,Y);
        layout.draw(canvas);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
     /*   Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(size);
        p.setAntiAlias(true);
        canvas.drawBitmap(bitmap, 0, 0, p);
        canvas.drawText(mark, x, y, p);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();*/
        return bmp;


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_save, menu);


        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save_btn:

                break;


            case R.id.back:
                finish();//当点击返回后结束当前活动
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Setting", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }




}
