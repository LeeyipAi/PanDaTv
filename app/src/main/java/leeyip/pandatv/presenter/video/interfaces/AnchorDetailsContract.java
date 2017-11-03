package leeyip.pandatv.presenter.video.interfaces;

import android.content.Context;

import leeyip.pandatv.base.BaseModel;
import leeyip.pandatv.base.BasePresenter;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.video.bean.RoomDetailsInfo;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/31/031.
 */

public interface AnchorDetailsContract {

    interface View extends BaseView {
        void getViewAnchorDetails(RoomDetailsInfo roomDetailsInfo);
    }

    interface Model extends BaseModel {
        Observable<RoomDetailsInfo> getModelAnchorDetails(Context context, String room_id);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getPresenterAnchorDetails(Context context, String room_id);
    }
}
