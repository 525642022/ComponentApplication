package com.example.shop.bean;

import java.util.List;

public class ShopListBean {

    /**
     * status : {"code":"0","message":"success","cninfo":""}
     * data : [{"id":1,"title":"商品1","price":50,"points":1000,"pic":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"}]
     */

    private StatusBean status;
    private List<DataBean> data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * code : 0
         * message : success
         * cninfo :
         */

        private String code;
        private String message;
        private String cninfo;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCninfo() {
            return cninfo;
        }

        public void setCninfo(String cninfo) {
            this.cninfo = cninfo;
        }
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 商品1
         * price : 50.0
         * points : 1000
         * pic : https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg
         */

        private String id;
        private String title;
        private double price;
        private int points;
        private String pic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
