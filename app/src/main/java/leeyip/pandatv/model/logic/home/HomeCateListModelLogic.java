package leeyip.pandatv.model.logic.home;


import android.content.Context;

import java.util.List;

import leeyip.pandatv.api.home.HomeApi;
import leeyip.pandatv.model.ParamsMapUtils;
import leeyip.pandatv.model.logic.home.bean.HomeCateList;
import leeyip.pandatv.net.http.HttpUtils;
import leeyip.pandatv.net.transformer.DefaultTransformer;
import leeyip.pandatv.presenter.home.interfaces.HomeCateListContract;
import rx.Observable;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2017/3/14 下午3:32
 **/
public class HomeCateListModelLogic implements HomeCateListContract.Model {

    @Override
    public Observable<List<HomeCateList>> getHomeCateList(Context context) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeCateList(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<HomeCateList>>());
    }
}
