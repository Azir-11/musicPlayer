package xyz.qcbyt.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable{

    //    响应状态码,200为正常
    private int code;
    //    响应消息
    private String msg;
    //    结果数据
    private Object data;

    //  对成功的结果进行封装，不用每次都自己填code和msg，只需要把数据传进来就可以了
    public static Result succ(Object data) {
        return succ(200, "请求成功", data);
    }

    public static Result succ(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


}
