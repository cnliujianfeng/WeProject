package peproject.whrj.com.weproject;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.yzq.zxinglibrary.encode.CodeCreator;

public class ErWeiMaSc extends AppCompatActivity {
    private EditText editText;
    private String contentEtString;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erweima);
        imageView=findViewById(R.id.contentIvWithLogo);
        editText=findViewById(R.id.contentEt);
        //透明状态栏
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        Button button=(Button)findViewById(R.id.scBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap=null;
                contentEtString = editText.getText().toString().trim();
                if (TextUtils.isEmpty(contentEtString)) {
                    Toast.makeText(ErWeiMaSc.this, "请输入要生成二维码图片的字符串", Toast.LENGTH_SHORT).show();

                    return;
                }
                try {
                     bitmap = CodeCreator.createQRCode(contentEtString, 400, 400, null);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }

            }
        });
    }
}
