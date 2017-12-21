package baselib.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import baselib.Adapter.holder.BaseStateViewHolder;
import baselib.Adapter.holder.FooterViewHolder;
import baselib.Adapter.holder.HeaderViewHolder;

/**
 * 适配器基类
 * 作者：wjh on 2017/12/21 16:24
 */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {
    private final int HEADER_TYPE = 10001;
    private final int FOOTER_TYPE = 10002;


    private Context context;
    private List<T> mData = new ArrayList<>();

    private BaseStateViewHolder footerView;
    private BaseStateViewHolder headerView;
    private int loadSize = 10;// 每页加载的数据，默认10条
    private boolean hasHeader;
    private boolean hasFooter;
    private RecyclerAction mLoadMoreAction;

    public RecyclerAdapter(Context context, List<T> data) {
        this.context = context;
        setData(data);
    }

    public RecyclerAdapter(Context context, T[] data) {
        this(context, Arrays.asList(data));
    }

    public void setData(T[] data) {
        setData(Arrays.asList(data));
    }

    public void addData(T[] data) {
        addData(Arrays.asList(data));
    }

    public void setData(List<T> data) {
        if (data == null) {
            data = new ArrayList<>();
        }

        this.mData = data;
        if (hasFooter) {
            if (this.mData.size() < loadSize) {
                setFooterState(BaseStateViewHolder.FOOTER_HIDE);
            } else {
                setFooterState(BaseStateViewHolder.FOOTER_NORMAL);
            }
        }
        this.notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        if (data == null || data.isEmpty()) {// 无更多数据
            setFooterState(BaseStateViewHolder.FOOTER_NO_MORE);
            return;
        }

        int positionStart = getItemCount() - 1;
        this.mData.addAll(data);
        if (loadSize != 0 && data.size() < loadSize) {// 数据不足
            setFooterState(BaseStateViewHolder.FOOTER_NO_MORE);
        } else {
            setFooterState(BaseStateViewHolder.FOOTER_NORMAL);
        }

        notifyItemRangeInserted(positionStart, data.size());
    }

    public abstract BaseViewHolder<T> onCreateBaseViewHolder(ViewGroup parent, int viewType);

    @Override
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            return headerView = getHeaderView(parent);
        } else if (viewType == FOOTER_TYPE) {
            return footerView = getFooterView(parent);
        } else {
            return onCreateBaseViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        switch (getItemViewType(position)) {
            case HEADER_TYPE:
                break;

            case FOOTER_TYPE:
                if (mLoadMoreAction != null && isFooterLoad()) {
                    setFooterState(BaseStateViewHolder.FOOTER_LOADING);
                    mLoadMoreAction.onAction();
                }
                break;

            default:
                holder.setData(mData.get(position));
                break;
        }
    }

    @Override
    public final int getItemCount() {
        int headerCount = hasHeader ? 1 : 0;
        int footerCount = hasFooter ? 1 : 0;
        final int size = getCount();
        return size > 0 ? size + headerCount + footerCount : headerCount;
    }

    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasHeader && position == 0) {
            return HEADER_TYPE;
        }
        if (hasFooter && position == getItemCount() - 1) {
            return FOOTER_TYPE;
        }
        return super.getItemViewType(position);
    }

    private boolean isFooterLoad() {
        return footerView != null && footerView.isLoad();
    }

    private void setFooterState(int state) {
        if (footerView != null) {
            footerView.setState(state);
        }
    }

    public void setLoadMoreAction(RecyclerAction action) {
        mLoadMoreAction = action;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    protected void setLoadSize(int loadSize) {
        this.loadSize = loadSize;
    }

    protected BaseStateViewHolder getFooterView(ViewGroup parent) {
        return new FooterViewHolder(parent);
    }

    protected BaseStateViewHolder getHeaderView(ViewGroup parent) {
        return new HeaderViewHolder(parent);
    }
}
