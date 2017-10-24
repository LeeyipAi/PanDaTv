package leeyip.pandatv;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import leeyip.pandatv.ui.home.fragment.RecommendHomeFragment;

public class Test2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return viewType == 0 ? new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content, parent, false)) : new TwoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (position == 1) {
                bindTab((TwoViewHolder) holder);
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
        }

        private void bindTab(TwoViewHolder holder) {
            holder.mViewPager.setAdapter(new Mapapter(getSupportFragmentManager()));
            holder.mTabLayout.setupWithViewPager(holder.mViewPager);
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }

        public class TwoViewHolder extends RecyclerView.ViewHolder {
            public TabLayout mTabLayout;
            public ViewPager mViewPager;

            public TwoViewHolder(View itemView) {
                super(itemView);
                mTabLayout = (TabLayout) itemView.findViewById(R.id.tab);
                mViewPager = (ViewPager) itemView.findViewById(R.id.viewpager);
            }
        }
    }

    class Mapapter extends FragmentStatePagerAdapter {

        public Mapapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new RecommendHomeFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position ==  0 ? "A" : "B";
        }
    }
}

