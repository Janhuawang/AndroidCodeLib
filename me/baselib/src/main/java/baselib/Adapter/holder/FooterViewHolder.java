package baselib.Adapter.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import baselib.R;

/**
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
       // progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        tv_foot = (TextView) findViewById(R.id.tv_foot);
    }

    @Override
    public void fillData() {
    }

    @Override
    public void setState(int state) {
        switch (state) {
            case FOOTER_NORMAL:
              //  progress_bar.setVisibility(View.GONE);
                tv_foot.setText("正常");
                break;

            case FOOTER_LOADING:
               // progress_bar.setVisibility(View.VISIBLE);
                tv_foot.setText("正在加载中~");
                break;

            case FOOTER_NO_MORE:
              //  progress_bar.setVisibility(View.GONE);
                tv_foot.setText("无数据了");
                break;
        }
    }
}
