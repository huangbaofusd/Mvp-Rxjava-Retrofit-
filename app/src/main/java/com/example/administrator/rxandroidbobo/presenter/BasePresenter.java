package com.example.administrator.rxandroidbobo.presenter;

import com.example.administrator.rxandroidbobo.view.iview.MvpView;

import java.lang.ref.WeakReference;

/**
 * 类描述：
 * 创建人：HBF
 * 创建时间： 2017/8/8 15:50
 */

public class BasePresenter<V extends MvpView> {
//    在做BaseActivity的时候MvpView需要的数据类型太多，所以写了个V泛型
//    弱引用
    private WeakReference<V> weakReference;
    public void attach(V mvpView){
        weakReference=new WeakReference(mvpView);
    }
    public void deAttach(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference=null;
        }
    }
    public V getView(){
        return  weakReference.get();
    }
}
