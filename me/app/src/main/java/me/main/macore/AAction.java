package me.main.macore;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.spinytech.macore.MaAction;
import com.spinytech.macore.MaActionResult;

import java.util.HashMap;

/**
 * 行为 实现动作
 * 作者：wjh on 2017/11/9 16:17
 */
public class AAction extends MaAction{
    @Override
    public boolean isAsync(Context context, HashMap<String, String> requestData) {
        return false;
    }

    @Override
    public MaActionResult invoke(Context context, HashMap<String, String> requestData) {
        String temp = "";
        if(!TextUtils.isEmpty(requestData.get("1"))){
            temp+=requestData.get("1");
        }
        if(!TextUtils.isEmpty(requestData.get("2"))){
            temp+=requestData.get("2");
        }
        Toast.makeText(context, "AAction.invoke:"+temp, Toast.LENGTH_SHORT).show();

        return new MaActionResult.Builder()
                .code(MaActionResult.CODE_SUCCESS)
                .msg("我是A的返回者")
                .data(temp)
                .build();
    }
}
