package com.xiaomai.cloud.api.comm;

/**
 * @author Madison
 * @date 2021/4/5
 */
public class R<T> {
    private int code = 200;
    private String msg = "success";
    private T data;

    public R(Builder<T> builder){
        this.code=builder.code;
        this.msg=builder.msg;
        this.data=builder.data;
    }

    public static class Builder<T>{
        private int code =200;
        private String msg = "success";
        private T data;

        public R ok(){
            return new R(this);
        }

        public R failed(){
            this.code=-1;
            this.msg="failed";
            return new R(this);
        }

        public R consumerFailed(int code,String msg){
            this.code=code;
            this.msg=msg;
            return new R(this);
        }

        public Builder<T> setData(T data){
            this.data =data;
            return this;
        }
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
