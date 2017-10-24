package leeyip.pandatv.ui.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import leeyip.pandatv.R;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.base.SwipBackActivity;
import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreColumn;
import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreLogic;
import leeyip.pandatv.presenter.home.impl.HomeFaceScorePresenterImp;
import leeyip.pandatv.presenter.home.interfaces.HomeFaceScoreContract;
import leeyip.pandatv.ui.home.adapter.HomeCarouselAdapter;
import leeyip.pandatv.ui.home.adapter.HomeRecommendFaceScoreColumnAdapter;
import leeyip.pandatv.ui.video.LiveDetailsActivity;
import leeyip.pandatv.utils.ThemeUtils;

public class HomeRecommendFaceScoreActivity extends SwipBackActivity<HomeFaceScoreLogic, HomeFaceScorePresenterImp> implements HomeFaceScoreContract.View, BGABanner.Delegate, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.facescore_content_recyclerview)
    RecyclerView mFacescoreContentRecyclerview;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<HomeFaceScoreColumn> mHomeFaceScoreColumn = new ArrayList<>();
    HomeRecommendFaceScoreColumnAdapter mAdapter;
    BGABanner mBanner;
    SVProgressHUD svProgressHUD;
    int limit = 20;
    int offset = 0;
    boolean isLoading;
    boolean loadmore = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_recommend_face_score;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        svProgressHUD = new SVProgressHUD(this);
        ThemeUtils.setStatusBarColor(this, R.color.colorPrimary);
        mTvTitle.setText(getIntent().getExtras().getString("title"));
        GridLayoutManager lm = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        lm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == mAdapter.getItemCount() - 1) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        mFacescoreContentRecyclerview.setLayoutManager(lm);
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_home_recommend_banner, mFacescoreContentRecyclerview, false);
        mAdapter = new HomeRecommendFaceScoreColumnAdapter(this, headerView);
        mFacescoreContentRecyclerview.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.comui_tab_select_color);
        HomeCarouselAdapter mRecommedBannerAdapter = new HomeCarouselAdapter();
        mBanner = (BGABanner) headerView.findViewById(R.id.recommed_banner);
        mBanner.setDelegate(this);
        mBanner.setAdapter(mRecommedBannerAdapter);
        mFacescoreContentRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                    boolean isRefresh = mSwipeRefreshLayout.isRefreshing();
                    if (isRefresh) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }
                    mAdapter.setVisibility();
                    if (!isLoading && loadmore) {
                        isLoading = true;
                        new Handler().postDelayed(() -> {
                            if (flag) {
                                return;
                            }
                            offset += limit;
                            loadMore(offset, limit);
                            isLoading = false;
                        }, 1000);
                    }
                }
            }
        });
    }

    @Override
    protected void onEvent() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
    }

    /**
     * 刷新网络数据
     */
    private void refresh() {
//       重新开始计算
        offset = 0;
        mPresenter.getPresenterFaceScoreColumn(0, 20);
    }

    @Override
    protected BaseView getView() {
        return this;
    }

    @Override
    public void showErrorWithStatus(String msg) {
        svProgressHUD.showErrorWithStatus(msg);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        this.mHomeFaceScoreColumn.addAll(homeFaceScoreColumns);
        ArrayList pic = new ArrayList();
        ArrayList name = new ArrayList();
        for (int i = 0; i < 5; i++) {
            int x = new Random().nextInt(8) + new Random().nextInt(8);
            pic.add(homeFaceScoreColumns.get(x).getRoom_src());
            name.add(homeFaceScoreColumns.get(x).getRoom_name());
        }
        mBanner.setData(R.layout.item_image_carousel, pic, name);
        mAdapter.getFaceScoreColumn(homeFaceScoreColumns);
        loadmore = true;
    }

    @Override
    public void getViewFaceScoreColumnLoadMore(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (homeFaceScoreColumns == null) {
            loadmore = false;
        }
        mAdapter.getFaceScoreColumnLoadMore(homeFaceScoreColumns);
    }

    @OnClick(R.id.img_back)
    public void back() {
        finish();
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
        Intent intent = new Intent(this, LiveDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Room_id", mHomeFaceScoreColumn.get(position).getRoom_id());
        bundle.putString("Room_name", mHomeFaceScoreColumn.get(position).getRoom_name());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void loadMore(int offset, int limit) {
        mPresenter.getPresenterFaceScoreLoadMore(offset, limit);
    }

    boolean flag = false;

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> {
            if (!flag) {
                refresh();
            }
        }, 500);
    }

    @Override
    protected void onDestroy() {
        flag = true;
        super.onDestroy();
    }
}
