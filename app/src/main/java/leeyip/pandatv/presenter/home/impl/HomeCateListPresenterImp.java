package leeyip.pandatv.presenter.home.impl;

import java.util.List;

import leeyip.pandatv.model.logic.home.bean.HomeCateList;
import leeyip.pandatv.net.callback.RxSubscriber;
import leeyip.pandatv.net.exception.ResponeThrowable;
import leeyip.pandatv.presenter.home.interfaces.HomeCateListContract;

/**
 * Created by Administrator on 2017/9/19/019.
 */

public class HomeCateListPresenterImp extends HomeCateListContract.Presenter {

    @Override
    public void getHomeCateList() {
        addSubscribe(mModel.getHomeCateList(mContext).subscribe(new RxSubscriber<List<HomeCateList>>() {
            @Override
            public void onSuccess(List<HomeCateList> homeCateListList) {
                mView.getHomeAllList(homeCateListList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}

