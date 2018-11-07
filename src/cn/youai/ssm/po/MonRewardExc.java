package cn.youai.ssm.po;

import com.wordnik.swagger.annotations.ApiModel;

import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel(value="MonRewardExc",description="每日奖励物品")
public class MonRewardExc extends MonReaward{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(dataType="item",value="领取物品")
	private Item  item;
	
	@ApiModelProperty(dataType="isSinginState",value="签到状态")
	private Integer isSinginState;
	
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getIsSinginState() {
		return isSinginState;
	}

	public void setIsSinginState(Integer isSinginState) {
		this.isSinginState = isSinginState;
	}

	@Override
	public String toString() {
		return "MonRewardExc [item=" + item + ", isSinginState="
				+ isSinginState + "]";
	}

	
}
