package leeyip.pandatv.ui.home.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import leeyip.pandatv.R;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.base.SwipBackActivity;
import leeyip.pandatv.model.logic.home.HomeColimnMoreListModelLogic;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreTwoCate;
import leeyip.pandatv.presenter.home.impl.HomeColumnMoreListImp;
import leeyip.pandatv.presenter.home.interfaces.HomeColumnMoreListContract;
import leeyip.pandatv.ui.home.adapter.HomeColumnMoreTowCateAdapter;
import leeyip.pandatv.utils.ThemeUtils;

/**
 * Created by Administrator on 2017/10/18/018.
 */

public class HomeColumnMoreListActivity extends SwipBackActivity<HomeColimnMoreListModelLogic, HomeColumnMoreListImp> implements HomeColumnMoreListContract.View {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_popup_live)
    ImageView mImgPopupLive;
    @BindView(R.id.rl_bar)
    RelativeLayout mRlBar;
    @BindView(R.id.twocolumn_tablayout)
    SlidingTabLayout mTwocolumnTablayout;
    @BindView(R.id.rl_twocolumn_bar)
    RelativeLayout mRlTwocolumnBar;
    @BindView(R.id.livetwocolumn_viewpager)
    ViewPager mLivetwocolumnViewpager;

    private SVProgressHUD mProgressHUD;

    HomeColumnMoreTowCateAdapter mHomeColumnMoreTowCateAdapter;

    private String[] mTilte;

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_home_more_list;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        ThemeUtils.setStatusBarColor(this, R.color.colorPrimary);
        mProgressHUD = new SVProgressHUD(this);
        mTvTitle.setText(getIntent().getExtras().getString("title"));
        mPresenter.getPresenterHomeColumnMoreTwoCate(getIntent().getExtras().getString("cate_id"));
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getView() {
        return this;
    }

    @Override
    public void showErrorWithStatus(String msg) {
        mProgressHUD.showErrorWithStatus("网络错误,请重试");
    }

    @OnClick(R.id.img_back)
    public void back() {
        finish();
    }

    @Override
    public void getViewHomeColumnMoreTwoCate(List<HomeColumnMoreTwoCate> mHomeColumnMoreTwoCate) {
        mTilte = new String[mHomeColumnMoreTwoCate.size() + 1];
        mTilte[0] = "全部";
        for (int i = 0; i < mHomeColumnMoreTwoCate.size(); i++) {
            mTilte[i + 1] = mHomeColumnMoreTwoCate.get(i).getName();
        }
        if (mTilte.length <= 1) {
            mRlTwocolumnBar.setVisibility(View.GONE);
        }
        mLivetwocolumnViewpager.setOffscreenPageLimit(mTilte.length);
        mHomeColumnMoreTowCateAdapter = new HomeColumnMoreTowCateAdapter(getSupportFragmentManager(), getIntent().getExtras().getString("cate_id"), mHomeColumnMoreTwoCate, mTilte);
        mLivetwocolumnViewpager.setAdapter(mHomeColumnMoreTowCateAdapter);
        mHomeColumnMoreTowCateAdapter.notifyDataSetChanged();
        mTwocolumnTablayout.setViewPager(mLivetwocolumnViewpager, mTilte);
    }
}
