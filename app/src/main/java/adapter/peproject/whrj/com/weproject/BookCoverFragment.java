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

import com.loopeer.cardstack.CardStackView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.Attributes;

import peproject.whrj.com.weproject.R;

public class BookCoverFragment extends Fragment implements CardStackView.ItemExpendListener {
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象


    private CardStackView mCardStack;

    Integer[] color = {
            R.color.one,
            R.color.three,
            R.color.two,
            R.color.four,
            R.color.five



    };
    String[] name = {"数据结构","计算机网络","编译原理","C语言","算法设计","FPGA编程"};
    String[] scores = {"77","87","65","98","74","80"};


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity(); // 获取活动页面的上下文
        // 根据布局文件fragment_book_cover.xml生成视图对象
        mView = inflater.inflate(R.layout.fragment_book_cover, container, false);
        init(mView);
        return mView;

    }
    public void onItemExpend(boolean expend) {

    }

    void init(View view)
    {
        mCardStack = (CardStackView)view.findViewById(R.id.cardStackView);
        ScoresCardStackAdapter adapter = new ScoresCardStackAdapter(getContext());
        mCardStack.setAdapter(adapter);

        mCardStack.setItemExpendListener(this);
        List<List<LessonData>> lists = new LinkedList<>();
        for(int i = 0;i<5;i++)
        {
            List<LessonData> list = new LinkedList<>();
            for(int j = 0;j<6;j++)
            {
                list.add(new LessonData(name[j],scores[j]));
            }
            lists.add(list);
        }

        adapter.updateData(Arrays.asList(color),lists);
        //mCardStack. setAnimatorAdapter(new AllMoveDownAnimatorAdapter(mCardStack));
        //mCardStack.setAnimatorAdapter(new UpDownAnimatorAdapter(mCardStack));
        //mCardStack.setAnimatorAdapter(new UpDownStackAnimatorAdapter(mCardStack));
    }

}
