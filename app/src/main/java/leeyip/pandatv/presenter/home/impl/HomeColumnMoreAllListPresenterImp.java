package leeyip.pandatv.presenter.home.impl;

import java.util.List;

import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreAllList;
import leeyip.pandatv.net.callback.RxSubscriber;
import leeyip.pandatv.net.exception.ResponeThrowable;
import leeyip.pandatv.presenter.home.interfaces.HomeColumnMoreAllListContract;

/**
 * Created by Administrator on 2017/10/18/018.
 */

public class HomeColumnMoreAllListPresenterImp extends HomeColumnMoreAllListContract.Presenter {
    @Override
    public void getPresenterColumnMoreAllList(String cate_id, int offset, int limit) {
        addSubscribe(mModel.getModelHomeColumnMoreAllList(mContext, cate_id, offset, limit).subscribe(new RxSubscriber<List<HomeColumnMoreAllList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreAllList> homeColumnMoreAllLists) {
                mView.getViewHomeColumnAllList(homeColumnMoreAllLists);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    @Override
    public void getPresenterColumnMoreAllListLoadMore(String cate_id, int offset, int limit) {
        addSubscribe(mModel.getModelHomeColumnMoreAllList(mContext, cate_id, offset, limit).subscribe(new RxSubscriber<List<HomeColumnMoreAllList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreAllList> homeColumnMoreAllLists) {
                mView.getViewHomeColumnAllListLoadMore(homeColumnMoreAllLists);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.getMessage());
            }
        }));
    }
}
