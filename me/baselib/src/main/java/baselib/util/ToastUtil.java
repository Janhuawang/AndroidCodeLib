package baselib.util;

import android.content.Context;
import android.widget.Toast;

import baselib.base.BaseApplication;

/**
 * Toast弹出工具
 *
 * @author wjh
 */
public class ToastUtil {

    private static Context activity = BaseApplication.getBaseApplication();
    private static Toast toast = null;

    /**
     * 吐司显示信息
     *
     * @param text
     */
    public static void showText(Object text) {
        show(text, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示吐司
     *
     * @param text
     */
    public static void showTextLong(Object text) {
        show(text, Toast.LENGTH_LONG);
    }

    private static void show(Object text, int duration) {
        if (text == null)
            return;
        if (toast != null) {
            toast.cancel();
        }
        if (text instanceof Integer) {
            toast = Toast.makeText(activity, (Integer) text, duration);
        } else {
            String s = text.toString();
            if (s.trim().length() > 0) {
                toast = Toast.makeText(activity, s, duration);
            }
        }
        if (toast != null) {
            toast.show();
        }
    }

    /**
     * 吐司显示信息 自定义位置
     *
     * @param text
     * @param gravity
     */
    public static void showText(Object text, int gravity) {
        if (text == null)
            return;
        if (toast != null) {
            toast.cancel();
        }
        if (text instanceof Integer) {
            toast = Toast
                    .makeText(activity, (Integer) text, Toast.LENGTH_SHORT);
        } else {
            String s = text.toString();
            if (s.trim().length() > 0) {
                toast = Toast.makeText(activity, s, Toast.LENGTH_SHORT);
            }
        }
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

}
