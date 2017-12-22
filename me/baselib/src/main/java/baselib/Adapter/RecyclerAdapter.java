package baselib.Adapter;

import android.content.Context;
import android.os.Handler;
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
    private RecyclerAction mLoadMoreAction;

    public RecyclerAdapter(Context context, List<T> data) {
        this.context = context;
        setData(data);
    }

    public void setData(T[] data) {
        setData(new ArrayList(Arrays.asList(data)));
    }

    public void addData(T[] data) {
        addData(new ArrayList(Arrays.asList(data)));
    }

    /**
     * 设置
     *
     * @param data
     */
    public void setData(List<T> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        if (mLoadMoreAction != null) {
            int size = data.size();
            if (size == 0) {
                setFooterState(BaseStateViewHolder.FOOTER_HIDE);
            } else if (size < loadSize) {
                setFooterState(BaseStateViewHolder.FOOTER_NO_MORE);
            } else {
                setFooterState(BaseStateViewHolder.FOOTER_NORMAL);
            }
        }

        this.mData = data;
        this.notifyDataSetChanged();
    }

    /**
     * 增加
     *
     * @param data
     */
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

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                notifyItemRangeInserted(positionStart, data.size());
            }
        });
    }

    public abstract BaseViewHolder<T> onCreateBaseViewHolder(ViewGroup parent, int viewType);

    @Override
    public final BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
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
        int footerCount = mLoadMoreAction != null ? 1 : 0;
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
        if (mLoadMoreAction != null && position == getItemCount() - 1) {
            return FOOTER_TYPE;
        }
        return super.getItemViewType(position);
    }

    private boolean isFooterLoad() {
        return footerView != null && footerView.isLoad();
    }

    /**
     * 底部状态
     *
     * @param state
     */
    private void setFooterState(int state) {
        if (footerView != null) {
            footerView.setState(state);
        }
    }

    /**
     * 底部加载回调
     *
     * @param action
     */
    public void setLoadMoreAction(RecyclerAction action) {
        mLoadMoreAction = action;
    }

    /**
     * 头部显示状态
     *
     * @param hasHeader
     */
    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    /**
     * 每页加载条数
     *
     * @param loadSize
     */
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
