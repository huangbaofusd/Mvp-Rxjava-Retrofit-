package com.example.administrator.rxandroidbobo.modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：HBF
 * 创建时间： 2017/8/8 12:31
 */
//相比MvpMoudle获取数据的方式变了
public class MainMoudle2 implements MvpMoudle{

    @Override
    public void getData(OnloadCompletelistener completelistener) {
//这里方式变了,伪装数据放生变化
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("这是更新最后的数据"+i+"条");
        }
        completelistener.onloadComplete(list);
    }
}
