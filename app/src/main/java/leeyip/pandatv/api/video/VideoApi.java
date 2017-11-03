package leeyip.pandatv.api.video;

import leeyip.pandatv.model.logic.video.bean.RoomDetailsInfo;
import leeyip.pandatv.net.response.HttpResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/31/031.
 */

public interface VideoApi {

    //"http://open.douyucdn.cn/api/RoomApi/room/" + room_id;

    @GET("http://open.douyucdn.cn/api/RoomApi/room/" + "{room_id}")
    Observable<HttpResponse<RoomDetailsInfo>> getRoomDetailsInfo(@Path("room_id") String room_id);
}
