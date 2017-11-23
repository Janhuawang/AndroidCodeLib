package baselib.activity;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import baselib.R;
import baselib.base.BaseActivity;
import baselib.util.KeyBoardUtil;
import baselib.view.IProgressBar;

/**
 * 基类UIActivity
 * 作者：wjh on 2017/11/23 21:06
 */
public abstract class BaseUIActivity extends BaseActivity {
    /**
     * 点击空白布局关闭输入法
     */
    private boolean isClickHintKeyboardStatus = true;
    /**
     * 返回键回调接口
     */
    private KeyBack keyBack;
    /**
     * 缓冲框
     */
    private IProgressBar iProgressBar;

    /**
     * 全局事件分发 实现 触摸非输入框控件 隐藏键盘
     *
     * @param motionEvent
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (isClickHintKeyboardStatus && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            KeyBoardUtil.closeKeyboardWhenCurFocusIsNotEt(this, motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (iProgressBar != null && iProgressBar.isShown()) {
                    iProgressBar.setVisibility(View.GONE);
                    onProgressbarHide4BackPressed();
                    return true;
                } else if (keyBack != null) {
                    keyBack.onBack();
                    return true;
                }
                break;

        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 展示progressbar
     */
    public void showProgressBar() {
        showProgressBar(getTopHeight(), getBottomHeight());
    }

    /**
     * 展示progressbar
     *
     * @param topSpace    progressbar与顶部的距离
     * @param bottomSpace progressbar与底部的距离
     */
    public void showProgressBar(int topSpace, int bottomSpace) {
        if (iProgressBar != null) {
            if (!iProgressBar.isShown()) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) iProgressBar.getLayoutParams();
                if (layoutParams.topMargin != topSpace || layoutParams.bottomMargin != bottomSpace) {
                    layoutParams.topMargin = topSpace;
                    layoutParams.bottomMargin = bottomSpace;
                    iProgressBar.setLayoutParams(layoutParams);
                }
                iProgressBar.setVisibility(View.VISIBLE);
            }
        } else {
            iProgressBar = new IProgressBar(baseActivity);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.topMargin = topSpace;
            layoutParams.bottomMargin = bottomSpace;
            ((FrameLayout) getWindow().findViewById(Window.ID_ANDROID_CONTENT)).addView(iProgressBar, layoutParams);
        }
    }

    /**
     * 隐藏progressbar
     */
    public void hideProgressBar() {
        if (iProgressBar != null) {
            iProgressBar.setVisibility(View.GONE);
        }
    }

    /**
     * 用户按back键取消progressbar显示时执行此方法
     */
    protected void onProgressbarHide4BackPressed() {
    }

    /**
     * 设置点击空白布局关闭输入法状态
     *
     * @param isClickHintKeyboardStatus
     */
    protected void setClickHintKeyboardStatus(boolean isClickHintKeyboardStatus) {
        this.isClickHintKeyboardStatus = isClickHintKeyboardStatus;
    }

    /**
     * 返回键监听实现
     *
     * @param KeyBack
     */
    public void onKeyBack(KeyBack KeyBack) {
        this.keyBack = KeyBack;
    }

    /**
     * 获取顶部的高度，包含标题栏、横向字母滑动栏、tab栏等<br/>
     * 用于确定progressbar显示的高度
     *
     * @return
     */
    public int getTopHeight() {
        View appBarLayout = findViewById(R.id.appBarLayout);
        if (appBarLayout != null) {
            return appBarLayout.getMeasuredHeight();
        }
        View toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            return toolbar.getMeasuredHeight();
        }
        return 0;
    }

    /**
     * 获取底部栏的高度<br/>
     * 用于确定progressbar显示的高度
     *
     * @return
     */
    public int getBottomHeight() {
        return 0;
    }

    /**
     * 关闭本页的输入框
     */
    protected void closeSoftInput() {
        KeyBoardUtil.hideSoftInputFromWindow(this);
    }

    public interface KeyBack {
        void onBack();
    }
}
