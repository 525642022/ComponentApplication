package com.example.answer.bean;

import java.io.Serializable;
import java.util.List;

public class AnswerDetail implements Serializable {


    /**
     * status : {"code":"0","message":"success","cninfo":""}
     * data : {"points":50,"questions":[{"id":1,"title":"题目1","optionList":[{"desc":"A.选项1","v":0},{"desc":"B.选项2","v":1},{"desc":"C.选项3","v":0},{"desc":"D.选项4","v":0}],"lastFlag":0},{"id":2,"title":"题目2","optionList":[{"desc":"A.选项1","v":0},{"desc":"B.选项2","v":1},{"desc":"C.选项3","v":0},{"desc":"D.选项4","v":0}],"lastFlag":1}],"pid":1,"title":"法律法规第一套测试题"}
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

    public static class StatusBean implements Serializable {
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

    public static class DataBean implements Serializable {
        /**
         * points : 50
         * questions : [{"id":1,"title":"题目1","optionList":[{"desc":"A.选项1","v":0},{"desc":"B.选项2","v":1},{"desc":"C.选项3","v":0},{"desc":"D.选项4","v":0}],"lastFlag":0},{"id":2,"title":"题目2","optionList":[{"desc":"A.选项1","v":0},{"desc":"B.选项2","v":1},{"desc":"C.选项3","v":0},{"desc":"D.选项4","v":0}],"lastFlag":1}]
         * pid : 1
         * title : 法律法规第一套测试题
         */

        private int points;
        private String pid;
        private String title;
        private List<QuestionsBean> questions;

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<QuestionsBean> getQuestions() {
            return questions;
        }

        public void setQuestions(List<QuestionsBean> questions) {
            this.questions = questions;
        }

        public static class QuestionsBean implements Serializable {
            /**
             * id : 1
             * title : 题目1
             * optionList : [{"desc":"A.选项1","v":0},{"desc":"B.选项2","v":1},{"desc":"C.选项3","v":0},{"desc":"D.选项4","v":0}]
             * lastFlag : 0
             */

            private String id;
            private String title;
            private int lastFlag;
            private List<OptionListBean> optionList;

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

            public int getLastFlag() {
                return lastFlag;
            }

            public void setLastFlag(int lastFlag) {
                this.lastFlag = lastFlag;
            }

            public List<OptionListBean> getOptionList() {
                return optionList;
            }

            public void setOptionList(List<OptionListBean> optionList) {
                this.optionList = optionList;
            }

            public static class OptionListBean implements  Serializable {
                /**
                 * desc : A.选项1
                 * v : 0
                 */

                private String desc;
                private int v;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getV() {
                    return v;
                }

                public void setV(int v) {
                    this.v = v;
                }
            }
        }
    }
}
