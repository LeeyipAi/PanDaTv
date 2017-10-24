package leeyip.pandatv.ui.home.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import leeyip.pandatv.R;
import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreColumn;
import leeyip.pandatv.ui.video.LiveDetailsActivity;
import leeyip.pandatv.utils.CalculationUtils;


public class HomeRecommendFaceScoreColumnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeFaceScoreColumn> mHomeFaceScoreColumn;
    private Context context;
    private View view;
    private FootViewHolder mFootViewHolder;
    boolean isBottom = false;

    public HomeRecommendFaceScoreColumnAdapter(Context context) {
        this.context = context;
        this.mHomeFaceScoreColumn = new ArrayList<HomeFaceScoreColumn>();
    }

    public HomeRecommendFaceScoreColumnAdapter(Context context, View view) {
        this.context = context;
        this.mHomeFaceScoreColumn = new ArrayList<HomeFaceScoreColumn>();
        this.view = view;
    }

    public void getFaceScoreColumn(List<HomeFaceScoreColumn> mHomeFaceScoreColumn) {
        this.mHomeFaceScoreColumn.clear();
        this.mHomeFaceScoreColumn.addAll(mHomeFaceScoreColumn);
        notifyDataSetChanged();
    }

    public void getFaceScoreColumnLoadMore(List<HomeFaceScoreColumn> mHomeFaceScoreColumn) {
        if (mHomeFaceScoreColumn.isEmpty()) {
            isBottom = true;
        }else {
            isBottom = false;
        }
        this.mHomeFaceScoreColumn.addAll(mHomeFaceScoreColumn);
        notifyDataSetChanged();
    }

    private void bindFaceScoreHolder(FaceScoreColumnHolder holder, int position) {
        int position1 = position;
        if (view != null) {
            position1 = position - 1;
        }
        holder.img_item_gridview.setImageURI(Uri.parse(mHomeFaceScoreColumn.get(position1).getVertical_src()));
        holder.tv_column_item_nickname.setText(mHomeFaceScoreColumn.get(position1).getNickname());
        holder.tv_online_num.setText(CalculationUtils.getOnLine(mHomeFaceScoreColumn.get(position1).getOnline()));
        holder.tv_facescore_city.setText(mHomeFaceScoreColumn.get(position1).getAnchor_city());
//        holder.img_item_gridview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, PhoneLiveVideoActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("Room_id", mHomeFaceScoreColumn.get(position).getRoom_id());
//                bundle.putString("Img_Path", mHomeFaceScoreColumn.get(position).getVertical_src());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });

        holder.img_item_gridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LiveDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Room_id", mHomeFaceScoreColumn.get(view == null ? position : position - 1).getRoom_id());
                bundle.putString("Room_name", mHomeFaceScoreColumn.get(view == null ? position : position - 1).getRoom_name());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount() && view != null) {
            return 2;
        } else {
            return view == null ? super.getItemViewType(position) : position == 0 ? 1 : super.getItemViewType(position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 2) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent,
                    false);
            mFootViewHolder = new FootViewHolder(view);
            return mFootViewHolder;
        } else {
            return viewType != 1 ? new FaceScoreColumnHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend_facescore, parent, false)) : new BannerViewHolder(view);
        }
    }

    boolean flag = true;

    public void setVisibility() {
        flag = false;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FaceScoreColumnHolder) {
            bindFaceScoreHolder((FaceScoreColumnHolder) holder, position);
        } else if (holder instanceof FootViewHolder) {
            if (isBottom) {
                ((FootViewHolder) holder).mProgressBar.setVisibility(View.INVISIBLE);
                if (mHomeFaceScoreColumn.size() <= 12) {
                    //条目小于12就将加载更多条目隐藏
                    ((FootViewHolder) holder).view.setVisibility(View.GONE);
                } else {
                    ((FootViewHolder) holder).tv.setText("我是有底线的");
                }
            }else {
                ((FootViewHolder) holder).view.setVisibility(flag ? View.INVISIBLE : View.VISIBLE);
            }
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return view == null ? mHomeFaceScoreColumn.size() : mHomeFaceScoreColumn.size() + 2;
    }

    static class FaceScoreColumnHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView img_item_gridview;
        public TextView tv_column_item_nickname;
        public TextView tv_online_num;
        public TextView tv_facescore_city;

        public FaceScoreColumnHolder(View view) {
            super(view);
            img_item_gridview = (SimpleDraweeView) view.findViewById(R.id.img_item_gridview);
            tv_column_item_nickname = (TextView) view.findViewById(R.id.tv_column_item_nickname);
            tv_online_num = (TextView) view.findViewById(R.id.tv_online_num);
            tv_facescore_city = (TextView) view.findViewById(R.id.tv_facescore_city);
        }
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {

        public BannerViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ProgressBar mProgressBar;
        public TextView tv;

        public FootViewHolder(View view) {
            super(view);
            this.view = view;
            mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
