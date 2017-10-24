package leeyip.pandatv.ui.mine.fragment;

import android.os.Bundle;

import butterknife.BindView;
import leeyip.pandatv.R;
import leeyip.pandatv.base.BaseFragment;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.ui.mine.view.ArcHeaderView;

/**
 * Created by Administrator on 2017/10/16/016.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.header_view)
    ArcHeaderView mHeaderView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mHeaderView.setColor(getResources().getColor(R.color.start_color),getResources().getColor(R.color.end_color));
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getViewImp() {
        return null;
    }

    @Override
    protected void lazyFetchData() {

    }

}
