package leeyip.pandatv.ui.video;

import android.os.Bundle;

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

public class LiveDetailsActivity extends BaseActivity<LiveVideoModelLogic, LiveVideoPresenter> implements LiveVideoContract.View {

    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard mVideoplayer;
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
