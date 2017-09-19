package leeyip.pandatv.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bigkoo.svprogresshud.SVProgressHUD;

import java.util.ArrayList;
import java.util.List;

import leeyip.pandatv.R;
import leeyip.pandatv.base.BaseFragment;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.home.HomeCateListModelLogic;
import leeyip.pandatv.model.logic.home.bean.HomeCateList;
import leeyip.pandatv.presenter.home.impl.HomeCateListPresenterImp;
import leeyip.pandatv.presenter.home.interfaces.HomeCateListContract;
import leeyip.pandatv.utils.L;

/**
 * Created by Administrator on 2017/9/19/019.
 */

public class HomeFragment extends BaseFragment<HomeCateListModelLogic, HomeCateListPresenterImp> implements HomeCateListContract.View {
    SVProgressHUD svProgressHUD;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        svProgressHUD = new SVProgressHUD(getActivity());
    }

    @Override
    protected void onEvent() {
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.getHomeCateList();
    }

    @Override
    public void showErrorWithStatus(String msg) {
        svProgressHUD.showErrorWithStatus(msg);
    }

    @Override
    public void getHomeAllList(List<HomeCateList> cateLists) {
        L.d(cateLists.toString());
    }
}
