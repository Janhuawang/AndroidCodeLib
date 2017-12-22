package baselib.Adapter.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import baselib.R;

/**
 * 底部
 * 作者：wjh on 2017/12/21 16:39
 */
public class FooterViewHolder extends BaseStateViewHolder {

    private ProgressBar progress_bar;
    private TextView tv_foot;

    public FooterViewHolder(ViewGroup parent) {
        super(parent, R.layout.include_adapter_footer_layout);
    }

    @Override
    protected void onInitializeView() {
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        tv_foot = (TextView) findViewById(R.id.tv_foot);
    }

    @Override
    public void fillData() {
    }

    @Override
    public void setState(int state) {
        super.setState(state);
        switch (state) {
            case FOOTER_HIDE:
                itemView.setVisibility(View.GONE);
                break;

            case FOOTER_NORMAL:
                itemView.setVisibility(View.VISIBLE);
                progress_bar.setVisibility(View.GONE);
                tv_foot.setText(R.string.footer_normal_public);
                break;

            case FOOTER_LOADING:
                itemView.setVisibility(View.VISIBLE);
                progress_bar.setVisibility(View.VISIBLE);
                tv_foot.setText(R.string.footer_loading_public);
                break;

            case FOOTER_NO_MORE:
                itemView.setVisibility(View.VISIBLE);
                progress_bar.setVisibility(View.GONE);
                tv_foot.setText(R.string.footer_empty_public);
                break;
        }
    }

}
