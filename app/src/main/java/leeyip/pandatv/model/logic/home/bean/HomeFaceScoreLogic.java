package leeyip.pandatv.model.logic.home.bean;

import android.content.Context;

import java.util.List;

import leeyip.pandatv.api.home.HomeApi;
import leeyip.pandatv.model.ParamsMapUtils;
import leeyip.pandatv.net.http.HttpUtils;
import leeyip.pandatv.net.transformer.DefaultTransformer;
import leeyip.pandatv.presenter.home.interfaces.HomeFaceScoreContract;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/12/012.
 */

public class HomeFaceScoreLogic implements HomeFaceScoreContract.Modle {
    @Override
    public Observable<List<HomeFaceScoreColumn>> getModleFaceScoreColumn(Context context, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getFaceScoreColumn(ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
                .compose(new DefaultTransformer<List<HomeFaceScoreColumn>>());
    }
}
