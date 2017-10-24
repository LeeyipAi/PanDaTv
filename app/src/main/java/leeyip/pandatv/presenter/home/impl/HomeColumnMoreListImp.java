package leeyip.pandatv.presenter.home.impl;

import java.util.List;

import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreTwoCate;
import leeyip.pandatv.net.callback.RxSubscriber;
import leeyip.pandatv.net.exception.ResponeThrowable;
import leeyip.pandatv.presenter.home.interfaces.HomeColumnMoreListContract;

/**
 * Created by Administrator on 2017/10/18/018.
 */

public class HomeColumnMoreListImp extends HomeColumnMoreListContract.Presenter {

    @Override
    public void getPresenterHomeColumnMoreTwoCate(String cate_id) {
        addSubscribe(mModel.getModelHomeColumnMoreTwoCate(mContext,cate_id).subscribe(new RxSubscriber<List<HomeColumnMoreTwoCate>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreTwoCate> homeColumnMoreTwoCates) {
                mView.getViewHomeColumnMoreTwoCate(homeColumnMoreTwoCates);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.getMessage());
            }
        }));
    }
}
