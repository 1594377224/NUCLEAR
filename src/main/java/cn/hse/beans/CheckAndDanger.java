package cn.hse.beans;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class CheckAndDanger {
    private Integer id;

    private Integer checkid;

    private Integer dangerid;
    
    private Integer delayToApplyForId;   //延期申请
    
    

    public Integer getDelayToApplyForId() {
		return delayToApplyForId;
	}

	public void setDelayToApplyForId(Integer delayToApplyForId) {
		this.delayToApplyForId = delayToApplyForId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCheckid() {
        return checkid;
    }

    public void setCheckid(Integer checkid) {
        this.checkid = checkid;
    }

    public Integer getDangerid() {
        return dangerid;
    }

    public void setDangerid(Integer dangerid) {
        this.dangerid = dangerid;
    }
}