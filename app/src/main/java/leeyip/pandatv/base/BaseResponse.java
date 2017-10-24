package leeyip.pandatv.base;

/**
 * Created by Administrator on 2017/10/19/019.
 */

public class BaseResponse {
    private int error;
    private Data data;

    public int getError() {
        return error;
    }

    public Data getData() {
        return data;
    }

    public static class Data {

    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "error=" + error +
                ", data=" + data +
                '}';
    }
}
