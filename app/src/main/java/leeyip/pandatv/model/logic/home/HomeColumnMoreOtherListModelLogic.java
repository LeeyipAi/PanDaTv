package leeyip.pandatv.model.logic.home;

import android.content.Context;

import java.util.List;

import leeyip.pandatv.api.home.HomeApi;
import leeyip.pandatv.model.ParamsMapUtils;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreOtherList;
import leeyip.pandatv.net.http.HttpUtils;
import leeyip.pandatv.net.transformer.DefaultTransformer;
import leeyip.pandatv.presenter.home.interfaces.HomeColumnMoreOtherListContract;
import rx.Observable;


public class HomeColumnMoreOtherListModelLogic implements HomeColumnMoreOtherListContract.Model {

    /**
     * 全部直播列表
     *
     * @param context
     * @param cate_id
     * @return
     */
    @Override
    public Observable<List<HomeColumnMoreOtherList>> getModelHomeColumnMoreOtherList(Context context, String cate_id, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeColumnMoreOtherList(ParamsMapUtils.getHomeColumnMoreOtherList(cate_id, offset, limit))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeColumnMoreOtherList>>());
    }
}
