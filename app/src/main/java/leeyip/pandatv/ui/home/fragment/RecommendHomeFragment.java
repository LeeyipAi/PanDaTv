package leeyip.pandatv.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import leeyip.pandatv.R;
import leeyip.pandatv.base.BaseFragment;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.home.HomeRecommendModelLogic;
import leeyip.pandatv.model.logic.home.bean.HomeCarousel;
import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreColumn;
import leeyip.pandatv.model.logic.home.bean.HomeHotColumn;
import leeyip.pandatv.model.logic.home.bean.HomeRecommendHotCate;
import leeyip.pandatv.presenter.home.impl.HomeRecommendPresenterImp;
import leeyip.pandatv.presenter.home.interfaces.HomeRecommendContract;
import leeyip.pandatv.ui.home.adapter.HomeCarouselAdapter;
import leeyip.pandatv.ui.home.adapter.HomeRecommendAdapter;
import leeyip.pandatv.ui.video.LiveDetailsActivity;

/**
 * Created by Administrator on 2017/9/20/020.
 */

public class RecommendHomeFragment extends BaseFragment<HomeRecommendModelLogic, HomeRecommendPresenterImp> implements BGABanner.Delegate<SimpleDraweeView, String>, HomeRecommendContract.View, SwipeRefreshLayout.OnRefreshListener {

    SVProgressHUD svProgressHUD;
    @BindView(R.id.recommend_content_recyclerview)
    RecyclerView mRecommendContentRecyclerview;
    @BindView(R.id.recommend_srefresh)
    SwipeRefreshLayout mRecommendSrefresh;
    private List<HomeCarousel> mHomeCarousel;
    private HomeRecommendAdapter mAdapter;
    private HomeCarouselAdapter mRecommedBannerAdapter;
    private BGABanner mBanner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        svProgressHUD = new SVProgressHUD(getActivity());
        mRecommendContentRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HomeRecommendAdapter(mContext);
        mHomeCarousel = new ArrayList<HomeCarousel>();
        pool.setMaxRecycledViews(mAdapter.getItemViewType(0), 500);
        mRecommendContentRecyclerview.setRecycledViewPool(pool);
        mRecommendContentRecyclerview.setAdapter(mAdapter);
        mRecommedBannerAdapter = new HomeCarouselAdapter();
        mRecommendSrefresh.setOnRefreshListener(this);
        mRecommendSrefresh.setColorSchemeResources(R.color.comui_tab_select_color);
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.item_home_recommend_banner, mRecommendContentRecyclerview, false);
        mAdapter.setHeaderView(headerView);
        mBanner = (BGABanner) headerView.findViewById(R.id.recommed_banner);
        mBanner.setDelegate(this);
        mBanner.setAdapter(mRecommedBannerAdapter);
    }

    final RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool() {
        @Override
        public void putRecycledView(RecyclerView.ViewHolder scrap) {
            super.putRecycledView(scrap);
        }

        @Override
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            final RecyclerView.ViewHolder recycledView = super.getRecycledView(viewType);
            return recycledView;
        }
    };

    @Override
    protected void onEvent() {
        mRecommendSrefresh.post(() -> mRecommendSrefresh.setRefreshing(true));
        onRefresh();
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    protected void lazyFetchData() {
        refresh();
    }

    @Override
    public void showErrorWithStatus(String msg) {
        svProgressHUD.showErrorWithStatus(msg);
        mRecommendSrefresh.setRefreshing(false);
    }

    @Override
    public void getViewCarousel(List<HomeCarousel> mHomeCarousel) {
        if (mRecommendSrefresh != null) {
            mRecommendSrefresh.setRefreshing(false);
        }
        this.mHomeCarousel.clear();
        this.mHomeCarousel.addAll(mHomeCarousel);
//        recommed_banner.setDelegate(this);
        ArrayList<String> pic_url = new ArrayList<String>();
        for (int i = 0; i < mHomeCarousel.size(); i++) {
            pic_url.add(mHomeCarousel.get(i).getPic_url());
        }
        if (mBanner != null && pic_url.size() > 0) {
            mBanner.setData(R.layout.item_image_carousel, pic_url, null);
        }
        mAdapter.notifyDataSetChanged();
    }

    //最热
    @Override
    public void getViewHotColumn(List<HomeHotColumn> mHomeHotColumn) {
        mAdapter.getHomeHotColumn(mHomeHotColumn);
    }

    //颜值
    @Override
    public void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
        mAdapter.getFaceScoreColmun(homeFaceScoreColumns);
    }

    //热门
    @Override
    public void getViewHotCate(List<HomeRecommendHotCate> homeRecommendHotCates) {
//        去掉颜值栏目
        homeRecommendHotCates.remove(1);
        mAdapter.getAllColumn(homeRecommendHotCates);
    }

    /**
     * 刷新网络数据
     */
    private void refresh() {
//        轮播图
        mPresenter.getPresenterCarousel();
        mPresenter.getPresenterHotColumn();
        mPresenter.getPresenterFaceScoreColumn(0, 4);
        mPresenter.getPresenterHotCate();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> {
            refresh();
        }, 500);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, SimpleDraweeView itemView, String model, int position) {
        Intent intent = new Intent(getActivity(), LiveDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Room_id", mHomeCarousel.get(position).getRoom().getRoom_id());
        bundle.putString("Room_name", mHomeCarousel.get(position).getRoom().getRoom_name());
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }
}
