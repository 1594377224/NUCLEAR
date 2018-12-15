package cn.hse.beans;

public class InstanceRelation {
    private Integer id;

    private Integer instanceid;

    private Integer checkid;
    
    private Integer dangerId;

    public Integer getDangerId() {
		return dangerId;
	}

	public void setDangerId(Integer dangerId) {
		this.dangerId = dangerId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(Integer instanceid) {
        this.instanceid = instanceid;
    }

    public Integer getCheckid() {
        return checkid;
    }

    public void setCheckid(Integer checkid) {
        this.checkid = checkid;
    }
}