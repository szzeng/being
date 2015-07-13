package com.sz.being.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sz.being.DetailActivity;
import com.sz.being.R;
import com.sz.being.bean.DemoItem;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/2/25  18:21.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/2/25        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class ItemsAdapter extends BaseAbstractRecycleCursorAdapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;

    public ItemsAdapter(Context context) {
        super(context, null);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {
        DemoItem item = DemoItem.fromCursor(cursor);
        ((NormalTextViewHolder) holder).mTextView.setText(item.title);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false), this);
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.text_view)
        TextView mTextView;
        ItemsAdapter mAdapter;

        NormalTextViewHolder(View view, ItemsAdapter adapter) {
            super(view);
            mAdapter = adapter;
            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
                    DemoItem item = DemoItem.fromCursor((Cursor) mAdapter.getItem(getPosition()));
                    Intent intent = new Intent(mAdapter.mContext, DetailActivity.class);
                    intent.putExtra("position", getPosition());
                    intent.putExtra("title", item.title);
                    mAdapter.mContext.startActivity(intent);
                }
            });
        }
    }
}
