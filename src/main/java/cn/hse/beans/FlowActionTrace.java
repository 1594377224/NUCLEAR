package cn.hse.beans;

import java.util.Date;

public class FlowActionTrace {
    private Integer id;

    private String instanceid;

    private String flowid;

    private String flowname;

    private String flowcode;

    private String stepid;

    private String stepname;

    private String stepcode;

    private String actionid;

    private String actionname;

    private String actioncode;

    private String owneruserid;

    private String ownerusername;

    private String owneruserdesc;

    private String submituserid;

    private String submitusername;

    private String submituserdesc;

    private Date arrivetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid;
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

    public String getFlowcode() {
        return flowcode;
    }

    public void setFlowcode(String flowcode) {
        this.flowcode = flowcode;
    }

    public String getStepid() {
        return stepid;
    }

    public void setStepid(String stepid) {
        this.stepid = stepid;
    }

    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
    }

    public String getStepcode() {
        return stepcode;
    }

    public void setStepcode(String stepcode) {
        this.stepcode = stepcode;
    }

    public String getActionid() {
        return actionid;
    }

    public void setActionid(String actionid) {
        this.actionid = actionid;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public String getActioncode() {
        return actioncode;
    }

    public void setActioncode(String actioncode) {
        this.actioncode = actioncode;
    }

    public String getOwneruserid() {
        return owneruserid;
    }

    public void setOwneruserid(String owneruserid) {
        this.owneruserid = owneruserid;
    }

    public String getOwnerusername() {
        return ownerusername;
    }

    public void setOwnerusername(String ownerusername) {
        this.ownerusername = ownerusername;
    }

    public String getOwneruserdesc() {
        return owneruserdesc;
    }

    public void setOwneruserdesc(String owneruserdesc) {
        this.owneruserdesc = owneruserdesc;
    }

    public String getSubmituserid() {
        return submituserid;
    }

    public void setSubmituserid(String submituserid) {
        this.submituserid = submituserid;
    }

    public String getSubmitusername() {
        return submitusername;
    }

    public void setSubmitusername(String submitusername) {
        this.submitusername = submitusername;
    }

    public String getSubmituserdesc() {
        return submituserdesc;
    }

    public void setSubmituserdesc(String submituserdesc) {
        this.submituserdesc = submituserdesc;
    }

    public Date getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(Date arrivetime) {
        this.arrivetime = arrivetime;
    }
}