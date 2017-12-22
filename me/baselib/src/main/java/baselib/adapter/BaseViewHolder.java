package baselib.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ViewHolder基类
 * 作者：wjh on 2017/12/21 16:03
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private T mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewGroup parent, int layoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        onInitializeView();
    }

    protected abstract void onInitializeView();

    public <T extends View> T findViewById(@IdRes int resId) {
        if (itemView != null) {
            return (T) itemView.findViewById(resId);
        } else {
            return null;
        }
    }

    public T getData() {
        return mData;
    }

    public void setData(final T data) {
        this.mData = data;
    }
}
