package adapter.peproject.whrj.com.weproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.jar.Attributes;

import peproject.whrj.com.weproject.R;

public class BookCoverFragment extends Fragment {
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象

    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity(); // 获取活动页面的上下文
        // 根据布局文件fragment_book_cover.xml生成视图对象
        mView = inflater.inflate(R.layout.fragment_book_cover, container, false);

        Button button=(Button)mView.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: "));
                startActivity(intent);
            }
        });
        return mView;

    }


}
