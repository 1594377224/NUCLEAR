package cn.mvtech.beans;

import java.util.Date;

public class FlowInstance {
    private Integer id;

    private String flowid;

    private String flowname;

    private String userid;

    private String username;

    private String applyusername;

    private Date applydatetime;

    private String endusername;

    private Date enddatetime;

    private String statusid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlowid() {
        return flowid;
    }

    public void setFlowid(String flowid) {
        this.flowid = flowid;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplyusername() {
        return applyusername;
    }

    public void setApplyusername(String applyusername) {
        this.applyusername = applyusername;
    }

    public Date getApplydatetime() {
        return applydatetime;
    }

    public void setApplydatetime(Date applydatetime) {
        this.applydatetime = applydatetime;
    }

    public String getEndusername() {
        return endusername;
    }

    public void setEndusername(String endusername) {
        this.endusername = endusername;
    }

    public Date getEnddatetime() {
        return enddatetime;
    }

    public void setEnddatetime(Date enddatetime) {
        this.enddatetime = enddatetime;
    }

    public String getStatusid() {
        return statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }
}