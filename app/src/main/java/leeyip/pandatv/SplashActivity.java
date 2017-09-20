package leeyip.pandatv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import leeyip.pandatv.utils.SharedPreferenceUtils;
import leeyip.pandatv.utils.ToastUtils;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    Intent intent;
    @BindView(R.id.sd_splash)
    SimpleDraweeView mIvSplash;
    @BindView(R.id.tv_to_main)
    TextView mTvToMain;
    private boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initEvent();
        isFirst = SharedPreferenceUtils.getBooleanData("isFirst", true);
        handler = new Handler();
        handler.postDelayed(() -> {
            if (isFinishing()) {
                return;
            }
            if (isFirst) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
                SharedPreferenceUtils.setBooleanData("isFirst", false);

            } else {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        }, 3000);

    }

    private void initEvent() {
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
        //加载drawable里的一张gif图
                .setUri(Uri.parse("res://" + getPackageName() + "/" + R.drawable.ic_splash_gif_default))//设置uri
                .build();
        //设置Controller
        mIvSplash.setController(mDraweeController);
    }

    @OnClick(R.id.tv_to_main)
    public void onViewClicked() {
        ToastUtils.showShort("what do you mean?");
    }
}
