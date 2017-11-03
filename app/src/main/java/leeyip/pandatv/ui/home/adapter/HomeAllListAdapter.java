package leeyip.pandatv.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import leeyip.pandatv.model.logic.home.bean.HomeCateList;
import leeyip.pandatv.ui.home.fragment.RecommendHomeFragment;

/**
 * Created by Administrator on 2017/9/20/020.
 */

public class HomeAllListAdapter extends FragmentPagerAdapter {

    private List<HomeCateList> mHomeCateLists;
    private String[] mTiltle;
    private FragmentManager mFragmentManager;

    public HomeAllListAdapter(FragmentManager mFragmentManager, List<HomeCateList> mHomeCateLists, String[] mTitle) {
        super(mFragmentManager);
        this.mFragmentManager = mFragmentManager;
        this.mHomeCateLists = mHomeCateLists;
        this.mTiltle = mTitle;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTiltle[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new RecommendHomeFragment();
        }else{
            return new RecommendHomeFragment();
        }
    }

    @Override
    public int getCount() {
        return mTiltle.length;
    }
}
