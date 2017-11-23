package baselib.imp;

import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Activity基类接口
 * 作者：wjh on 2017/11/23 19:51
 */
public interface BaseImp {

    void initView();

    void initText();

    void initListener();

    void initData();

    void onIntentEvent(@NonNull Intent intent);
}
