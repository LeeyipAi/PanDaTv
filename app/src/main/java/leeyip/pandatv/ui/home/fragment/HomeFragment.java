package leeyip.pandatv.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import leeyip.pandatv.R;
import leeyip.pandatv.Test2Activity;
import leeyip.pandatv.TestActivity;
import leeyip.pandatv.base.BaseFragment;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.home.HomeCateListModelLogic;
import leeyip.pandatv.model.logic.home.bean.HomeCateList;
import leeyip.pandatv.presenter.home.impl.HomeCateListPresenterImp;
import leeyip.pandatv.presenter.home.interfaces.HomeCateListContract;
import leeyip.pandatv.ui.home.adapter.HomeAllListAdapter;
import leeyip.pandatv.ui.video.LiveDetailsActivity;
import leeyip.pandatv.utils.ToastUtils;

/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/11/14 上午11:50
 **/
public class HomeFragment extends BaseFragment<HomeCateListModelLogic, HomeCateListPresenterImp> implements HomeCateListContract
        .View {
    SVProgressHUD svProgressHUD;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_scanner)
    ImageView imgScanner;
    @BindView(R.id.img_history)
    ImageView imgHistory;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private HomeAllListAdapter mAdapter;
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
    public void getHomeAllList(List<HomeCateList> cateLists) {
        new HashMap<>();
        mTitles = new String[cateLists.size() + 1];
        mTitles[0] = "推荐";
        for (int i = 0; i < cateLists.size(); i++) {
            mTitles[i + 1] = cateLists.get(i).getTitle();
        }
        viewpager.setOffscreenPageLimit(mTitles.length);
        mAdapter = new HomeAllListAdapter(getChildFragmentManager(), cateLists, mTitles);
        viewpager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        slidingTab.setViewPager(viewpager, mTitles);
    }

    @Override
    public void showErrorWithStatus(String msg) {
        svProgressHUD.showErrorWithStatus(msg);
    }

    @OnClick({R.id.img_search, R.id.img_scanner, R.id.img_history, R.id.img_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_search:
                ToastUtils.showShort("search");
                startActivity(new Intent(getActivity(), LiveDetailsActivity.class));
                break;
            case R.id.img_scanner:
                ToastUtils.showShort("scanner");
                startActivity(new Intent(getActivity(), TestActivity.class));
                break;
            case R.id.img_history:
                ToastUtils.showShort("history");
                startActivity(new Intent(getActivity(), Test2Activity.class));
                break;
            case R.id.img_message:
                ToastUtils.showShort("message");
                startActivity(new Intent(getActivity(), LiveDetailsActivity.class));
                break;
        }
    }
}
