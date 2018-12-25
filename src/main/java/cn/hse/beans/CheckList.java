package cn.hse.beans;

import java.util.Date;

public class CheckList {
    private Integer id;

    private String userId;
    private String projno;

    private Integer state;

    private String recordno;

    private Date checkdate;

    private Integer checkform;

    private Integer recordtype;

    private String checkcontent;

    private String checkperson;

    private String draftunit;

    private String draftdept;

    private String draftperson;

    private Date draftdate;

    private String approveperson;

    private Date approvedate;

    private Integer isdel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjno() {
        return projno;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setProjno(String projno) {
        this.projno = projno;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRecordno() {
        return recordno;
    }

    public void setRecordno(String recordno) {
        this.recordno = recordno;
    }

    public Date getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    public Integer getCheckform() {
        return checkform;
    }

    public void setCheckform(Integer checkform) {
        this.checkform = checkform;
    }

    public Integer getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(Integer recordtype) {
        this.recordtype = recordtype;
    }

    public String getCheckcontent() {
        return checkcontent;
    }

    public void setCheckcontent(String checkcontent) {
        this.checkcontent = checkcontent;
    }

    public String getCheckperson() {
        return checkperson;
    }

    public void setCheckperson(String checkperson) {
        this.checkperson = checkperson;
    }

    public String getDraftunit() {
        return draftunit;
    }

    public void setDraftunit(String draftunit) {
        this.draftunit = draftunit;
    }

    public String getDraftdept() {
        return draftdept;
    }

    public void setDraftdept(String draftdept) {
        this.draftdept = draftdept;
    }

    public String getDraftperson() {
        return draftperson;
    }

    public void setDraftperson(String draftperson) {
        this.draftperson = draftperson;
    }

    public Date getDraftdate() {
        return draftdate;
    }

    public void setDraftdate(Date draftdate) {
        this.draftdate = draftdate;
    }

    public String getApproveperson() {
        return approveperson;
    }

    public void setApproveperson(String approveperson) {
        this.approveperson = approveperson;
    }

    public Date getApprovedate() {
        return approvedate;
    }

    public void setApprovedate(Date approvedate) {
        this.approvedate = approvedate;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}