package com.example.home.bean;

public class CheckBean {

    /**
     * data : {"btnName1":"升级","btnName2":"取消","info":"安卓优化安卓优化安卓优化安卓优化安卓优化","link":"http://www.baidu.com/android","title":"新版更新","forceUpdate":0,"version":"1.0.0","versionCode":100}
     * status : {"cninfo":"","code":"0","message":"success"}
     */

    private DataBean data;
    private StatusBean status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * btnName1 : 升级
         * btnName2 : 取消
         * info : 安卓优化安卓优化安卓优化安卓优化安卓优化
         * link : http://www.baidu.com/android
         * title : 新版更新
         * forceUpdate : 0
         * version : 1.0.0
         * versionCode : 100
         */

        private String btnName1;
        private String btnName2;
        private String info;
        private String link;
        private String title;
        private int forceUpdate;
        private String version;
        private int versionCode;

        public String getBtnName1() {
            return btnName1;
        }

        public void setBtnName1(String btnName1) {
            this.btnName1 = btnName1;
        }

        public String getBtnName2() {
            return btnName2;
        }

        public void setBtnName2(String btnName2) {
            this.btnName2 = btnName2;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(int forceUpdate) {
            this.forceUpdate = forceUpdate;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }
    }

    public static class StatusBean {
        /**
         * cninfo :
         * code : 0
         * message : success
         */

        private String cninfo;
        private String code;
        private String message;

        public String getCninfo() {
            return cninfo;
        }

        public void setCninfo(String cninfo) {
            this.cninfo = cninfo;
        }

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
    }
}
