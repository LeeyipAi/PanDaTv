package leeyip.pandatv.ui.video.fragment;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import leeyip.pandatv.R;
import leeyip.pandatv.base.BaseFragment;
import leeyip.pandatv.base.BaseView;

/**
 * Created by Administrator on 2017/10/31/031.
 */

public class ChatFragment extends BaseFragment {

    @BindView(R.id.barrage)
    TextView mBarrage;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;
    @BindView(R.id.rl_login)
    RelativeLayout mRlLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        SpannableString spannableString = new SpannableString(mBarrage.getText());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        spannableString.setSpan(colorSpan, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mBarrage.setText(spannableString);

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

    @OnClick(R.id.iv_delete)
    public void onViewClicked() {
        mRlLogin.setVisibility(View.GONE);
    }
}
