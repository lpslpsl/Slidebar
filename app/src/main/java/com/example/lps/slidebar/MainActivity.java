package com.example.lps.slidebar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.promeg.pinyinhelper.Pinyin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    IndexBar indexBar;
    TextView hintText;
    private LinearLayoutManager mLayoutManager;
    List<String> mIndexDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    List<DataBean> mSouseDatas = new ArrayList<>();

    private void init() {

        hintText = (TextView) findViewById(R.id.hintText);
        indexBar = (IndexBar) findViewById(R.id.indexBar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        inittestData();
        initSouesData();
        recyclerView.setAdapter(new RecycleAdapter(this, mSouseDatas));
        indexBar.setHintTextView(hintText)
                .setLayoutmanager(mLayoutManager)
                .setSouseData(mSouseDatas);
        recyclerView.addItemDecoration(new TitleItemDecoration(mSouseDatas));

    }

    private void inittestData() {
        for (int i = 0; i < 10; i++) {
            mSouseDatas.add(new DataBean("张三"));
            mSouseDatas.add(new DataBean("浪费的考虑过"));
            mSouseDatas.add(new DataBean("根本"));
            mSouseDatas.add(new DataBean("李四"));
            mSouseDatas.add(new DataBean("欧阳克"));
            mSouseDatas.add(new DataBean("肯定能发"));
            mSouseDatas.add(new DataBean("李四"));
            mSouseDatas.add(new DataBean("王五"));
            mSouseDatas.add(new DataBean("撒"));
            mSouseDatas.add(new DataBean("艾丝凡"));
            mSouseDatas.add(new DataBean("艾弗森"));
            mSouseDatas.add(new DataBean("李四"));
            mSouseDatas.add(new DataBean("爱疯"));
            mSouseDatas.add(new DataBean("阿芳认"));
            mSouseDatas.add(new DataBean("啊"));
            mSouseDatas.add(new DataBean("欧盟和"));
            mSouseDatas.add(new DataBean("哦你概况"));
            mSouseDatas.add(new DataBean("偶尔"));
        }

    }

    private void initSouesData() {
        int size = mSouseDatas.size();
        for (int i = 0; i < size; i++) {
            DataBean dataBean = mSouseDatas.get(i);
            StringBuilder pySb = new StringBuilder();
//            取出需要被拼音化的字段
            String target = dataBean.getName();
            for (int j = 0; j < target.length(); j++) {
                pySb.append(Pinyin.toPinyin(target.charAt(j)));
            }
            dataBean.setPyName(pySb.toString());//设置转化后的拼音
            String tagString = pySb.substring(0, 1);//获取首字母
            if (tagString.matches("[A-Z]")) {
                dataBean.setTag(tagString);
            } else {
                dataBean.setTag("#");
            }
            if (!mIndexDatas.contains(tagString)) {
                mIndexDatas.add(tagString);
            }
        }
        sortData();
    }

    private void sortData() {
        Collections.sort(mSouseDatas, new Comparator<DataBean>() {
            @Override
            public int compare(DataBean o1, DataBean o2) {
                if (o1.getTag().equals("#")) return 1;
                else if (o2.getTag().equals("#")) return -1;
                return o1.getPyName().compareTo(o2.getPyName());
            }
        });
        Collections.sort(mIndexDatas, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals("#")) return 1;
                else if (o2.equals("#")) return -1;
                return o1.compareTo(o2);
            }
        });
    }
}
