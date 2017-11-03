package leeyip.pandatv.presenter.video.impl;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import leeyip.pandatv.model.logic.video.bean.TempLiveVideoInfo;
import leeyip.pandatv.presenter.video.interfaces.LiveVideoContract;
import leeyip.pandatv.utils.L;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/22/022.
 */

public class LiveVideoPresenter extends LiveVideoContract.Presenter {
    @Override
    public void getPresenterPcLiveVideoInfo(String room_id) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(mModel.getModelPcLiveVideoInfo(mContext, room_id)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("error", e.getMessage() + "---");
                L.e("错误信息:" + e.getMessage());
                mView.showErrorWithStatus(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string().toString();
                Log.e("onResponse", json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.getInt("error") == 0) {
                        Gson gson = new Gson();
                        TempLiveVideoInfo mLiveVideoInfo = gson.fromJson(json, TempLiveVideoInfo.class);
                        mView.getViewPcLiveVideoInfo(mLiveVideoInfo);
                    } else {
                        mView.showErrorWithStatus("获取数据失败!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
