package baselib.Adapter.holder;

import android.view.View;
import android.view.ViewGroup;

import baselib.Adapter.BaseViewHolder;

/**
 * 作者：wjh on 2017/12/21 16:39
 */
public abstract class BaseStateViewHolder extends BaseViewHolder {
    public static final int FOOTER_NORMAL = 0;
    public static final int FOOTER_LOADING = 1;
    public static final int FOOTER_NO_MORE = 2;
    public static final int FOOTER_HIDE = 3;
    public static final int FOOTER_SPACE = 4;

    private int state;

    public BaseStateViewHolder(View itemView) {
        super(itemView);
    }

    public BaseStateViewHolder(ViewGroup parent, int layoutId) {
        super(parent, layoutId);
    }

    public abstract void fillData();

    public void setState(int state) {
        this.state = state;
    }

    public boolean isLoad() {
        return this.state == FOOTER_NORMAL;
    }
}
