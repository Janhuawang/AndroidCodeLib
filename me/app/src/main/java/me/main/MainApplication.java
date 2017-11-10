package me.main;


import com.spinytech.macore.router.WideRouter;

import baselib.BaseApplication;

/**
 * Application 注册进程
 * 作者：wjh on 2017/11/9 11:56
 */
public class MainApplication  extends BaseApplication{

    @Override
    public void initializeAllProcessRouter() {
        WideRouter.registerLocalRouter("me.main",MainRouterConnectService.class);
    }

    @Override
    protected void initializeLogic() {
        registerApplicationLogic("me.main",10001,MainApplicationLogic.class);
    }

    @Override
    public boolean needMultipleProcess() {
        return false;
    }
}
