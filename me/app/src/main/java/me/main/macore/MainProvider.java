package me.main.macore;

import com.spinytech.macore.MaProvider;

/**
 * 供应者 注册Action
 * 作者：wjh on 2017/11/9 11:43
 */
public class MainProvider extends MaProvider {
    @Override
    protected void registerActions() {
        registerAction("A", new AAction());
    }
}
