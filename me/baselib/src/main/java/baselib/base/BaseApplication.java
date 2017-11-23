package baselib.base;

import android.app.Activity;
import android.os.Handler;

import com.spinytech.macore.MaApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 基类Application
 * 作者：wjh on 2017/11/9 11:54
 */
public abstract class BaseApplication extends MaApplication implements Thread.UncaughtExceptionHandler {

    /**
     * 基类Application
     */
    private static BaseApplication baseApplication;
    /**
     * 存活的Activity集合 此处采用 LinkedList作为容器，增删速度快
     */
    private List<Activity> activityList = Collections
            .synchronizedList(new LinkedList<Activity>());
    /**
     * 程序崩溃回调接口
     */
    private UncaughtExceptionCallBack ueBack;

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        Thread.setDefaultUncaughtExceptionHandler(this);// 设置Thread Exception Handler
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (ueBack != null) {
            ueBack.backCall(ex);
        }
    }

    /**
     * 设置崩溃回调
     *
     * @param callBack
     */
    public void setUncaughtExceptionCallBack(UncaughtExceptionCallBack callBack) {
        this.ueBack = callBack;
    }

    /**
     * 获取当前页Activity
     *
     * @return
     * @author wjh
     * @update 2015-6-27 下午3:45:04
     */
    public Activity getCurrentActivity() {
        return activityList.isEmpty() ? null : activityList.get(activityList.size() - 1);
    }

    /**
     * App是否存活
     *
     * @return
     */
    public boolean isActivation() {
        return !(activityList == null || activityList.isEmpty());
    }

    /**
     * 增加Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 删除Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 判断项目是否已经开启，防止点击桌面icon后重启应用
     */
    public final boolean isAlreadyOpen() {
        return activityList.size() > 1;
    }

    /**
     * 延迟关闭之前的所有页面，防止部分手机在关闭所有页面后，程序直接退出，不能直接打开新的页面
     */
    public void clearActivitiesDelay() {
        ArrayList<Activity> oldActivities = new ArrayList<>();
        oldActivities.addAll(activityList);
        activityList.clear();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (Activity activity : oldActivities) {
                    activity.finish();
                }
                oldActivities.clear();
            }
        }, 1000);
    }

    /**
     * 退出程序
     */
    public void onAppExit() {
        clearActivitiesDelay();
    }

    public interface UncaughtExceptionCallBack {
        void backCall(Throwable ex);
    }
}
