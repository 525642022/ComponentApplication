package com.example.bean;

public  class UserBean {
            /**
             * id : 10000
             * token : 7417603b51e179b94659d234550be9a4
             * account : 189****8421
             * mobile : 18910598421
             * poStrings : 80
             * headImg : http://p4.so.qhmsg.com/t0142d5ab0ec499e5f9.jpg
             * systime : 1548760474925
             */

            private String id;
            private String token;
            private String account;
            private String mobile;
            private String poStrings;
            private String points;
            private String headImg;
            private long systime;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPoStrings() {
                return poStrings;
            }

            public void setPoStrings(String poStrings) {
                this.poStrings = poStrings;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public long getSystime() {
                return systime;
            }

            public void setSystime(long systime) {
                this.systime = systime;
            }
        }
