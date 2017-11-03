package leeyip.pandatv.ui.video.fragment;


import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import leeyip.pandatv.R;
import leeyip.pandatv.base.BaseFragment;
import leeyip.pandatv.base.BaseView;
import leeyip.pandatv.model.logic.video.AnchorDetailsModelLogic;
import leeyip.pandatv.model.logic.video.bean.RoomDetailsInfo;
import leeyip.pandatv.presenter.video.impl.AnchorDetilsPresenter;
import leeyip.pandatv.presenter.video.interfaces.AnchorDetailsContract;

/**
 * Created by Administrator on 2017/10/31/031.
 */

public class AnchorFragment extends BaseFragment<AnchorDetailsModelLogic, AnchorDetilsPresenter> implements AnchorDetailsContract.View {
    //房间号
    String roomId;
    @BindView(R.id.iv)
    SimpleDraweeView mIv;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv2)
    TextView mTv2;
    SVProgressHUD mSvProgressHUD;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.tv4)
    TextView mTv4;
    @BindView(R.id.iv_lv)
    ImageView mIvLv;

    public AnchorFragment(String roomId) {
        this.roomId = roomId;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_anchor;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mSvProgressHUD = new SVProgressHUD(getActivity());
        mPresenter.getPresenterAnchorDetails(mContext, roomId);
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    public void showErrorWithStatus(String msg) {
        mSvProgressHUD.showErrorWithStatus(msg);
    }

    boolean flag = false;
    boolean once = true;
    int lines;

    @Override
    public void getViewAnchorDetails(RoomDetailsInfo roomDetailsInfo) {
        mIv.setImageURI(Uri.parse(roomDetailsInfo.getAvatar()));
        mTv1.setText(roomDetailsInfo.getOwner_name());
        mTv2.setText("房间ID: " + roomDetailsInfo.getRoom_id());
        mTv3.setText("体重: " + roomDetailsInfo.getOwner_weight() + "  粉丝: " + roomDetailsInfo.getFans_num());
        mTv4.setText(roomDetailsInfo.getRoom_name());
        initLevel(roomDetailsInfo.getOwner_weight());
        Drawable drawable = getResources().getDrawable(R.drawable.arrow_down_host);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        ViewTreeObserver vto = mTv4.getViewTreeObserver();
        vto.addOnPreDrawListener(() -> {
            lines = mTv4.getLineCount();
            if (lines == 1 && once) {
                mTv4.setClickable(false);
                mTv4.setCompoundDrawables(null,null,null,null);
            }else if (lines != 1 && once){
                mTv4.setClickable(true);
                mTv4.setMaxLines(1);
                mTv4.setCompoundDrawables(null, null, drawable, null);
                once = false;
            }
            return true;
        });

        mTv4.setOnClickListener(v -> {
            if (flag) {
                mTv4.setMaxLines(1);
                mTv4.setCompoundDrawables(null, null, drawable, null);
            } else {
                mTv4.setCompoundDrawables(null, null, null, null);
                mTv4.setMaxLines(1000);
            }
            flag = !flag;
        });
    }

    private void initLevel(String owner_weight) {
        for (int i = 0; i < owner_weight.length(); i++) {
            if ('a' <= owner_weight.charAt(i) && owner_weight.charAt(i)<= 'z') {
                String substring = owner_weight.substring(i);
                int j = owner_weight.indexOf(".");
                String weight = owner_weight.substring(0, j);
                Log.e("ADASDASD",Integer.parseInt(weight) +"");
                showLevel(substring,Integer.parseInt(weight));
                return ;
            }
        }
    }

    private void showLevel(String substring,int weight) {
        if (substring.equals("g")) {
            mIvLv.setImageResource(R.drawable.host_avatar_level_0);
        }else if (substring.equals("kg")){
            mIvLv.setImageResource(R.drawable.host_avatar_level_1);
        }else {
            switch (weight / 3) {
                case 0:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_2);
                    break;
                case 1:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_3);
                    break;
                case 2:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_4);
                    break;
                case 3:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_5);
                    break;
                case 4:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_6);
                    break;
                case 5:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_7);
                    break;
                case 6:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_8);
                    break;
                case 7:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_9);
                    break;
                case 8:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_10);
                    break;
                case 9:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_11);
                    break;
                case 10:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_12);
                    break;
                case 11:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_13);
                    break;
                case 12:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_14);
                    break;
                case 13:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_15);
                    break;
                case 14:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_16);
                    break;
                case 15:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_17);
                    break;
                case 16:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_18);
                    break;
                case 17:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_19);
                    break;
                default:
                    mIvLv.setImageResource(R.drawable.host_avatar_level_20);
                    break;
            }
        }

    }

}
