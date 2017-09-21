package leeyip.pandatv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.zhy.autolayout.AutoFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.ui.NavigateTabBar;
import leeyip.pandatv.ui.home.fragment.HomeFragment;
import leeyip.pandatv.utils.ThemeUtils;
import leeyip.pandatv.utils.ToastUtils;

public class MainActivity extends AppCompatActivity implements BaseView{
    private static final String TAG_PAGE_HOME = "首页";
    private static final String TAG_PAGE_GAME = "游戏";
    private static final String TAG_PAGE_LIVE = "娱乐";
    private static final String TAG_PAGE_DYNAMIC = "精品";
    private static final String TAG_PAGE_MATCH = "比赛";
    protected Unbinder unbinder;

    @BindView(R.id.main_container)
    AutoFrameLayout mMainContainer;
    @BindView(R.id.mainTabBar)

    NavigateTabBar mMainTabBar;
    //    退出时间
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        ThemeUtils.setStatusBarColor(this,R.color.colorPrimary);
        mMainTabBar.onRestoreInstanceState(savedInstanceState);
        mMainTabBar.addTab(HomeFragment.class,new NavigateTabBar.TabParam(R.drawable.home,R.drawable.home_pressed,TAG_PAGE_HOME));
        mMainTabBar.addTab(HomeFragment.class,new NavigateTabBar.TabParam(R.drawable.column,R.drawable.column_pressed,TAG_PAGE_GAME));
        mMainTabBar.addTab(HomeFragment.class,new NavigateTabBar.TabParam(R.drawable.live,R.drawable.live_pressed,TAG_PAGE_LIVE));
        mMainTabBar.addTab(HomeFragment.class,new NavigateTabBar.TabParam(R.drawable.dynamic_tab_normal,R.drawable.dynamic_tab_selected,TAG_PAGE_DYNAMIC));
        mMainTabBar.addTab(HomeFragment.class,new NavigateTabBar.TabParam(R.drawable.match,R.drawable.match_pressed,TAG_PAGE_MATCH));
        mMainTabBar.setTabSelectListener(holder -> {
            switch (holder.tag.toString()) {
//                    首页
                case TAG_PAGE_HOME:
                    mMainTabBar.showFragment(holder);
                    break;
//                    游戏
                case TAG_PAGE_GAME:
                    mMainTabBar.showFragment(holder);
                    break;
//                    直播
                case TAG_PAGE_LIVE:
                    mMainTabBar.showFragment(holder);
                    break;
//                    精品
                case TAG_PAGE_DYNAMIC:
                    mMainTabBar.showFragment(holder);
                    break;
//                    比赛
                case TAG_PAGE_MATCH:
                    if(mMainTabBar!=null)
                        mMainTabBar.showFragment(holder);
                    break;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * 拦截返回键，要求点击两次返回键才退出应用
     *
     * @param keyCode 按键代码
     * @param event   点击事件
     * @return 是否处理本次事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.showShort("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * 保存数据状态
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMainTabBar.onSaveInstanceState(outState);
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }
}
