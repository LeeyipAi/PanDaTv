package leeyip.pandatv.presenter.home.impl;

import java.util.List;

import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreColumn;
import leeyip.pandatv.net.callback.RxSubscriber;
import leeyip.pandatv.net.exception.ResponeThrowable;
import leeyip.pandatv.presenter.home.interfaces.HomeFaceScoreContract;

/**
 * Created by Administrator on 2017/10/12/012.
 */

public class HomeFaceScorePresenterImp extends HomeFaceScoreContract.Presenter {
    @Override
    public void getPresenterFaceScoreColumn(int offset, int limit) {
        addSubscribe(mModel.getModleFaceScoreColumn(mContext, offset, limit).subscribe(new RxSubscriber<List<HomeFaceScoreColumn>>() {
            @Override
            public void onSuccess(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
                mView.getViewFaceScoreColumn(homeFaceScoreColumns);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    @Override
    public void getPresenterFaceScoreLoadMore(int offset, int limit) {
        addSubscribe(mModel.getModleFaceScoreColumn(mContext, offset, limit).subscribe(new RxSubscriber<List<HomeFaceScoreColumn>>() {
            @Override
            public void onSuccess(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
                mView.getViewFaceScoreColumnLoadMore(homeFaceScoreColumns);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
