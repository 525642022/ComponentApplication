package com.example.answer.api;

import com.example.answer.bean.AnswerDetail;
import com.example.answer.bean.AnswerList;
import com.example.answer.bean.AnswerPoint;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AnswerApi {
    @POST("/rest/api/exam/list")
    Observable<AnswerList> getAnswerList(@Query("cid") String cid, @Query("uid") String uid);

    @POST("/rest/api/exam/detail")
    Observable<AnswerDetail> getAnswerDetail(@Query("id") String id);

    @POST("/rest/api/exam/commit")
    Observable<AnswerPoint> commitAnswer(@Query("uid") String uid, @Query("pid") String pid, @Query("ids") String ids);
}
