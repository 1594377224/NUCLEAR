package cn.hse.beans;

import java.sql.Timestamp;
import java.util.Date;

public class DangerList {
    private Integer id;

    private String lineno;

    private String noticeno;

    private Timestamp distributdate;

    private String unit;

    private String area;

    private String unitid;

    private String hsehiddenlevel;

    private String hiddencategory;

    private String nonconformity;

    private String hiddendescription;

    private String hiddendoc;

    private Timestamp reqcompletedate;

    private String correctiverequest;

    private String rectificationsituation;

    private String returndoc;

    private String contractonpeople;

    private Date completedate;
    
    private String responsiblepersonid;

    private String responsibleperson;
    
    private String copyPerson;
    private Date responsibledate;

    private String contractorapprove;

    private Date contractorapprovedate;

    private String comfirmcontent;

    private String verifyperson;

    private Date verifydate;

    private String hsepasscontent;

    private String verificationcondition;

    private String closeperson;

    private Date closedate;

    private String corapproveperson;

    private Date corapprovedate;

    private String ifsitecorrection;

    private Integer isdel;
    
    private String ifModify;  //是否当场整改
    private String keyHidden;  //'关键隐患（0 管理性关键隐患、1 行为性关键隐患、2 装置性关键隐患）',

	public String getIfModify() {
		return ifModify;
	}

	public void setIfModify(String ifModify) {
		this.ifModify = ifModify;
	}

	public String getKeyHidden() {
		return keyHidden;
	}

	public void setKeyHidden(String keyHidden) {
		this.keyHidden = keyHidden;
	}

	public String getResponsiblepersonid() {
		return responsiblepersonid;
	}

	public void setResponsiblepersonid(String responsiblepersonid) {
		this.responsiblepersonid = responsiblepersonid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLineno() {
        return lineno;
    }

    public void setLineno(String lineno) {
        this.lineno = lineno;
    }

    public String getNoticeno() {
        return noticeno;
    }

    public void setNoticeno(String noticeno) {
        this.noticeno = noticeno;
    }

    public Date getDistributdate() {
        return distributdate;
    }

    public void setDistributdate(Timestamp distributdate) {
        this.distributdate = distributdate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getHsehiddenlevel() {
        return hsehiddenlevel;
    }

    public void setHsehiddenlevel(String hsehiddenlevel) {
        this.hsehiddenlevel = hsehiddenlevel;
    }

    public String getHiddencategory() {
        return hiddencategory;
    }

    public void setHiddencategory(String hiddencategory) {
        this.hiddencategory = hiddencategory;
    }

    public String getNonconformity() {
        return nonconformity;
    }

    public void setNonconformity(String nonconformity) {
        this.nonconformity = nonconformity;
    }

    public String getHiddendescription() {
        return hiddendescription;
    }

    public void setHiddendescription(String hiddendescription) {
        this.hiddendescription = hiddendescription;
    }

    public String getHiddendoc() {
        return hiddendoc;
    }

    public void setHiddendoc(String hiddendoc) {
        this.hiddendoc = hiddendoc;
    }

    public Date getReqcompletedate() {
        return reqcompletedate;
    }

    public void setReqcompletedate(Timestamp reqcompletedate) {
        this.reqcompletedate = reqcompletedate;
    }

    public String getCorrectiverequest() {
        return correctiverequest;
    }

    public void setCorrectiverequest(String correctiverequest) {
        this.correctiverequest = correctiverequest;
    }

    public String getRectificationsituation() {
        return rectificationsituation;
    }

    public void setRectificationsituation(String rectificationsituation) {
        this.rectificationsituation = rectificationsituation;
    }

    public String getReturndoc() {
        return returndoc;
    }

    public void setReturndoc(String returndoc) {
        this.returndoc = returndoc;
    }

    public String getContractonpeople() {
        return contractonpeople;
    }

    public void setContractonpeople(String contractonpeople) {
        this.contractonpeople = contractonpeople;
    }

    public Date getCompletedate() {
        return completedate;
    }

    public void setCompletedate(Date completedate) {
        this.completedate = completedate;
    }

    public String getResponsibleperson() {
        return responsibleperson;
    }

    public void setResponsibleperson(String responsibleperson) {
        this.responsibleperson = responsibleperson;
    }

    public Date getResponsibledate() {
        return responsibledate;
    }

    public void setResponsibledate(Date responsibledate) {
        this.responsibledate = responsibledate;
    }

    public String getContractorapprove() {
        return contractorapprove;
    }

    public void setContractorapprove(String contractorapprove) {
        this.contractorapprove = contractorapprove;
    }

    public Date getContractorapprovedate() {
        return contractorapprovedate;
    }

    public void setContractorapprovedate(Date contractorapprovedate) {
        this.contractorapprovedate = contractorapprovedate;
    }

    public String getComfirmcontent() {
        return comfirmcontent;
    }

    public void setComfirmcontent(String comfirmcontent) {
        this.comfirmcontent = comfirmcontent;
    }

    public String getVerifyperson() {
        return verifyperson;
    }

    public void setVerifyperson(String verifyperson) {
        this.verifyperson = verifyperson;
    }

    public Date getVerifydate() {
        return verifydate;
    }

    public void setVerifydate(Date verifydate) {
        this.verifydate = verifydate;
    }

    public String getHsepasscontent() {
        return hsepasscontent;
    }

    public void setHsepasscontent(String hsepasscontent) {
        this.hsepasscontent = hsepasscontent;
    }

    public String getVerificationcondition() {
        return verificationcondition;
    }

    public void setVerificationcondition(String verificationcondition) {
        this.verificationcondition = verificationcondition;
    }

    public String getCloseperson() {
        return closeperson;
    }

    public void setCloseperson(String closeperson) {
        this.closeperson = closeperson;
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public String getCorapproveperson() {
        return corapproveperson;
    }

    public void setCorapproveperson(String corapproveperson) {
        this.corapproveperson = corapproveperson;
    }

    public Date getCorapprovedate() {
        return corapprovedate;
    }

    public void setCorapprovedate(Date corapprovedate) {
        this.corapprovedate = corapprovedate;
    }

    public String getIfsitecorrection() {
        return ifsitecorrection;
    }

    public void setIfsitecorrection(String ifsitecorrection) {
        this.ifsitecorrection = ifsitecorrection;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

	public String getCopyPerson() {
		return copyPerson;
	}

	public void setCopyPerson(String copyPerson) {
		this.copyPerson = copyPerson;
	}
    
    
}