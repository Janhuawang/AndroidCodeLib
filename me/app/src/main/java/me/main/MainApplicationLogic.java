package me.main;

import com.spinytech.macore.multiprocess.BaseApplicationLogic;
import com.spinytech.macore.router.LocalRouter;

/**
 * 进程Application 注册供应者Provider
 * 作者：wjh on 2017/11/9 11:48
 */
public class MainApplicationLogic extends BaseApplicationLogic {
    @Override
    public void onCreate() {
        super.onCreate();
        LocalRouter.getInstance(mApplication).registerProvider("main",new MainProvider());
    }
}
