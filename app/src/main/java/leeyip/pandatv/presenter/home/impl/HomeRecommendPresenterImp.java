package leeyip.pandatv.presenter.home.impl;

import java.util.List;

import leeyip.pandatv.model.logic.home.bean.HomeCarousel;
import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreColumn;
import leeyip.pandatv.model.logic.home.bean.HomeHotColumn;
import leeyip.pandatv.model.logic.home.bean.HomeRecommendHotCate;
import leeyip.pandatv.net.callback.RxSubscriber;
import leeyip.pandatv.net.exception.ResponeThrowable;
import leeyip.pandatv.presenter.home.interfaces.HomeRecommendContract;

/**
 * Created by Administrator on 2017/9/20/020.
 */

public class HomeRecommendPresenterImp extends HomeRecommendContract.Presenter {

    /**
     * 推荐轮播图
     *
     */
    @Override
    public void getPresenterCarousel() {
        addSubscribe(mModel.getModelCarousel(mContext).subscribe(new RxSubscriber<List<HomeCarousel>>() {
            @Override
            public void onSuccess(List<HomeCarousel> homeCarousels) {
                mView.getViewCarousel(homeCarousels);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    @Override
    public void getPresenterHotColumn() {
        addSubscribe(mModel.getModelHotColumn(mContext).subscribe(new RxSubscriber<List<HomeHotColumn>>() {
            @Override
            public void onSuccess(List<HomeHotColumn> homeHotColumns) {
                mView.getViewHotColumn(homeHotColumns);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    @Override
    public void getPresenterFaceScoreColumn(int offset, int limit) {
        addSubscribe(mModel.getModelFaceScoreColumn(mContext,offset,limit).subscribe(new RxSubscriber<List<HomeFaceScoreColumn>>() {
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
    public void getPresenterHotCate() {
        addSubscribe(mModel.getModelHotCate(mContext).subscribe(new RxSubscriber<List<HomeRecommendHotCate>>() {
            @Override
            public void onSuccess(List<HomeRecommendHotCate> homeRecommendHotCates) {
                mView.getViewHotCate(homeRecommendHotCates);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
