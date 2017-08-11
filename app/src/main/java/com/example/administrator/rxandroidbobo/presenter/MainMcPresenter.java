package com.example.administrator.rxandroidbobo.presenter;

import com.example.administrator.rxandroidbobo.modle.MainAcMoudle;
import com.example.administrator.rxandroidbobo.modle.MainMoudle2;
import com.example.administrator.rxandroidbobo.modle.MvpMoudle;
import com.example.administrator.rxandroidbobo.view.iview.MvpView;

import java.util.List;

/**
 * 类描述：
 * 创建人：HBF
 * 创建时间： 2017/8/8 10:53
 */
//用于协调view和moudle的中间类
public class MainMcPresenter extends BasePresenter<MvpView> {

    private MvpView mvpView;//获得数据后需要更新视图的view,***现在是Activity的对象
    private MvpMoudle mvpMoudle;//用于获取数据的moudle

//    moudle就不用传了，有一个具体的实现类,优于构造方法
//    public MainMcPresenter(MvpView mvpView) {
//        this.mvpView = mvpView;
//        mvpMoudle=new MainAcMoudle();
//    }

    public MainMcPresenter() {
        mvpMoudle=new MainAcMoudle();
    }

    public MainMcPresenter setModle(int modle){
       switch (modle){
           case 0:
               mvpMoudle=new MainAcMoudle();
               break;
           case 1:
               mvpMoudle=new MainMoudle2();
               break;
       }

        return this;
    }
//    2@加载数据，内部通过modle加载
    public void load(){
         mvpMoudle.getData(new MvpMoudle.OnloadCompletelistener() {
             @Override
             public void onloadComplete(List<String> list) {
//加载完成后数据会传送到这里
//                 mvpView.showView(list);//3@
                 getView().showView(list);//3@

             }
         });
    }
}
