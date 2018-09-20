package com.hcyq.jblog.Utils;

import com.google.gson.Gson;
import com.hcyq.jblog.common.bean.ResultBeam;

public class ResultUtils {
    private static final int RESULT_CODE_SUCCESS = 0;
    private static final int RESULT_CODE_ERROR = -1;
//    private static final int RESULT_CODE_SUCCESS =1;
//    private static final int RESULT_CODE_SUCCESS =1;
    public static String getSuccessResult(Object object){
        ResultBeam bean =new ResultBeam();
        bean.setMsg("请求成功");
        bean.setStatus(RESULT_CODE_SUCCESS);
        bean.setData(object);
        return new Gson().toJson(bean);
    }

    public static String getErrorResult(String msg){
        ResultBeam bean =new ResultBeam();
        bean.setMsg(msg);
        bean.setStatus(RESULT_CODE_ERROR);
        bean.setData("");
        return new Gson().toJson(bean);
    }
}
