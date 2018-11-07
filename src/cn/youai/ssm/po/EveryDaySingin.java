package cn.youai.ssm.po;

import java.io.Serializable;
import java.util.Date;

public class EveryDaySingin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int playerId;
	
	private int rewardType;
	
	private int refId;
	
	private int num;
	
	private int isType;
	
	private Date date;
	
	private int mType;
	
	private int dayLimit;

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getRewardType() {
		return rewardType;
	}

	public void setRewardType(int rewardType) {
		this.rewardType = rewardType;
	}

	public int getRefId() {
		return refId;
	}

	public void setRefId(int refId) {
		this.refId = refId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getIsType() {
		return isType;
	}

	public void setIsType(int isType) {
		this.isType = isType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	public int getDayLimit() {
		return dayLimit;
	}

	public void setDayLimit(int dayLimit) {
		this.dayLimit = dayLimit;
	}

	@Override
	public String toString() {
		return "EveryDaySingin [playerId=" + playerId + ", rewardType="
				+ rewardType + ", refId=" + refId + ", num=" + num
				+ ", isType=" + isType + ", date=" + date + ", mType=" + mType
				+ ", dayLimit=" + dayLimit + "]";
	}
	
}
