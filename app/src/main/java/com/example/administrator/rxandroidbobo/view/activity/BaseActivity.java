package com.example.administrator.rxandroidbobo.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.rxandroidbobo.presenter.BasePresenter;
import com.example.administrator.rxandroidbobo.presenter.MainMcPresenter;
import com.example.administrator.rxandroidbobo.view.iview.MvpView;

import java.util.List;

/**
 * 类描述：
 * 创建人：HBF
 * 创建时间： 2017/8/10 9:42
 */

public abstract class BaseActivity<V extends MvpView,T extends BasePresenter<V>> extends AppCompatActivity {

    public T basePresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter=getBasePresenter();
        basePresenter.attach((V) this);//最终的 this 代表是具体的子类，
        // 子类在继承的时候必须实现MvpView
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenter.deAttach();
    }

    public abstract T getBasePresenter() ;

//    @Override
//    public void showView(List<String> list) {
//
//    }
}
