package leeyip.pandatv.presenter.video.impl;

import android.content.Context;

import leeyip.pandatv.model.logic.video.bean.RoomDetailsInfo;
import leeyip.pandatv.net.callback.RxSubscriber;
import leeyip.pandatv.net.exception.ResponeThrowable;
import leeyip.pandatv.presenter.video.interfaces.AnchorDetailsContract;

/**
 * Created by Administrator on 2017/10/31/031.
 */

public class AnchorDetilsPresenter extends AnchorDetailsContract.Presenter {

    @Override
    public void getPresenterAnchorDetails(Context context,String room_id) {
        addSubscribe(mModel.getModelAnchorDetails(context,room_id).subscribe(new RxSubscriber<RoomDetailsInfo>() {
            @Override
            public void onSuccess(RoomDetailsInfo roomDetailsInfo) {
                mView.getViewAnchorDetails(roomDetailsInfo);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
