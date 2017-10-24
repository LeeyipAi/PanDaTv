package leeyip.pandatv.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import leeyip.pandatv.R;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreAllList;
import leeyip.pandatv.ui.video.LiveDetailsActivity;
import leeyip.pandatv.utils.CalculationUtils;

/**
 * Created by Administrator on 2017/10/18/018.
 */

public class HomeColumnMoreAllListAdapter extends RecyclerView.Adapter {

    private List<HomeColumnMoreAllList> mLiveList;
    private Context context;
    boolean isBottom = false;

    private static final int COMMONTYPE = 0;
    private static final int FOOTTYPE = 1;

    public HomeColumnMoreAllListAdapter(Context context) {
        this.context = context;
        this.mLiveList = new ArrayList<HomeColumnMoreAllList>();
    }

    public void getLiveAllList(List<HomeColumnMoreAllList> mLiveList) {
        this.mLiveList.clear();
        this.mLiveList.addAll(mLiveList);
        notifyDataSetChanged();
    }

    public void getLiveAllListLoadMore(List<HomeColumnMoreAllList> mLiveList) {
        if (mLiveList.isEmpty()) {
            isBottom = true;
        } else {
            isBottom = false;
        }
        if (this.mLiveList.isEmpty()) {
            flag = true;
        }else {
            flag = false;
        }
        this.mLiveList.addAll(mLiveList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position == mLiveList.size() ? FOOTTYPE : COMMONTYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case COMMONTYPE:
                return new LiveAllListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend_view, parent, false));
            case FOOTTYPE:
                return new HomeRecommendFaceScoreColumnAdapter.FootViewHolder(LayoutInflater.from(context).inflate(R.layout.item_foot, parent, false));
            default:
                return null;
        }

    }

    boolean flag = true;

    public void setVisibility() {
        flag = false;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeRecommendFaceScoreColumnAdapter.FootViewHolder) {
            if (isBottom) {
                ((HomeRecommendFaceScoreColumnAdapter.FootViewHolder) holder).mProgressBar.setVisibility(View.GONE);
                if (mLiveList.size() <= 10) {
                    //条目小于12就将加载更多条目隐藏
                    ((HomeRecommendFaceScoreColumnAdapter.FootViewHolder) holder).view.setVisibility(View.GONE);
                } else {
                    ((HomeRecommendFaceScoreColumnAdapter.FootViewHolder) holder).tv.setText("我是有底线的");
                }
            }else {
                ((HomeRecommendFaceScoreColumnAdapter.FootViewHolder) holder).view.setVisibility(flag ? View.INVISIBLE : View.VISIBLE);
            }
        } else {
            bindLiveAll((LiveAllListHolder) holder, position);
        }
    }

    private void bindLiveAll(LiveAllListHolder holder, int position) {
        holder.img_item_gridview.setImageURI(Uri.parse(mLiveList.get(position).getVertical_src()));
        holder.tv_column_item_nickname.setText(mLiveList.get(position).getRoom_name());
        holder.tv_nickname.setText(mLiveList.get(position).getNickname());
        holder.tv_online_num.setText(CalculationUtils.getOnLine(mLiveList.get(position).getOnline()));
        holder.tv_game_name.setText(mLiveList.get(position).getGame_name());
        if (mLiveList.get(position).getCate_id() == 201) {
            holder.rl_live_icon.setBackgroundResource(R.drawable.search_header_live_type_mobile);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  颜值栏目 竖屏播放
                if (mLiveList.get(position).getCate_id() == 201) {
                    Intent intent = new Intent(context, LiveDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Room_id", mLiveList.get(position).getRoom_id());
                    bundle.putString("Room_name", mLiveList.get(position).getRoom_name());
                    bundle.putString("Img_Path", mLiveList.get(position).getVertical_src());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                } else if (mLiveList.get(position).getCate_id() == 207) {
//                    Intent intent = new Intent(context, WebViewActivity.class);
//                    intent.putExtra("web_url", mLiveList.get(position).getJumpUrl()+"?from=dy");
//                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, LiveDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Room_id", mLiveList.get(position).getRoom_id());
                    bundle.putString("Room_name", mLiveList.get(position).getRoom_name());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLiveList.size() + 1;
    }


    public class LiveAllListHolder extends BaseViewHolder {
        //        图片
        public SimpleDraweeView img_item_gridview;
        //        房间名称
        public TextView tv_column_item_nickname;
        //        在线人数
        public TextView tv_online_num;
        //        昵称
        public TextView tv_nickname;
        //        Icon
        public RelativeLayout rl_live_icon;
        //        游戏名称
        public TextView tv_game_name;

        public LiveAllListHolder(View view) {
            super(view);
            img_item_gridview = (SimpleDraweeView) view.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname = (TextView) view.findViewById(R.id.tv_column_item_nickname);
            tv_online_num = (TextView) view.findViewById(R.id.tv_online_num);
            tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
            tv_game_name = (TextView) view.findViewById(R.id.tv_game_name);
            rl_live_icon = (RelativeLayout) view.findViewById(R.id.rl_live_icon);
        }
    }
}
