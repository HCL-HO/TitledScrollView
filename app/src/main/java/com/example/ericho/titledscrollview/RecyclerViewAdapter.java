package com.example.ericho.titledscrollview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<String> itemList;
    Context context;
    RecyclerView recyclerView;


    public RecyclerViewAdapter(Activity mContext, List<String> itemList) {
        this.context = mContext;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_layout, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.typeNameText.setText(itemList.get(position));
        TextView child = new TextView(context);
        child.setText("child  " + itemList.get(position));
        holder.typeContainer.addView(child);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static GridLayoutManager getPK10ClassicGridManager(Context context) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position < 6) {
                    return 1;
                } else {
                    return 2;
                }
            }
        });
        return gridLayoutManager;
    }

    public static GridLayoutManager getNormalGridManager(Context context) {
        return new GridLayoutManager(context, 1);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView typeNameText;
        public GridLayout typeContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            typeNameText = (TextView) itemView.findViewById(R.id.tvPlayTypeName);
            typeContainer = (GridLayout) itemView.findViewById(R.id.playTypeRadioContainer);
        }
    }
}
