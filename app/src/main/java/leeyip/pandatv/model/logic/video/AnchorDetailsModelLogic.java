package leeyip.pandatv.model.logic.video;

import android.content.Context;

import leeyip.pandatv.api.video.VideoApi;
import leeyip.pandatv.model.logic.video.bean.RoomDetailsInfo;
import leeyip.pandatv.net.http.HttpUtils;
import leeyip.pandatv.net.transformer.DefaultTransformer;
import leeyip.pandatv.presenter.video.interfaces.AnchorDetailsContract;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/31/031.
 */

public class AnchorDetailsModelLogic implements AnchorDetailsContract.Model {
    @Override
    public Observable<RoomDetailsInfo> getModelAnchorDetails(Context context, String room_id) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(VideoApi.class)
                .getRoomDetailsInfo(room_id)
                .compose(new DefaultTransformer<RoomDetailsInfo>());
    }
}
