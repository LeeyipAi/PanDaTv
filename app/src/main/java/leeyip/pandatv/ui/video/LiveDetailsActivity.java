package leeyip.pandatv.ui.video;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.bigkoo.svprogresshud.SVProgressHUD;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import leeyip.pandatv.R;
import leeyip.pandatv.base.BaseActivity;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.video.LiveVideoModelLogic;
import leeyip.pandatv.model.logic.video.bean.TempLiveVideoInfo;
import leeyip.pandatv.presenter.video.impl.LiveVideoPresenter;
import leeyip.pandatv.presenter.video.interfaces.LiveVideoContract;
import leeyip.pandatv.ui.video.adapter.RoomPagerAdapter;
import leeyip.pandatv.utils.TabLayoutHelper;

public class LiveDetailsActivity extends BaseActivity<LiveVideoModelLogic, LiveVideoPresenter> implements LiveVideoContract.View {

    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard mVideoplayer;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private SVProgressHUD mSvProgressHUD;
    private String Room_id;
    private String Room_name;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_details;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mSvProgressHUD = new SVProgressHUD(this);
        Room_id = getIntent().getExtras().getString("Room_id");
        Room_name = getIntent().getExtras().getString("Room_name");
        mPresenter.getPresenterPcLiveVideoInfo(Room_id);
        initTab();
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;  //纵向
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getView() {
        return this;
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    /**
     * 初始化Tab
     */
    private void initTab() {
        RoomPagerAdapter roomPagerAdapter = new RoomPagerAdapter(getSupportFragmentManager(), new String[]{"聊天", "主播", "排行", "车站"},Room_id);
        mVp.setAdapter(roomPagerAdapter);
        mTab.setupWithViewPager(mVp);
        mTab.postDelayed(() -> TabLayoutHelper.setIndicator(mTab, LiveDetailsActivity.this, 20, 20), 1);
    }

    @Override
    public void showErrorWithStatus(String msg) {
        runOnUiThread(() -> mSvProgressHUD.showErrorWithStatus("主播还在赶来的路上~~"));
    }

    @Override
    public void getViewPcLiveVideoInfo(TempLiveVideoInfo mLiveVideoInfo) {
        String url = mLiveVideoInfo.getData().getHls_url();
        runOnUiThread(() -> {
            mVideoplayer.setUp(url, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, Room_name);
            mVideoplayer.startVideo();
        });

    }
}
