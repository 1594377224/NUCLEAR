package cn.hse.service;

import cn.hse.beans.DangerList;

public interface DangerListServie {

	public int insertDanger(DangerList dangerList);

	public DangerList selectDangerByCheckId(int checkId);

	public int delCheckAndDanger(int dangerId);

	public int updateDanger(DangerList dangerList);

}
