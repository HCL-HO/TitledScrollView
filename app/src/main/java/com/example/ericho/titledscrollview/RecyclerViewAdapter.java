package com.example.ericho.titledscrollview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    static boolean INIT = true;
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
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.typeNameText.setText(itemList.get(position));
        if (holder.typeContainer.getChildCount() <= 0) {
            TextView child = new TextView(context);
            child.setText("child  " + itemList.get(position));
            holder.typeContainer.addView(child);
        }
    }
//
//    private void addBottomPadding(View itemView) {
//        int recyclerViewMeasuredHeight = recyclerView.getMeasuredHeight();
//        int childMeasuredHeight = itemView.getMeasuredHeight();
//        int numOfItems = Math.round(recyclerViewMeasuredHeight / childMeasuredHeight);
//        int extraItems = getItemCount() % numOfItems;
//        recyclerView.setPadding(0, 0, 0, extraItems * childMeasuredHeight);
//
//    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
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
        public View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            typeNameText = (TextView) itemView.findViewById(R.id.tvPlayTypeName);
            typeContainer = (GridLayout) itemView.findViewById(R.id.playTypeRadioContainer);
            this.itemView = itemView;
        }
    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            boolean isLast = position == state.getItemCount() - 1;
//            noOfChilds = noOfChilds == null ? (int) Math.ceil(parent.getMeasuredHeight() / view.getHeight()) : noOfChilds;
            if (isLast) {
                outRect.bottom = space;
                outRect.top = 0; //don't forget about recycling...
            }
            if (position == 0) {
                // don't recycle bottom if first item is also last
                // should keep bottom padding set above
                if (!isLast)
                    outRect.bottom = 0;
            }
        }
    }
}
