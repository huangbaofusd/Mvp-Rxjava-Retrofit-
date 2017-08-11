package com.example.administrator.rxandroidbobo.modle;

import java.util.List;

/**
 * 类描述：
 * 创建人：HBF
 * 创建时间： 2017/8/8 10:05
 */
//用于获取数据的模型层
public interface MvpMoudle {
//    专门用于获取数据
        void  getData(OnloadCompletelistener completelistener);//获取数据的方法（上网，异步的一般需要通过接口回调传回来）

    public interface OnloadCompletelistener{

        void  onloadComplete(List<String> list);//这个是请求完成的回调，携带数据回来
    }
}
