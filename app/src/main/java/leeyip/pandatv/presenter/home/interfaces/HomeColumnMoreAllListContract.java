package leeyip.pandatv.presenter.home.interfaces;

import android.content.Context;

import java.util.List;

import leeyip.pandatv.base.BaseModel;
import leeyip.pandatv.base.BasePresenter;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreAllList;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/18/018.
 */

public interface HomeColumnMoreAllListContract {
    interface View extends BaseView {
        void getViewHomeColumnAllList(List<HomeColumnMoreAllList> mHomeColumnMoreAllList);
        void getViewHomeColumnAllListLoadMore(List<HomeColumnMoreAllList> mHomeColumnMoreAllList);
    }

    interface Model extends BaseModel {

        Observable<List<HomeColumnMoreAllList>> getModelHomeColumnMoreAllList(Context context, String cate_id, int offset, int limit);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 获取全部列表
         */
        public abstract void getPresenterColumnMoreAllList(String cate_id, int offset, int limit);
        /**
         *  加载更多
         */
        public abstract  void getPresenterColumnMoreAllListLoadMore(String cate_id,int offset,int limit);

    }
}
