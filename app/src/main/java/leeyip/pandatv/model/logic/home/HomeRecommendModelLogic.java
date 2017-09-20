package leeyip.pandatv.model.logic.home;

import android.content.Context;

import java.util.List;

import leeyip.pandatv.api.home.HomeApi;
import leeyip.pandatv.model.ParamsMapUtils;
import leeyip.pandatv.model.logic.home.bean.HomeCarousel;
import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreColumn;
import leeyip.pandatv.model.logic.home.bean.HomeHotColumn;
import leeyip.pandatv.model.logic.home.bean.HomeRecommendHotCate;
import leeyip.pandatv.net.http.HttpUtils;
import leeyip.pandatv.net.transformer.DefaultTransformer;
import leeyip.pandatv.presenter.home.interfaces.HomeRecommendContract;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/20/020.
 */

public class HomeRecommendModelLogic implements HomeRecommendContract.Model{

    /**
     * 获取首页轮播图
     *
     * @param context
     * @return
     */
    @Override
    public Observable<List<HomeCarousel>> getModelCarousel(Context context) {
        return HttpUtils
                .getInstance(context)
                .setLoadDiskCache(false)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getCarousel(ParamsMapUtils.getHomeCarousel())
                .compose(new DefaultTransformer<List<HomeCarousel>>());
    }

    /**
     * 首页 -- 推荐 -- 最热
     *
     * @param context
     * @return
     */
    @Override
    public Observable<List<HomeHotColumn>> getModelHotColumn(Context context) {
        return HttpUtils
                .getInstance(context)
                .setLoadDiskCache(false)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHotColumn(ParamsMapUtils.getDefaultParams())
                .compose(new DefaultTransformer<List<HomeHotColumn>>());
    }

    /**
     * 首页 -- 推荐 -- 颜值
     *
     * @param context
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public Observable<List<HomeFaceScoreColumn>> getModelFaceScoreColumn(Context context, int offset, int limit) {
        return HttpUtils
                .getInstance(context)
                .setLoadDiskCache(false)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getFaceScoreColumn(ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
                .compose(new DefaultTransformer<List<HomeFaceScoreColumn>>());
    }

    /**
     * 首页 -- 推荐 -- 热门
     *
     * @param context
     * @return
     */
    @Override
    public Observable<List<HomeRecommendHotCate>> getModelHotCate(Context context) {
        return HttpUtils
                .getInstance(context)
                .setLoadDiskCache(false)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHotCate(ParamsMapUtils.getDefaultParams())
                .compose(new DefaultTransformer<List<HomeRecommendHotCate>>());
    }
}
