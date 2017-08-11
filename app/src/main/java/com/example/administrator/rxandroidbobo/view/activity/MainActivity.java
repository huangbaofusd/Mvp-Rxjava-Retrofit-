package com.example.administrator.rxandroidbobo.view.activity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.rxandroidbobo.R;
import com.example.administrator.rxandroidbobo.presenter.MainMcPresenter;
import com.example.administrator.rxandroidbobo.view.iview.MvpView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 类描述：
 * 创建人：HBF
 * 创建时间：2017/8/2 8:44
 * modle主管事物的处理，用于获取数据，view主管更新页面，用于展示内容，presenter用于沟通MV
 */
public class MainActivity extends BaseActivity<MvpView,MainMcPresenter>  implements MvpView{

    private ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listview);
//      view和moudle产生了耦合关系有驳理念
//        new MainMcPresenter(this).setModle(0).load();//1@创建presenter，调用内部的加载数据
         basePresenter.setModle(1).load();
//      需要通过某种方式获取数据源，然后设置给Listview
//        Button button= (Button) findViewById(R.id.btn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("对比lambda","====");
//            }
//        });
//       只有一个实现的方法才可以替代，v形参任何值都可以，后面是最终要执行的方法
//        button.setOnClickListener(v-> Log.e("对比lamda","===="+v));
//        observer();
//        subScriber();
//          action();
//          range();
//        just();
//        defer();
//        from();
//        fromList();
//        interval();
//        repeat();
//        flatMap();
    }

    @Override
    public MainMcPresenter getBasePresenter() {
        return new MainMcPresenter();
    }

    private void flatMap(){//可以进行类型转换
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        Observable<String> observable1 = observable.flatMap(integer -> Observable.just("转换" + integer));
        observable1.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
        Log.e("flatmap","==ddgdfwe==="+s);
            }
        });

    }
    private void repeat(){//有限循环,就一直循环1,2,3这三个数字
        Observable.just(1,2,3).repeat(10).subscribe(integer -> Log.e("repeat","==dad=="+integer));
    }
    private void interval(){//对外发送数组，每隔多久发送一次,功能类似于handler发送延时消息，不是在主线程里面运行
        Subscription subscribe = Observable.interval(3, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {//无限循环
          Log.e("interval","===add==="+aLong);
            }
        });
    }
    private void fromList(){
        List<String> stringList=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add(""+i);
        }
        Observable.from(stringList).subscribe(s -> Log.e("3344","==da==="+s));
    }
    private  void from(){//将数组或者可迭代的对象内容取出来一个一个发射出来
        String[] strings={"1","2","3","4","asd","qweqdw"};
        Observable.from(strings).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
            Log.e("from","====adad===="+s);
            }
        });

    }
//    功能实时刷新
    private void  defer(){//延时操作，只有创建订阅者并且订阅之后才会创建才会创建ObserVable
//        Func0 func0=new Func0() {
//            @Override
//            public Object call() {
//                return "111111";
//            }
//        };
        Observable.defer(()->Observable.just(1,2,3,4)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
            Log.e("defer","==dafqrqws==="+integer);
            }
        });
    }
    private void just(){//just里面的参数一个个拿出来之后对外发射,just内部可以是数字，字符串等任意内容
        Subscription subscribe = Observable.just("a", "b", "c").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("just数据","==just=="+s);
            }
        });
    }
    private void range() {
        Subscription subscribe = Observable.range(1, 10).subscribe(integer -> Log.e("自定义", "lambda"+integer));
    }

    private void action(){
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
               subscriber.onNext("1");

            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
               Log.d("adc","==action=="+s);

            }
        });

/**
 * 类描述：
 * 创建人：HBF
 * 创建时间：2017/7/27 16:49
 * (Observable<String>) 这个方法应该是不安全的，新版本已经弃用了
 */
//        Observable<String>observable= (Observable<String>) Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                 subscriber.onNext("action1的方法");
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                 Log.e("自定义的","action1的方法"+s);
//            }
//        });
    }
    private void subScriber() {
        Observable<String> observable=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {//参数就是订阅者
                //被观察者的数据发生变化的时候发射
                if (!subscriber.isUnsubscribed()){//如果仍处于订阅状态，才会通知观察者
                    subscriber.onNext("数据发生变化了");
                }
            }
        });
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("自定义","onSubscriber"+s);
            }
        });

    }

    private void observer() {
        Observable<String> observable=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {//参数就是订阅者
                //被观察者的数据发生变化的时候发射
                if (!subscriber.isUnsubscribed()){//如果仍处于订阅状态，才会通知观察者
                    subscriber.onNext("数据发生变化了");
                }
            }
        });
        observable.subscribe(new Observer<String>() {//订阅
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("自定义","onNext=="+s);
            }
        });
    }

//  在这里数据传回来 ，更新界面
    @Override
    public void showView(List<String> list) {
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(arrayAdapter);
    }
}
