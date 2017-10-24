package leeyip.pandatv.model.logic.home;

import android.content.Context;

import java.util.List;

import leeyip.pandatv.api.home.HomeApi;
import leeyip.pandatv.model.ParamsMapUtils;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreAllList;
import leeyip.pandatv.net.http.HttpUtils;
import leeyip.pandatv.net.transformer.DefaultTransformer;
import leeyip.pandatv.presenter.home.interfaces.HomeColumnMoreAllListContract;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/18/018.
 */

public class HomeColumnMoreAllListModelLogic implements HomeColumnMoreAllListContract.Model {
    @Override
    public Observable<List<HomeColumnMoreAllList>> getModelHomeColumnMoreAllList(Context context, String cate_id, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeColumnMoreAllList(cate_id,ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
                .compose(new DefaultTransformer<List<HomeColumnMoreAllList>>());
    }
}
