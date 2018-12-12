package cn.mvtech.beans;

public class Flow {
    private Integer id;

    private String flowcode;

    private String flowname;

    private String flowversion;

    private String params;

    private Integer enableflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlowcode() {
        return flowcode;
    }

    public void setFlowcode(String flowcode) {
        this.flowcode = flowcode;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getFlowversion() {
        return flowversion;
    }

    public void setFlowversion(String flowversion) {
        this.flowversion = flowversion;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getEnableflag() {
        return enableflag;
    }

    public void setEnableflag(Integer enableflag) {
        this.enableflag = enableflag;
    }
}