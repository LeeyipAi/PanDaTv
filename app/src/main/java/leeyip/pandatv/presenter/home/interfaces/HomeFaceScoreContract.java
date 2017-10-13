package leeyip.pandatv.presenter.home.interfaces;

import android.content.Context;

import java.util.List;

import leeyip.pandatv.base.BaseModel;
import leeyip.pandatv.base.BasePresenter;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreColumn;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/12/012.
 */

public interface HomeFaceScoreContract {

    interface View extends BaseView {
        void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumns);

        void getViewFaceScoreColumnLoadMore(List<HomeFaceScoreColumn> homeFaceScoreColumns);
    }

    interface Modle extends BaseModel {
        Observable<List<HomeFaceScoreColumn>> getModleFaceScoreColumn(Context context, int offset, int limit);
    }

    abstract class Presenter extends BasePresenter<View, Modle> {
        //          刷新数据
        public abstract void getPresenterFaceScoreColumn(int offset, int limit);

        //          加载更多
        public abstract void getPresenterFaceScoreLoadMore(int offset, int limit);
    }
}
