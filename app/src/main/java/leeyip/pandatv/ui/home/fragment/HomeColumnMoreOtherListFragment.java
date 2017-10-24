package leeyip.pandatv.ui.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigkoo.svprogresshud.SVProgressHUD;

import java.util.List;

import butterknife.BindView;
import leeyip.pandatv.R;
import leeyip.pandatv.base.BaseFragment;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.home.HomeColumnMoreOtherListModelLogic;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreOtherList;
import leeyip.pandatv.presenter.home.impl.HomeColumnMoreOtherListPresenterImp;
import leeyip.pandatv.presenter.home.interfaces.HomeColumnMoreOtherListContract;
import leeyip.pandatv.ui.home.adapter.HomeColumnMoreOtherListAdapter;

/**
 * Created by Administrator on 2017/10/19/019.
 */

public class HomeColumnMoreOtherListFragment extends BaseFragment<HomeColumnMoreOtherListModelLogic,HomeColumnMoreOtherListPresenterImp> implements SwipeRefreshLayout.OnRefreshListener,HomeColumnMoreOtherListContract.View {
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.sr)
    SwipeRefreshLayout mSr;

    private String cate_id;

    SVProgressHUD svProgressHUD;

    private HomeColumnMoreOtherListAdapter mHomeColumnMoreAllListAdapter;

    boolean isLoading;

    boolean flag = false;

    boolean loadmore = false;

    //    起始位置
    private int offset = 0;
    //    每页加载数量
    private int limit = 20;

    public static HomeColumnMoreOtherListFragment getInstance(String cate_id) {
        HomeColumnMoreOtherListFragment rf = new HomeColumnMoreOtherListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cate_id", cate_id);
        rf.setArguments(bundle);
        return rf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_column_more_all_list;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        Bundle arguments = getArguments();
        cate_id = arguments.getString("cate_id");
        svProgressHUD = new SVProgressHUD(getActivity());
        mSr.setOnRefreshListener(this);
        mSr.setColorSchemeResources(R.color.comui_tab_select_color);
        mHomeColumnMoreAllListAdapter = new HomeColumnMoreOtherListAdapter(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == mHomeColumnMoreAllListAdapter.getItemCount() - 1 ? 2 : 1;
            }
        });
        mRv.setLayoutManager(gridLayoutManager);
        mRv.setAdapter(mHomeColumnMoreAllListAdapter);
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == mHomeColumnMoreAllListAdapter.getItemCount()) {
                    boolean isRefresh = mSr.isRefreshing();
                    if (isRefresh) {
                        mHomeColumnMoreAllListAdapter.notifyItemRemoved(mHomeColumnMoreAllListAdapter.getItemCount());
                        return;
                    }
                    mHomeColumnMoreAllListAdapter.setVisibility();
                    if (!isLoading && loadmore) {
                        isLoading = true;
                        new Handler().postDelayed(() -> {
                            if (flag){
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
        mSr.post(() -> mSr.setRefreshing(true));
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

    private void refresh() {
        offset = 0;
        mPresenter.getPresenterColumnMoreOtherList(cate_id,offset,20);
    }

    private void loadMore(int offset, int limit) {
        mPresenter.getPresenterColumnMoreOtherListLoadMore(cate_id, offset, limit);
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> {
            if (!flag) {
                refresh();
            }
        }, 500);
    }

    @Override
    public void onDestroy() {
        flag = true;
        super.onDestroy();
    }

    @Override
    public void showErrorWithStatus(String msg) {
        svProgressHUD.showErrorWithStatus(msg);
        if (mSr != null) {
            mSr.setRefreshing(false);
        }
    }

    @Override
    public void getViewHomeColumnOtherList(List<HomeColumnMoreOtherList> mHomeColumnMoreOtherList) {
        mHomeColumnMoreAllListAdapter.getLiveAllList(mHomeColumnMoreOtherList);
        if (mSr != null) {
            mSr.setRefreshing(false);
        }
        loadmore = true;
    }

    @Override
    public void getViewHomeColumnOtherListLoadMore(List<HomeColumnMoreOtherList> mHomeColumnMoreOtherList) {
        if (mSr != null) {
            mSr.setRefreshing(false);
        }
        if (mHomeColumnMoreOtherList == null) {
            loadmore = false;
        }
        mHomeColumnMoreAllListAdapter.getLiveAllListLoadMore(mHomeColumnMoreOtherList);
    }
}
