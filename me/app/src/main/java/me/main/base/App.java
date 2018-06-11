package me.main.base;


import android.content.Context;
import android.util.DisplayMetrics;

import com.google.firebase.FirebaseApp;
import com.spinytech.macore.router.WideRouter;

import baselib.base.BaseApplication;
import me.main.macore.MainApplicationLogic;
import me.main.macore.MainRouterConnectService;
import me.main.resource.LoadResource;

/**
 * Application 注册进程
 * 作者：wjh on 2017/11/9 11:56
 */
public class App extends BaseApplication {

    public static int H, W;

    @Override
    public void onCreate() {
        super.onCreate();
        LoadResource.init(this);
        getScreen(this);

        FirebaseApp.initializeApp(this); // TODO: 2018/6/6 人脸识别
    }

    public void getScreen(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        H = dm.heightPixels;
        W = dm.widthPixels;
    }

    @Override
    public void initializeAllProcessRouter() {
        WideRouter.registerLocalRouter("me.main", MainRouterConnectService.class);
    }

    @Override
    protected void initializeLogic() {
        registerApplicationLogic("me.main", 10001, MainApplicationLogic.class);
    }

    @Override
    public boolean needMultipleProcess() {
        return false;
    }
}
