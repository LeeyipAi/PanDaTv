package leeyip.pandatv.api.home;

import java.util.List;
import java.util.Map;

import leeyip.pandatv.model.logic.home.bean.HomeCarousel;
import leeyip.pandatv.model.logic.home.bean.HomeCateList;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreAllList;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreOtherList;
import leeyip.pandatv.model.logic.home.bean.HomeColumnMoreTwoCate;
import leeyip.pandatv.model.logic.home.bean.HomeFaceScoreColumn;
import leeyip.pandatv.model.logic.home.bean.HomeHotColumn;
import leeyip.pandatv.model.logic.home.bean.HomeRecommendHotCate;
import leeyip.pandatv.net.response.HttpResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

import static leeyip.pandatv.api.NetWorkApi.getCarousel;
import static leeyip.pandatv.api.NetWorkApi.getHomeCate;
import static leeyip.pandatv.api.NetWorkApi.getHomeCateList;
import static leeyip.pandatv.api.NetWorkApi.getHomeColumnMoreAllList;
import static leeyip.pandatv.api.NetWorkApi.getHomeColumnMoreCate;
import static leeyip.pandatv.api.NetWorkApi.getHomeColumnMoreOtherList;
import static leeyip.pandatv.api.NetWorkApi.getHomeFaceScoreColumn;
import static leeyip.pandatv.api.NetWorkApi.getHomeHotColumn;
import static leeyip.pandatv.api.NetWorkApi.getHomeRecommendHotCate;

/**
 * Created by Administrator on 2017/9/19/019.
 */

public interface HomeApi {

    /**
     * 首页分类列表
     *
     * @return
     */
    @GET(getHomeCateList)
    Observable<HttpResponse<List<HomeCateList>>> getHomeCateList(@QueryMap Map<String, String> params);

    /**
     * 首页 列表详情页
     *
     * @return
     */
    @GET(getHomeCate)
    Observable<HttpResponse<List<HomeRecommendHotCate>>> getHomeCate(@QueryMap Map<String, String> params);

    /**
     * 首页   推荐轮播图
     *
     * @return
     */
    @GET(getCarousel)
    Observable<HttpResponse<List<HomeCarousel>>> getCarousel(@QueryMap Map<String, String> params);

    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(getHomeHotColumn)
    Observable<HttpResponse<List<HomeHotColumn>>> getHotColumn(@QueryMap Map<String, String> params);

    /**
     * 推荐---颜值
     *
     * @return
     */
    @GET(getHomeFaceScoreColumn)
    Observable<HttpResponse<List<HomeFaceScoreColumn>>> getFaceScoreColumn(@QueryMap Map<String, String> params);

    /**
     * 推荐---热门 种类
     *
     * @return
     */
    @GET(getHomeRecommendHotCate)
    Observable<HttpResponse<List<HomeRecommendHotCate>>> getHotCate(@QueryMap Map<String, String> params);


    /**
     * 栏目 更多   --二级分类列表
     *
     * @return
     */
    @GET(getHomeColumnMoreCate)
    Observable<HttpResponse<List<HomeColumnMoreTwoCate>>> getHomeColumnMoreCate(@QueryMap Map<String, String> params);

    /**
     * 栏目 更多   --其他列表
     *
     * @return
     */
    @GET(getHomeColumnMoreOtherList)
    Observable<HttpResponse<List<HomeColumnMoreOtherList>>> getHomeColumnMoreOtherList(@QueryMap Map<String, String> params);

    /**
     * 栏目 更多   --全部列表
     *
     * @return
     */
    @GET(getHomeColumnMoreAllList + "{cate_id}")
    Observable<HttpResponse<List<HomeColumnMoreAllList>>> getHomeColumnMoreAllList(@Path("cate_id") String cate_id, @QueryMap Map<String, String> params);

    /**
     * 获取房间信息
     *请求URL：
     * http://capi.douyucdn.cn/api/v1/room/235520?aid=android1&client_sys=android&ne=1&support_pwd=1&time=1480501469&token=89175431_12_cd2cb4963d259081_1_54371072&auth=6fddd0d2e155255e923e208bebd7efb9
     * 请求方式：Get * 请求参数： * client_sys：设备类型 默认为：Android * roomid:房间ID * time:unixtime/1000 * aid:设备标示 默认：Android1 * ne： 默认：1 * support_pwd： 默认：1 * token： 登录后获取token * auth: md5("room/"+roomid+"?aid=android&clientsys=android&time="+1231)注意：time要和上面一致，android也不能改成ios
     */
//    @GET(getRoomDetails + "{room_id}")
//    Observable<HttpResponse<List<BaseResponse>>> getRoomDetails(@Path("room_id") String room_id, @QueryMap Map<String, String> params);
}