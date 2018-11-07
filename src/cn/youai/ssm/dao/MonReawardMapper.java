package cn.youai.ssm.dao;

import cn.youai.ssm.po.MonRewardExc;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface MonReawardMapper {
	//每日签到物品
    List<MonRewardExc> selectAll();
    //累计签到物品列表
    List<MonRewardExc> selectAlltotal();
}