package leeyip.pandatv.presenter.video.interfaces;

import android.content.Context;

import leeyip.pandatv.base.BaseModel;
import leeyip.pandatv.base.BasePresenter;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.video.bean.RoomDetailsInfo;
import leeyip.pandatv.model.logic.video.bean.TempLiveVideoInfo;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/9/22/022.
 */

public interface LiveVideoContract {
    interface View extends BaseView {
        void getViewPcLiveVideoInfo(TempLiveVideoInfo mLiveVideoInfo);
        void getRoomDetails(RoomDetailsInfo baseResponse);
    }

    interface Model extends BaseModel {
        Request getModelPcLiveVideoInfo(Context context, String room_id);
        Request getModelRoomDetails(Context context, String room_id);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getPresenterPcLiveVideoInfo(String room_id);
        public abstract void getPresenterRoomDetails(String room_id);
    }
}
