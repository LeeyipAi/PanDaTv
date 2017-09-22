package leeyip.pandatv.presenter.home.interfaces;

import android.content.Context;

import java.util.List;

import leeyip.pandatv.base.BaseModel;
import leeyip.pandatv.base.BasePresenter;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.home.bean.HomeCateList;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/19/019.
 */
public interface HomeCateListContract {
    interface View extends BaseView {
        void getHomeAllList(List<HomeCateList> cateLists);
    }

    interface Model extends BaseModel {
        Observable getHomeCateList(Context context);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHomeCateList();
    }
}