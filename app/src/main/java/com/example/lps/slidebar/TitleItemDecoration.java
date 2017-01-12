package com.example.lps.slidebar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by dangxiaohui on 2017/1/12.
 */

public class TitleItemDecoration extends RecyclerView.ItemDecoration {
    List<DataBean> mDatas;
    int mTitleHeight = 100;
    Paint mPaint = new Paint();

    public TitleItemDecoration(List<DataBean> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int pos = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        String tag = mDatas.get(pos).getTag();
        View child = parent.findViewHolderForLayoutPosition(pos).itemView;
        boolean flag = false;
        if (pos + 1 < mDatas.size()) {
            if (null != tag && !tag.equals(mDatas.get(pos).getTag())) {//需要更换view
                if (child.getHeight() + child.getTop() < mTitleHeight) {
                    c.save();
                    flag = true;
                    c.translate(0, child.getHeight() + child.getTop() - mTitleHeight);
                }

            }
        }
        mPaint.setColor(parent.getContext().getResources().getColor(R.color.colorAccent));
        Rect textBounds = new Rect();
        mPaint.getTextBounds(tag, 0, tag.length(), textBounds);
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingLeft(), parent.getPaddingTop() + mTitleHeight, mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(parent.getResources().getDisplayMetrics().density * 16 + 0.5f);
        c.drawText(tag, (parent.getWidth() - textBounds.width() - parent.getPaddingLeft()) / 2,
                (parent.getPaddingTop() + mTitleHeight + textBounds.height()) / 2, mPaint);
        if (flag) c.restore();
    }
}
