package com.example.home.bean;

import java.util.List;

public class HomeArticleBean {

    /**
     * status : {"code":"0","message":"success","cninfo":""}
     * data : [{"id":7,"type":1,"title":"光天化日之下","pics":"[\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\"]","author":"新华日报","publishTime":1546834200000,"timeDesc":"01-07 12:10","thumbnail":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","imgList":["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]},{"id":5,"type":1,"title":"光天化日之下","pics":"[\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\"]","author":"新华日报","publishTime":1546661400000,"timeDesc":"01-05 12:10","thumbnail":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","imgList":["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]},{"id":4,"type":2,"title":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","pics":"[\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\",\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\",\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\"]","author":"人民日报","publishTime":1546576200000,"timeDesc":"01-04 12:30","thumbnail":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","imgList":["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]},{"id":3,"type":1,"title":"光天化日之下","pics":"[\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\"]","author":"新华日报","publishTime":1546488600000,"timeDesc":"01-03 12:10","thumbnail":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","imgList":["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]},{"id":2,"type":2,"title":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","pics":"[\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\",\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\",\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\"]","author":"人民日报","publishTime":1546403400000,"timeDesc":"01-02 12:30","thumbnail":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","imgList":["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]},{"id":8,"type":2,"title":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","pics":"[\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\",\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\",\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\"]","author":"人民日报","publishTime":1546317000000,"timeDesc":"01-01 12:30","thumbnail":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","imgList":["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]},{"id":10,"type":2,"title":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","pics":"[\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\",\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\",\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\"]","author":"人民日报","publishTime":1546317000000,"timeDesc":"01-01 12:30","thumbnail":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","imgList":["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]},{"id":12,"type":2,"title":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","pics":"[\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\",\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\",\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\"]","author":"人民日报","publishTime":1546317000000,"timeDesc":"01-01 12:30","thumbnail":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","imgList":["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]},{"id":1,"type":1,"title":"光天化日之下","pics":"[\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\"]","author":"新华日报","publishTime":1546315800000,"timeDesc":"01-01 12:10","thumbnail":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","imgList":["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]},{"id":9,"type":1,"title":"光天化日之下","pics":"[\"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg\"]","author":"新华日报","publishTime":1546315800000,"timeDesc":"01-01 12:10","thumbnail":"https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg","imgList":["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]}]
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
         * id : 7
         * type : 1
         * title : 光天化日之下
         * pics : ["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]
         * author : 新华日报
         * publishTime : 1546834200000
         * timeDesc : 01-07 12:10
         * thumbnail : https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg
         * imgList : ["https://00.imgmini.eastday.com/mobile/20180507/20180507102111_9637460e8183b40d65b62dd7bb94cb10_1_mwpm_03200403.jpg"]
         */

        private String id;
        private String type;
        private String title;
        private String pics;
        private String author;
        private long publishTime;
        private String timeDesc;
        private String thumbnail;
        private List<String> imgList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getTimeDesc() {
            return timeDesc;
        }

        public void setTimeDesc(String timeDesc) {
            this.timeDesc = timeDesc;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }
    }
}
