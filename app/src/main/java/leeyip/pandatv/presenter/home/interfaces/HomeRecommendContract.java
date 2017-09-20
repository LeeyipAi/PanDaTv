package leeyip.pandatv.presenter.home.interfaces;

import android.content.Context;

import java.util.List;

import leeyip.pandatv.base.BaseModel;
import leeyip.pandatv.base.BasePresenter;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.home.bean.HomeCarousel;
import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreColumn;
import leeyip.pandatv.model.logic.home.bean.HomeHotColumn;
import leeyip.pandatv.model.logic.home.bean.HomeRecommendHotCate;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/20/020.
 */

public interface HomeRecommendContract {

    interface View extends BaseView {
        //        轮播图
        void getViewCarousel(List<HomeCarousel> mHomeCarousel);

        //        最热栏目
        void getViewHotColumn(List<HomeHotColumn> mHomeHotColumn);

        //        颜值栏目
        void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumns);

        //       热门栏目
        void getViewHotCate(List<HomeRecommendHotCate> homeRecommendHotCates);
    }

    interface Model extends BaseModel {
        Observable<List<HomeCarousel>> getModelCarousel(Context context);

        Observable<List<HomeHotColumn>> getModelHotColumn(Context context);

        Observable<List<HomeFaceScoreColumn>> getModelFaceScoreColumn(Context context,int offset,int limit  );

        Observable<List<HomeRecommendHotCate>> getModelHotCate(Context context);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        //        轮播
        public abstract void getPresenterCarousel();

        //        最热栏目
        public abstract void getPresenterHotColumn();

        public abstract void getPresenterFaceScoreColumn(int offset,int limit );

        public abstract void getPresenterHotCate();

    }
}
