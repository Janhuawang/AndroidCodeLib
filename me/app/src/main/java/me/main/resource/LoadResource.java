package me.main.resource;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.main.R;

/**
 * 本地资源
 * 作者：wjh on 2017/11/28 17:45
 */
public class LoadResource {

    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();

    public static void init(Context context) {
        String[] urls = context.getResources().getStringArray(R.array.url);
        String[] tips = context.getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        List list1 = Arrays.asList(tips);
        images = new ArrayList(list);
        titles = new ArrayList(list1);
    }
}
