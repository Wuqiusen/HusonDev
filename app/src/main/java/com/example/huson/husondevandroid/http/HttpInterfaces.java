package com.example.huson.husondevandroid.http;


import com.example.huson.husondevandroid.bean.BaseBean;
import com.example.huson.husondevandroid.bean.GirlBean;
import com.example.huson.husondevandroid.mvp.modle.MainModel;

import org.json.JSONArray;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by huson on 2016/5/23.
 * 940762301@qq.com
 */
public class HttpInterfaces {
    /**
     * 上传URL例子
     */
    public interface CheckVersion {
        @POST("phone/new/version")
        Observable<BaseBean<String>> checkVersion(@Query("keyCode") String keyCode);
    }

    /**
     * 下载更新包
     */
    public interface DownLoadAPK {
        @GET("http://driver.szebus.net/driver_ebus/phone/app/version/update?fileName=eBusDriver1.2.3.apk")
        @Streaming
        Call<ResponseBody> getFile();
    }


    /**
     * 上窗对象例子
     */
    public interface ReportSave {
        @FormUrlEncoded
        @POST("accident/phone/report/save")
        Observable<BaseBean> reportSave(@Field("code") String code, @Field("keyCode") String keyCode,
                                        @Field("lineId") String lineId, @Field("accidenDate") String accidenDate,
                                        @Field("accidentTime") String accidentTime, @Field("driverId") String personnelId,
                                        @Field("vehiceId") String vehicleId, @Field("geogName") String geogName,
                                        @Field("accidentPlace") String accidentPlace, @Field("dutyType") Integer dutyType,
                                        @Field("injuredNumber") Integer injuredNumber, @Field("deadNumber") Integer deadNumber,
                                        @Field("accidentObj") JSONArray list);
    }

    public interface LoadData{

        //加载天气
        @GET("adat/sk/{cityId}.html")
        Observable<MainModel> loadData(@Path("cityId") String cityId);
    }

    //baseUrl
    String API_SERVER_URL = "http://www.weather.com.cn/";

    public interface GirlsService {

        @GET("api/data/{type}/{count}/{page}")
        Observable<GirlBean> getGirls(
                @Path("type") String type,
                @Path("count") int count,
                @Path("page") int page
        );

    }


}
