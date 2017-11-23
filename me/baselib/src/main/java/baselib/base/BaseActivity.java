package baselib.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import baselib.imp.BaseImp;

/**
 * 基类Activity
 * 作者：wjh on 2017/11/23 19:47
 */
public abstract class BaseActivity extends AppCompatActivity implements
        BaseImp {

    protected BaseApplication baseApplication;
    protected BaseActivity baseActivity;

    /**
     * 主程序Handler
     */
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApplication = BaseApplication.getBaseApplication();
        baseApplication.addActivity(this);
        baseActivity = this;

        Intent intent = getIntent();
        if (intent != null) {
            onIntentEvent(intent);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent != null) {
            onIntentEvent(intent);
        }
    }

    @Override
    public void setContentView(@LayoutRes int resId) {
        super.setContentView(resId);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        init();
    }

    /**
     * 初始化流程
     */
    private void init() {
        // 如果页面正在关闭，不调用下面的方法，防止在onIntentEvent中发现传入的参数为null，
        // 调用了finish方法后，执行下面的方法报空指针异常，友盟发现魅族3和虚拟机会有这种情况
        if (baseActivity.isFinishing()) {
            return;
        }
        initView(); // 初始化布局
        initText(); // 初始化文案
        initListener();// 初始化监听事件
        initData(); // 初始化数据
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseApplication.removeActivity(this);
    }

    public final Handler getHandler() {
        if (handler == null) {
            handler = new Handler(getMainLooper());
        }
        return handler;
    }

}
