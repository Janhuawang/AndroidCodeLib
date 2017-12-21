package me.main.demo.recycle;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

import baselib.Adapter.BaseViewHolder;
import baselib.Adapter.RecyclerAdapter;

/**
 * 作者：wjh on 2017/12/21 20:22
 */

public class RecycleTestAdapter extends RecyclerAdapter<Consumption> {


    public RecycleTestAdapter(Context context, List<Consumption> data) {
        super(context, data);
    }

    public RecycleTestAdapter(Context context, Consumption[] data) {
        super(context, data);
    }

    @Override
    public BaseViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new TestItemHolder(parent);
    }

}
