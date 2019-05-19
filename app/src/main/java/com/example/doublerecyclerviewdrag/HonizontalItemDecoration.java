package com.example.doublerecyclerviewdrag;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 设置RecyclerView上下左右边距
 */
public class HonizontalItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public HonizontalItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = 5 * space;
        outRect.bottom = 5 * space;
        outRect.left = space;
        outRect.right = space;

        /*if (parent.getChildPosition(view) == 0)
            outRect.top = space;*/
    }
}