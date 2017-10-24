package leeyip.pandatv.model.logic.video;

import android.content.Context;

import leeyip.pandatv.presenter.video.interfaces.LiveVideoContract;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/9/22/022.
 */

public class LiveVideoModelLogic implements LiveVideoContract.Model {

    @Override
    public Request getModelPcLiveVideoInfo(Context context, String room_id) {
        String str = "https://m.douyu.com/html5/live?roomId=" + room_id;
        System.out.println(str + "---------------------");
        Request request = new Request.Builder()
                .url(str)
                .get()
                .build();
        return request;
    }

    @Override
    public Request getModelRoomDetails(Context context, String room_id) {
        String path = "http://open.douyucdn.cn/api/RoomApi/room/" + room_id;
        System.out.println(path + "---------------------");
        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        return request;
    }


}
