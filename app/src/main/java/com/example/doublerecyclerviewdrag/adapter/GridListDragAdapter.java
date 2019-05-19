package com.example.doublerecyclerviewdrag.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.doublerecyclerviewdrag.MainActivity;
import com.example.doublerecyclerviewdrag.R;

import java.util.List;

public class GridListDragAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> list;
    private LinearLayout gridLinearlayout;

    public GridListDragAdapter(Context context, List<String> list, LinearLayout gridLinearlayout) {
        this.mContext = context;
        this.list = list;
        this.gridLinearlayout = gridLinearlayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyDragViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_list_drag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyDragViewHolder viewHolder = (MyDragViewHolder) holder;
        viewHolder.tvTag.setText(list.get(position));
        viewHolder.relativeLayout.setTag(list.get(position));
    }

    public void updateList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void removeListData(String string) {
        list.remove(string);
        updateList(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyDragViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTag;
        private ImageView imageview;
        private RelativeLayout relativeLayout;

        public MyDragViewHolder(View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tv_tag);
            imageview = itemView.findViewById(R.id.imageview);
            relativeLayout = itemView.findViewById(R.id.relativelayout);
            relativeLayout.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    View.DragShadowBuilder builder = new View.DragShadowBuilder(v);
//                ClipData.Item item = new ClipData.Item(list.get(position));
//                ClipData clipData = new ClipData(v.getTag().toString(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                    v.startDragAndDrop(/*clipData*/null, builder, v, View.DRAG_FLAG_GLOBAL);
                    return true;
                }
            });

            relativeLayout.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent event) {
                    switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            break;
                        case DragEvent.ACTION_DRAG_LOCATION:
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            break;
                        case DragEvent.ACTION_DROP:
                            //RecyclerView target = (RecyclerView)view.getRootView().findViewById(R.id.recyclerviewgrid);
                            //ClipData.Item item = event.getClipData().getItemAt(0);
                            /**
                             * 本Recyclerview Item之间进行排序
                             */
                            int lenght = list.size();
                            View viewSource = (View) event.getLocalState();
                            int indexOf = list.indexOf((String) viewSource.getTag());
                            if (indexOf != -1) {
                                list.remove((String) viewSource.getTag());
                            }
                            list.add(getAdapterPosition(), (String) viewSource.getTag());
                            if (list.size() != lenght)
                                MainActivity.thisInstance.updateHonizontalList((String) viewSource.getTag());
                            updateList(list);
                            break;
                        case DragEvent.ACTION_DRAG_ENDED:
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });

            gridLinearlayout.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            break;
                        case DragEvent.ACTION_DRAG_LOCATION:
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            break;
                        case DragEvent.ACTION_DROP:
                            //RecyclerView target = (RecyclerView)view.getRootView().findViewById(R.id.recyclerviewgrid);
                            //ClipData.Item item = event.getClipData().getItemAt(0);
                            /**
                             * 只添加另一个Recyclerview移动上来的数据
                             */
                            View viewSource = (View) event.getLocalState();
                            if (!list.contains((String) viewSource.getTag())) {
                                list.add(list.size(), (String) viewSource.getTag());
                                MainActivity.thisInstance.updateHonizontalList((String) viewSource.getTag());
                                updateList(list);
                            }
                            break;
                        case DragEvent.ACTION_DRAG_ENDED:
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
        }
    }
}