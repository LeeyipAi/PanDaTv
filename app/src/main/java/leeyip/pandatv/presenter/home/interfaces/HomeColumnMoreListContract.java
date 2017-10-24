package leeyip.pandatv.presenter.home.interfaces;

import android.content.Context;

import java.util.List;

import leeyip.pandatv.base.BaseModel;
import leeyip.pandatv.base.BasePresenter;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreTwoCate;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/18/018.
 */

public interface HomeColumnMoreListContract {
    interface View extends BaseView {
        void getViewHomeColumnMoreTwoCate(List<HomeColumnMoreTwoCate> mHomeColumnMoreTwoCate);
    }

    interface Model extends BaseModel {
        Observable<List<HomeColumnMoreTwoCate>> getModelHomeColumnMoreTwoCate(Context context, String cate_id);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        /**
         *  获取栏目 更多 二级分类
         */
        public abstract void getPresenterHomeColumnMoreTwoCate(String cate_id);
    }
}
