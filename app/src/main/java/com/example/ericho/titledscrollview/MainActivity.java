package com.example.ericho.titledscrollview;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<String> words = new ArrayList<String>() {{
            add("fudshfiud");
            add("sdiuafiu");
            add("aydsgfisdfiug");
            add("sdsdfigiuafiu");
            add("12345");
            add("3425234523");
            add("23452345");
            add("23452345324");
            add("fudshfiud");
            add("sdiuafiu");
            add("aydsgfisdfiug");
            add("sdsdfigiuafiu");
            add("12345");
            add("3425234523");
            add("23452345");
            add("23452345324");
            add("fudshfiud");
            add("sdiuafiu");
            add("aydsgfisdfiug");
            add("sdsdfigiuafiu");
            add("12345");
            add("3425234523");
            add("23452345");
            add("23452345324");
            add("fudshfiud");
            add("sdiuafiu");
            add("aydsgfisdfiug");
            add("sdsdfigiuafiu");
            add("12345");
            add("3425234523");
            add("23452345");
            add("23452345324");
        }};

        for (String s : words) {
            TabLayout.Tab tab = tabLayout.newTab();
            tabLayout.addTab(tab.setText(s));
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, words);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(RecyclerViewAdapter.getNormalGridManager(this));
        setBottomPadding(recyclerView);

        HeaderScrollerManager headerScrollerManager = new HeaderScrollerManager(recyclerView, tabLayout);
        headerScrollerManager.bindOnListScrollListener();
    }

    private void setBottomPadding(RecyclerView recyclerView) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels * 3 / 4;
        recyclerView.addItemDecoration(new RecyclerViewAdapter.SpacesItemDecoration(height));
    }


}
