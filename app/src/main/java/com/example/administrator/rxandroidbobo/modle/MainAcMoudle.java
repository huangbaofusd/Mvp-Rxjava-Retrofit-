package com.example.administrator.rxandroidbobo.modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：HBF
 * 创建时间： 2017/8/8 10:42
 */
//专门给MainActivity获取数据的类
public class MainAcMoudle implements MvpMoudle {

    @Override
    public void getData(OnloadCompletelistener completelistener) {
//        获取数据
//        伪装网络请求
        List<String> list=new ArrayList<>();//数据
        for (int i = 0; i < 100; i++) {
            list.add("这是第"+i+"条数据");
        }
        if (completelistener!=null){
            completelistener.onloadComplete(list);
        }
    }
}
