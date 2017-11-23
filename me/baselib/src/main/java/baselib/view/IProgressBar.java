package baselib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * 缓冲圈
 *
 * @author wjh
 */
public class IProgressBar extends FrameLayout {

    public IProgressBar(Context context) {
        super(context);
        initView(context);
    }

    public IProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public IProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        IProgressBar progressBar = new IProgressBar(context, null, android.R.attr.progressBarStyle);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(layoutParams);
        addView(progressBar);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

}
