package leeyip.pandatv.model.logic.video.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19/019.
 */

public class RoomDetailsInfo {
    private String room_id;
    private String room_thumb;
    private String cate_id;
    private String cate_name;
    private String room_name;
    private String room_status;
    private String owner_name;
    private String avatar;
    private int online;
    private String owner_weight;
    private String fans_num;
    private String start_time;
    private List<GiftBean> gift;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_thumb() {
        return room_thumb;
    }

    public void setRoom_thumb(String room_thumb) {
        this.room_thumb = room_thumb;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_status() {
        return room_status;
    }

    public void setRoom_status(String room_status) {
        this.room_status = room_status;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getOwner_weight() {
        return owner_weight;
    }

    public void setOwner_weight(String owner_weight) {
        this.owner_weight = owner_weight;
    }

    public String getFans_num() {
        return fans_num;
    }

    public void setFans_num(String fans_num) {
        this.fans_num = fans_num;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public List<GiftBean> getGift() {
        return gift;
    }

    public void setGift(List<GiftBean> gift) {
        this.gift = gift;
    }

    public static class GiftBean {
        /**
         * id : 1005
         * name : 超级火箭
         * type : 2
         * pc : 2000
         * gx : 20000
         * desc : 赠送全站广播并派送鱼丸宝箱
         * intro :
         * mimg : https://staticlive.douyucdn.cn/storage/webpic_resources/upload/dygift/1707/674c98b8acde7b7791512c82adfdf68d.png
         * himg : https://staticlive.douyucdn.cn/storage/webpic_resources/upload/dygift/1707/c3f3f69e1fdc4f9b2c02a7bcd30334eb.gif
         */

        private String id;
        private String name;
        private String type;
        private String pc;
        private String gx;
        private String desc;
        private String intro;
        private String mimg;
        private String himg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPc() {
            return pc;
        }

        public void setPc(String pc) {
            this.pc = pc;
        }

        public String getGx() {
            return gx;
        }

        public void setGx(String gx) {
            this.gx = gx;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getMimg() {
            return mimg;
        }

        public void setMimg(String mimg) {
            this.mimg = mimg;
        }

        public String getHimg() {
            return himg;
        }

        public void setHimg(String himg) {
            this.himg = himg;
        }
    }
}
