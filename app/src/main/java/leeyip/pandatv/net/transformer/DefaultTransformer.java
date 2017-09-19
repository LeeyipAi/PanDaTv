package leeyip.pandatv.net.transformer;


import leeyip.pandatv.net.response.HttpResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *  类描述：  预处理异常信息
 **/
public class DefaultTransformer<T>  implements Observable.Transformer<HttpResponse<T>,T>{
    @Override
    public Observable<T> call(Observable<HttpResponse<T>> httpResponseObservable) {

        return httpResponseObservable. subscribeOn(Schedulers.io())
                                                .observeOn(Schedulers.newThread())
                                                .compose(ErrorTransformer.<T>getInstance())
                                                .observeOn(AndroidSchedulers.mainThread());
    }
}
