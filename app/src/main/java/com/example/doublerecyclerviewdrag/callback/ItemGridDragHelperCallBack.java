package com.example.doublerecyclerviewdrag.callback;

import com.example.doublerecyclerviewdrag.listener.OnGridItemDragListener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by wj on 2018/2/24.
 * 长按拖拽
 */

public class ItemGridDragHelperCallBack extends ItemTouchHelper.Callback {

    private OnGridItemDragListener mOnGridItemDragListener;

    public ItemGridDragHelperCallBack(OnGridItemDragListener onGridItemDragListener) {
        this.mOnGridItemDragListener = onGridItemDragListener;
    }

    /**
     * 返回可以滑动的方向
     dispatchTouchEvent    *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        int dragFlags;
        if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
            //网格布局管理器允许上下左右拖动
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            //其他布局管理器允许上下拖动
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        return makeMovementFlags(dragFlags, 0);
    }

    /**
     * 拖拽到新位置时候的回调方法
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //不同Type之间不允许移动
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        if (mOnGridItemDragListener != null) {
            mOnGridItemDragListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return true;
    }

    /**
     * 当用户左右滑动的时候执行的方法
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            
    }

    public void setOnItemDragListeber(OnGridItemDragListener onGridItemDragListener) {
        this.mOnGridItemDragListener = onGridItemDragListener;
    }

}
