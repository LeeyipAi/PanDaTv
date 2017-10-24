package leeyip.pandatv.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import leeyip.pandatv.R;
import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreColumn;
import leeyip.pandatv.model.logic.home.bean.HomeHotColumn;
import leeyip.pandatv.model.logic.home.bean.HomeRecommendHotCate;
import leeyip.pandatv.ui.home.FullyGridLayoutManager;
import leeyip.pandatv.ui.home.activity.HomeColumnMoreListActivity;
import leeyip.pandatv.ui.home.activity.HomeRecommendFaceScoreActivity;

/**
 * Created by Administrator on 2017/9/20/020.
 */

public class HomeRecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //    最热栏目
    private List<HomeHotColumn> mHomeHotColumn;
    //    颜值栏目
    private List<HomeFaceScoreColumn> mHomeFaceScoreColumn;
    private Context context;

    //    最热adapter
    private HomeRecommendHotColumnAdapter mHotColumnAdapter;
    //    颜值
    private HomeRecommendFaceScoreColumnAdapter mFaceScoreColumnAdapter;
    //    全部栏目
    private List<HomeRecommendHotCate> mHomeRecommendHotCate;
    private HomeRecommendAllColumnAdapter mAllColumnAdapter;


    /**
     * 指定 type类型
     */
    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;

    //头布局
    private View mHeaderView;

    public HomeRecommendAdapter(Context context) {
        this.context = context;
        mHomeHotColumn = new ArrayList<HomeHotColumn>();
        mHomeFaceScoreColumn = new ArrayList<HomeFaceScoreColumn>();
        mHomeRecommendHotCate = new ArrayList<HomeRecommendHotCate>();
        mFaceScoreColumnAdapter = new HomeRecommendFaceScoreColumnAdapter(context);
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    /**
     * 最热栏目
     *
     * @param mHomeHotColumn
     */
    public void getHomeHotColumn(List<HomeHotColumn> mHomeHotColumn) {
        this.mHomeHotColumn.clear();
        this.mHomeHotColumn.addAll(mHomeHotColumn);
        notifyDataSetChanged();
    }

    /**
     * 颜值栏目
     *
     * @param mHomeFaceScoreColumn
     */
    public void getFaceScoreColmun(List<HomeFaceScoreColumn> mHomeFaceScoreColumn) {
        this.mHomeFaceScoreColumn.clear();
        this.mHomeFaceScoreColumn.addAll(mHomeFaceScoreColumn);
        mFaceScoreColumnAdapter.getFaceScoreColumn(mHomeFaceScoreColumn);
        notifyDataSetChanged();
    }

    /**
     * 全部栏目
     *
     * @param mHomeRecommendHotCate
     */
    public void getAllColumn(List<HomeRecommendHotCate> mHomeRecommendHotCate) {
        this.mHomeRecommendHotCate.clear();
        this.mHomeRecommendHotCate.addAll(mHomeRecommendHotCate);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_1:
                return new ColumnViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend, parent, false));
            case TYPE_2:
                return new ColumnViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend, parent, false));
            case TYPE_3:
                return new ColumnViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend, parent, false));
            case 4:
                if (mHeaderView != null) {
                    return new BannerViewHolder(mHeaderView);
                } else {
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder && position == 0) {

        } else if (holder instanceof ColumnViewHolder && position == 1) {
            bindColumnHolder((ColumnViewHolder) holder, position);
        } else if (holder instanceof ColumnViewHolder && position == 2) {
            bindFaceScoreColumnHolder((ColumnViewHolder) holder, position, true);
        } else {
            bindAllColumnHolder((ColumnViewHolder) holder, position);
        }
    }

    /**
     * 全部栏目
     *
     * @param holder
     * @param position
     */
    private void bindAllColumnHolder(ColumnViewHolder holder, int position) {
        holder.img_column_icon.setImageResource(R.mipmap.icon_column);
        holder.tv_column_name.setText(mHomeRecommendHotCate.get(position - 3).getTag_name());
        holder.rv_column_list.setLayoutManager(new GridLayoutManager(holder.rv_column_list.getContext(), 2, GridLayoutManager.VERTICAL, false));
        mAllColumnAdapter = new HomeRecommendAllColumnAdapter(holder.rv_column_list.getContext(), mHomeRecommendHotCate.get(position - 3).getRoom_list());
        holder.rv_column_list.setAdapter(mAllColumnAdapter);
        holder.rl_column_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeColumnMoreListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", holder.tv_column_name.getText().toString());
                bundle.putString("cate_id", mHomeRecommendHotCate.get(position - 3).getTag_id());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    /**
     * 颜值 栏目
     *
     * @param holder
     * @param position
     * @param isItem
     */
    private void bindFaceScoreColumnHolder(ColumnViewHolder holder, int position, boolean isItem) {

        holder.img_column_icon.setImageResource(R.mipmap.icon_reco_mobile);
        holder.tv_column_name.setText("熊猫星颜");
        holder.rv_column_list.setLayoutManager(new FullyGridLayoutManager(holder.rv_column_list.getContext(), 2, GridLayoutManager.VERTICAL, false));
        holder.rv_column_list.setAdapter(mFaceScoreColumnAdapter);
        holder.rl_column_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeRecommendFaceScoreActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", holder.tv_column_name.getText().toString());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) {
            return 4;
        } else if (position == 1) {
            return TYPE_1;
        } else if (position == 2) {
            return TYPE_2;
        }else {
            return TYPE_3;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_1:
                        case TYPE_2:
                        case TYPE_3:
                            return gridManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    /**
     * 栏目
     *
     * @param holder
     * @param position
     */
    private void bindColumnHolder(ColumnViewHolder holder, int position) {
        holder.rl_column_more.setVisibility(View.GONE);
        holder.img_column_icon.setImageResource(R.mipmap.reco_game_txt_icon);
        holder.tv_column_name.setText("最热");
        holder.rv_column_list.setLayoutManager(new FullyGridLayoutManager(holder.rv_column_list.getContext(), 2, GridLayoutManager.VERTICAL, false));
        mHotColumnAdapter = new HomeRecommendHotColumnAdapter(holder.rv_column_list.getContext(), mHomeHotColumn);
        holder.rv_column_list.setAdapter(mHotColumnAdapter);
    }


    @Override
    public int getItemCount() {
        return mHomeRecommendHotCate.size() + 3;
    }

    public class ColumnViewHolder extends RecyclerView.ViewHolder {
        //       栏目 Icon
        public ImageView img_column_icon;
        //        栏目 名称
        public TextView tv_column_name;
        //        加载更多
        public LinearLayout rl_column_more;
        //        栏目列表
        public RecyclerView rv_column_list;

        public LinearLayout item_home_recommed_girdview;

        public ColumnViewHolder(View itemView) {
            super(itemView);
            img_column_icon = (ImageView) itemView.findViewById(R.id.img_column_icon);
            tv_column_name = (TextView) itemView.findViewById(R.id.tv_column_name);
            rl_column_more = (LinearLayout) itemView.findViewById(R.id.rl_column_more);
            rv_column_list = (RecyclerView) itemView.findViewById(R.id.rv_column_list);
            item_home_recommed_girdview = (LinearLayout) itemView.findViewById(R.id.item_home_recommed_girdview);
        }
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        public BannerViewHolder(View itemView) {
            super(itemView);
        }
    }

}
