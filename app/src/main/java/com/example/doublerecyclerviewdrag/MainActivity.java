package com.example.doublerecyclerviewdrag;

import com.example.doublerecyclerviewdrag.adapter.HorizontalListDragadapter;
import com.example.doublerecyclerviewdrag.adapter.GridListDragAdapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewGrid, recyclerViewHorizontal;
    private LinearLayout gridLinearlayout, horizontalLinearlayout;
    private String[] arrayGrid = {" 今日头条 ", " 知乎 ", " 网易新闻 ", " 腾讯新闻 ", " 稀土掘金 ", " 简书 "};
    private String[] arrayHorizontal = {" 美团 ", " 饿了么 ", " 新浪微博 ", " 微信 ", " 高德地图 ", " 百度地图 "};
    private GridListDragAdapter adapterGrid;
    private HorizontalListDragadapter adapterHorizontal;
    private List<String> listGrid, listHorizontal;
    public static MainActivity thisInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thisInstance = this;
        recyclerViewGrid = (RecyclerView) findViewById(R.id.recyclerviewgrid);
        recyclerViewHorizontal = (RecyclerView) findViewById(R.id.recyclerviewhorizontal);

        gridLinearlayout = (LinearLayout) findViewById(R.id.grid_linearlayout);
        horizontalLinearlayout = (LinearLayout) findViewById(R.id.horizontal_linearlayout);

        recyclerViewGrid.setHasFixedSize(true);
        recyclerViewHorizontal.setHasFixedSize(true);
        listGrid = new ArrayList<>();
        for (int i = 0; i < arrayGrid.length; i++) {
            listGrid.add(arrayGrid[i]);
        }
        recyclerViewGrid.setLayoutManager(new GridLayoutManager(this, 4));
        adapterGrid = new GridListDragAdapter(this, listGrid, gridLinearlayout);
        recyclerViewGrid.setAdapter(adapterGrid);
       /*
       进行排序的时候可以使用
       ItemTouchHelper itemTouchHelperGrid = new ItemTouchHelper(new ItemGridDragHelperCallBack(new OnGridItemDragListener() {

            @Override
            public void onItemMove(int startPos, int endPos) {
                //交换变换位置的集合数据
                Collections.swap(listGrid, startPos, endPos);
                adapterGrid.notifyItemMoved(startPos, endPos);
            }
        }));
        //关联RecyclerView
        itemTouchHelperGrid.attachToRecyclerView(recyclerViewGrid);*/
        //------------------------------------------------------------------------------------------
        listHorizontal = new ArrayList<>();
        for (int i = 0; i < arrayHorizontal.length; i++) {
            listHorizontal.add(arrayHorizontal[i]);
        }
        recyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterHorizontal = new HorizontalListDragadapter(this, listHorizontal, horizontalLinearlayout);
        recyclerViewHorizontal.setAdapter(adapterHorizontal);
        recyclerViewHorizontal.addItemDecoration(new HonizontalItemDecoration(20));
        new LinearSnapHelper().attachToRecyclerView(recyclerViewHorizontal);
        /*ItemTouchHelper itemTouchHelperHonizontal = new ItemTouchHelper(new ItemHorizontalDragHelperCallBack(new OnHorizontalItemDragListener() {
            @Override
            public void onItemMove(int startPos, int endPos) {
                Collections.swap(listHorizontal, startPos, endPos);
                adapterHorizontal.notifyItemMoved(startPos, endPos);
            }
        }));
        itemTouchHelperHonizontal.attachToRecyclerView(recyclerViewHorizontal);*/
    }

    public void updateHonizontalList(String string) {
        adapterHorizontal.removeListData(string);
    }

    public void updateGridList(String string) {
        adapterGrid.removeListData(string);
    }
}
