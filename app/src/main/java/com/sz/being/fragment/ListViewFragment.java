package com.sz.being.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
//import com.github.florent37.materialviewpager.sample.R;
import com.sz.being.DetailActivity;
import com.sz.being.R;

import com.sz.being.dao.DBManager;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragment extends Fragment  {

    private ListView mListView;
    private DBManager mgr;
    private SimpleCursorAdapter mAdapter;

    private List<Object> mContentItems = new ArrayList<>();

    public static ListViewFragment newInstance() {
        return new ListViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mgr = new DBManager(getActivity().getApplicationContext());
        return inflater.inflate(R.layout.fragment_listview, container, false);
    }

    private static final String[] strs = new String[] {
            "first", "second", "third", "fourth", "fifth"
    };
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.list_view);


        final Cursor c = mgr.queryTheCursor();

        final CursorWrapper cursorWrapper = new CursorWrapper(c);

        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item,
                cursorWrapper, new String[]{"diary_label", "diary_content", "diary_date", "diary_mood", "diary_weather"},
                new int[]{R.id.list_item_diary_label, R.id.list_item_diary_content,
                        R.id.list_item_diary_date, R.id.img_mood, R.id.img_weather});

        mListView.setAdapter(mAdapter);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, strs));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                String id1 = cursorWrapper.getString(0);
                intent.putExtra("id", id1);
                startActivity(intent);
            }
        });

    }
}