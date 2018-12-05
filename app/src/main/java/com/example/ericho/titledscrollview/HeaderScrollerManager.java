package com.example.ericho.titledscrollview;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class HeaderScrollerManager {

    RecyclerView recyclerView;
    TabLayout tabLayout;

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            int tabPosition = tabLayout.getSelectedTabPosition();
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:
                    int scrolledPosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    if (scrolledPosition != tabPosition) {
                        TabLayout.Tab tab = tabLayout.getTabAt(scrolledPosition);
                        if (tab != null) {
                            tab.select();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public HeaderScrollerManager(final RecyclerView recyclerView, TabLayout tabLayout) {
        this.recyclerView = recyclerView;
        this.tabLayout = tabLayout;

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    ((GridLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(tab.getPosition(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setTabColor(R.color.theme_blue, tab);
            }

            private void setTabColor(int color, TabLayout.Tab tab) {
                TextView textView = (TextView) tab.getCustomView();
                if (textView != null) {
                    textView.setTextColor(textView.getContext().getResources().getColor(color));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setTabColor(R.color.white, tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        selectFirsScrolltTab();
    }

    private void selectFirsScrolltTab() {
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        if (tab != null) {
            tab.select();
        }
    }

    public void bindOnListScrollListener() {
        recyclerView.addOnScrollListener(onScrollListener);
        selectFirsScrolltTab();
    }

    public void unbindOnListScrollListener() {
        recyclerView.removeOnScrollListener(onScrollListener);
        selectFirsScrolltTab();
    }

}

