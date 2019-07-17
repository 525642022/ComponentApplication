package com.example.bean;

public class MyUserBean {

    /**
     * status : {"code":"0","message":"success","cninfo":""}
     * data : {"user":{"id":10000,"token":"7417603b51e179b94659d234550be9a4","account":"189****8421","mobile":"18910598421","points":80,"headImg":"http://p4.so.qhmsg.com/t0142d5ab0ec499e5f9.jpg","systime":1548760474925}}
     */

    private StatusBean status;
    private DataBean data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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
         * user : {"id":10000,"token":"7417603b51e179b94659d234550be9a4","account":"189****8421","mobile":"18910598421","points":80,"headImg":"http://p4.so.qhmsg.com/t0142d5ab0ec499e5f9.jpg","systime":1548760474925}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

    }
}
