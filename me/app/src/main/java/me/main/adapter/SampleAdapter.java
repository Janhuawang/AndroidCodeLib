package me.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import me.main.R;

/**
 * 适配器
 */
public class SampleAdapter extends BaseAdapter {

    private String[] mDataSet;
    private Context context;

    public SampleAdapter(Context context, String[] dataSet) {
        this.mDataSet = dataSet;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mDataSet.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.adapter_text_item, null);
        TextView textView = convertView.findViewById(R.id.text);
        textView.setText(mDataSet[position]);
        return convertView;
    }

}
