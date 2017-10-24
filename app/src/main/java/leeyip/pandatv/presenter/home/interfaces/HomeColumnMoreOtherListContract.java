package leeyip.pandatv.presenter.home.interfaces;

import android.content.Context;

import java.util.List;

import leeyip.pandatv.base.BaseModel;
import leeyip.pandatv.base.BasePresenter;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreOtherList;
import rx.Observable;


public interface HomeColumnMoreOtherListContract {
    interface View extends BaseView {
        void getViewHomeColumnOtherList(List<HomeColumnMoreOtherList> mHomeColumnMoreOtherList);
        void getViewHomeColumnOtherListLoadMore(List<HomeColumnMoreOtherList> mHomeColumnMoreOtherList);
    }

    interface Model extends BaseModel {

        Observable<List<HomeColumnMoreOtherList>> getModelHomeColumnMoreOtherList(Context context, String cate_id, int offset, int limit);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 获取全部列表
         */
        public abstract void getPresenterColumnMoreOtherList(String cate_id, int offset, int limit);
        /**
         *  加载更多
         */
        public abstract  void getPresenterColumnMoreOtherListLoadMore(String cate_id,int offset,int limit);

    }
}
