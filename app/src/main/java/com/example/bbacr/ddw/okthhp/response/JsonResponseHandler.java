package com.example.bbacr.ddw.okthhp.response;

import org.json.JSONObject;

/**
 * Created by MiyuShiroki
 * on 2017/2/24.
 * json类型的回调接口
 */
public abstract class JsonResponseHandler implements IResponseHandler {

    public abstract void onSuccess(int statusCode, JSONObject response);

    @Override
    public void onProgress(long currentBytes, long totalBytes) {

    }
}
