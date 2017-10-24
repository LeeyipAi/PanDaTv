package leeyip.pandatv.presenter.home.impl;


import java.util.List;

import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreOtherList;
import leeyip.pandatv.net.callback.RxSubscriber;
import leeyip.pandatv.net.exception.ResponeThrowable;
import leeyip.pandatv.presenter.home.interfaces.HomeColumnMoreOtherListContract;

public class HomeColumnMoreOtherListPresenterImp extends HomeColumnMoreOtherListContract.Presenter {
    @Override
    public void getPresenterColumnMoreOtherList(String cate_id, int offset, int limint) {
        addSubscribe(mModel.getModelHomeColumnMoreOtherList(mContext, cate_id, offset, limint).subscribe(new RxSubscriber<List<HomeColumnMoreOtherList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreOtherList> mHomeColumnMoreOtherList) {
                mView.getViewHomeColumnOtherList(mHomeColumnMoreOtherList);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));

    }

    @Override
    public void getPresenterColumnMoreOtherListLoadMore(String cate_id, int offset, int limit) {
        addSubscribe(mModel.getModelHomeColumnMoreOtherList(mContext, cate_id, offset, limit).subscribe(new RxSubscriber<List<HomeColumnMoreOtherList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreOtherList> mHomeColumnMoreAllList) {
                mView.getViewHomeColumnOtherListLoadMore(mHomeColumnMoreAllList);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
