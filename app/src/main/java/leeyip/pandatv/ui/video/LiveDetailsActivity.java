package leeyip.pandatv.ui.video;

import android.os.Bundle;

import com.bigkoo.svprogresshud.SVProgressHUD;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import leeyip.pandatv.R;
import leeyip.pandatv.base.BaseActivity;
import leeyip.pandatv.base.BaseView;

public class LiveDetailsActivity extends BaseActivity {

    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard mVideoplayer;
    private SVProgressHUD mSvProgressHUD;

    String path = "http://hls3a.douyucdn.cn/live/2046501r3kM7UZzJ_550/playlist.m3u8?wsSecret=f348bb6ad31586d94542e796b2b0cccd&wsTime=1505982321&token=h5-douyu-0-2046501-f305122d521735dd3b2fe5eb150c2080&did=h5_did";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_details;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mSvProgressHUD = new SVProgressHUD(this);
        mVideoplayer.setUp(path, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "直播");
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getView() {
        return null;
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
}
