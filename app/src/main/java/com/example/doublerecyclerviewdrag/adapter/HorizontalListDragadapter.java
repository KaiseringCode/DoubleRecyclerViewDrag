package com.example.doublerecyclerviewdrag.adapter;

import com.example.doublerecyclerviewdrag.MainActivity;
import com.example.doublerecyclerviewdrag.R;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class HorizontalListDragadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> list;
    private LinearLayout horizontalLinearlayout;

    public HorizontalListDragadapter(Context context, List<String> list, LinearLayout horizontalLinearlayout) {
        this.mContext = context;
        this.list = list;
        this.horizontalLinearlayout = horizontalLinearlayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyDragViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_horizontal_list_drag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyDragViewHolder viewHolder = (MyDragViewHolder) holder;
        viewHolder.tvHorizontalTag.setText(list.get(position));
        viewHolder.tvHorizontalTag.setTag(list.get(position));
    }

    private void updateList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeListData(String string) {
        list.remove(string);
        updateList(list);
        notifyDataSetChanged();
    }

    class MyDragViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHorizontalTag;

        public MyDragViewHolder(View itemView) {
            super(itemView);
            tvHorizontalTag = itemView.findViewById(R.id.tv_horizontal_tag);
            tvHorizontalTag.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    View.DragShadowBuilder builder = new View.DragShadowBuilder(v);
//                    ClipData.Item item = new ClipData.Item(list.get(position));
//                    ClipData clipData = new ClipData(v.getTag().toString(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                    v.startDragAndDrop(null, builder, v, View.DRAG_FLAG_GLOBAL);
                    return true;
                }
            });
            tvHorizontalTag.setOnDragListener(new View.OnDragListener() {
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
                            int lenght = list.size();
                            View viewSource = (View) event.getLocalState();
                            int indexOf = list.indexOf((String) viewSource.getTag());
                            if (indexOf != -1) {
                                list.remove((String) viewSource.getTag());
                            }
                            list.add(getAdapterPosition(), (String) viewSource.getTag());
                            if (list.size() != lenght) {
                                MainActivity.thisInstance.updateGridList((String) viewSource.getTag());
                            }
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
            horizontalLinearlayout.setOnDragListener(new View.OnDragListener() {
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
                            View viewSource = (View) event.getLocalState();
                            if (list.size() < 6 && !list.contains((String) viewSource.getTag())) {
                                list.add(list.size(), (String) viewSource.getTag());
                                MainActivity.thisInstance.updateGridList((String) viewSource.getTag());
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
