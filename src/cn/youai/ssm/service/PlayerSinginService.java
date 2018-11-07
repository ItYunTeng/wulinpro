package cn.youai.ssm.service;

import java.util.List;

import cn.youai.ssm.po.EveryDaySingin;
import cn.youai.ssm.po.MonRewardExc;

public interface PlayerSinginService {
	//玩家每日签到状态列表
	public List<MonRewardExc> selectmonReawarList(Integer playerId);
	//玩家每日签到或补签操作
	public int geteveryDaySingin(EveryDaySingin everyDaySingin);
	//玩家累计签到列表
	public List<MonRewardExc> selectplayerSinginTolaaList(Integer playerId);
}
