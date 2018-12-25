package cn.hse.util;

import java.io.Serializable;

public class Result<T> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rtnCode;
    private String rtnMsg;
    private T bean;
    private T beans;
    private T object;


    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public T getBeans() {
        return beans;
    }

    public void setBeans(T beans) {
        this.beans = beans;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Result{" +
                "rtnCode='" + rtnCode + '\'' +
                ", rtnMsg='" + rtnMsg + '\'' +
                ", bean=" + bean +
                ", beans=" + beans +
                ", object=" + object +
                '}';
    }
}
