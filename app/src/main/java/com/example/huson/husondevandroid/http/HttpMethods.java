package com.example.huson.husondevandroid.http;


import com.example.huson.husondevandroid.bean.BaseBean;
import com.example.huson.husondevandroid.bean.GirlBean;
import com.example.huson.husondevandroid.bean.WeatherinfoBean;
import com.example.mylibrary.utils.DebugLog;

import org.json.JSONArray;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by huson on 2016/5/20.
 * 940762301@qq.com
 */
public class HttpMethods {
//    public static final String BASE_URL = "http://192.168.0.70:8080/safety_app/";
    public static final String BASE_URL = "http://gank.io/";
    private static final int DEFAULT_TIMEOUT = 1;
    private Retrofit retrofit;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.DAYS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<BaseBean<T>, T> {

        @Override
        public T call(BaseBean<T> httpResult) {
//            if (httpResult.returnCode == 505) {
//                DebugLog.w(httpResult.returnInfo);
//                throw new ApiException(httpResult.returnInfo);
//            }
            return httpResult.returnData;
        }
    }

    public void checkVersion(Subscriber<String> subscriber, String keyCode){
        HttpInterfaces.CheckVersion versionService = retrofit.create(HttpInterfaces.CheckVersion.class);
        Observable observable = versionService.checkVersion(keyCode)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable, subscriber);
    }

    public void downLoad(Callback<ResponseBody> callback){
        HttpInterfaces.DownLoadAPK downLoadAPK = retrofit.create(HttpInterfaces.DownLoadAPK.class);
        retrofit2.Call<ResponseBody> call = downLoadAPK.getFile();
        call.enqueue(callback);
   }

    public void reportSave(Subscriber<BaseBean> subscriber, String code, String KeyCode, String lineName, String accidenDate,
                           String accidentTime, String driverName, String vehiceCode, String geogName, String accidentPlace, Integer dutyType, Integer injuredNumber,
                           Integer deadNumber, JSONArray list){
        HttpInterfaces.ReportSave reportSaveService = retrofit.create(HttpInterfaces.ReportSave.class);
        Observable observable = reportSaveService.reportSave(code, KeyCode, lineName, accidenDate,
                accidentTime, driverName,vehiceCode,geogName,accidentPlace,dutyType,injuredNumber, deadNumber, list)
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    public void loadData(Subscriber<WeatherinfoBean> subscriber, String city){
        HttpInterfaces.LoadData loadData = retrofit.create(HttpInterfaces.LoadData.class);
        Observable observable = loadData.loadData(city)
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }

    public void getGirls(Subscriber<List<GirlBean.ResultsEntity>> subscriber, String type, int count, int page){
        HttpInterfaces.GirlsService girlsService = retrofit.create(HttpInterfaces.GirlsService.class);
        Observable observable = girlsService.getGirls(type, count, page)
                .map(new Func1<GirlBean, List<GirlBean.ResultsEntity>>() {
                    @Override
                    public List<GirlBean.ResultsEntity> call(GirlBean girlBean) {
                        return girlBean.results;
                    }

                });
        toSubscribe(observable, subscriber);
    }


}
