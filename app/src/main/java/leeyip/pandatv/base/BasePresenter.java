package leeyip.pandatv.base;

import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 1.获取绑定View实例传递到子类中进行调用!
 * <p>
 * 2.注销View实例
 * <p>
 * 3.创建 Model 实例
 * <p>
 * 4.注销Model实例
 * <p>
 * 5.通过RxJava进行绑定activity和fragment生命周期绑定
 */

public class BasePresenter<V extends BaseView, M extends BaseModel> implements Presenter<V, M> {

    protected Context mContext;

    protected V mView;

    protected M mModel;

    protected CompositeSubscription mCompositeSubscription;

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }


    /**
     * 取消訂閱
     */
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    //獲取綁定view實例
    @Override
    public void attachView(V v) {
        this.mView = v;
    }

    //獲取綁定model實例
    @Override
    public void attachModel(M m) {
        this.mModel = m;
    }

    //解除綁定view實例
    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }

    public V getView() {
        return mView;
    }

    public M getModel() {
        return mModel;
    }

    @Override
    public void detachModel() {
        this.mModel = null;
    }

    public boolean isViewBind() {
        return mView != null;
    }
}
