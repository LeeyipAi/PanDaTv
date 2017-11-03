package leeyip.pandatv.ui.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import leeyip.pandatv.model.logic.video.bean.RoomDetailsInfo;
import leeyip.pandatv.ui.video.fragment.AnchorFragment;
import leeyip.pandatv.ui.video.fragment.ChatFragment;

/**
 * Created by Administrator on 2017/10/31/031.
 */

public class RoomPagerAdapter extends FragmentPagerAdapter {

    String[] mTitles;
    String roomId;
    RoomDetailsInfo baseResponse;

    public RoomPagerAdapter(FragmentManager fm , String[] mTitles,String roomId) {
        super(fm);
        this.mTitles = mTitles;
        this.roomId = roomId;
    }

    @Override
    public Fragment getItem(int position) {
        return position ==  1 ? new AnchorFragment(roomId) : new ChatFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    public void getRommDetails(RoomDetailsInfo baseResponse) {
        this.baseResponse = baseResponse;
    }
}
