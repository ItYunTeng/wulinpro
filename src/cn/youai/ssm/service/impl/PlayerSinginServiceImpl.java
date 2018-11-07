package cn.youai.ssm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.youai.ssm.dao.ItemMapper;
import cn.youai.ssm.dao.MonReawardMapper;
import cn.youai.ssm.dao.PlayerEverysinginMapper;
import cn.youai.ssm.dao.PlayerMapper;
import cn.youai.ssm.dao.PlayerPropcodityMapper;
import cn.youai.ssm.po.EveryDaySingin;
import cn.youai.ssm.po.Item;
import cn.youai.ssm.po.MonRewardExc;
import cn.youai.ssm.po.PlayerEverysingin;
import cn.youai.ssm.service.PlayerSinginService;
import cn.youai.ssm.util.DateUtil;

@Service
public class PlayerSinginServiceImpl implements PlayerSinginService {
	@Autowired
	private MonReawardMapper monReawarDao;
	@Autowired
	private PlayerEverysinginMapper  playerEverysinginDao;
	@Autowired
	private ItemMapper itemDao;
	@Autowired
	private PlayerMapper playerDao;
	@Autowired
	private PlayerPropcodityMapper playerPropcodityDao;
	@Value("${needYuanBao}")
	private int needYuanBao;
	/**
	 * 每日签到列表及状态判断
	 */
	public List<MonRewardExc> selectmonReawarList(Integer playerId){
		List<MonRewardExc> rewardAll = monReawarDao.selectAll();
		for(int i = 0; i < rewardAll.size(); i++){
			MonRewardExc monRewardExc = rewardAll.get(i);
			int compareTo = monRewardExc.getDate().compareTo(DateUtil.formateDate(new Date()));
			//等于当天
			if(compareTo == 0){
				int flag = playerEverysinginDao.selectEverysingin(playerId, monRewardExc.getDate());
				if(flag == 1){
					monRewardExc.setIsSinginState(0);
				}else{
					monRewardExc.setIsSinginState(1);//签到
				}
			}
			//比当天时间小
			if(compareTo == -1){
				int flag = playerEverysinginDao.selectEverysingin(playerId, monRewardExc.getDate());
				if(flag == 1){
					monRewardExc.setIsSinginState(0);
				}else{
					monRewardExc.setIsSinginState(2);//补签
				}
			}
			//比今天时间大
			if(compareTo == 1){
				monRewardExc.setIsSinginState(-1);//不可签到
			}
			String itemBag = monRewardExc.getEverydayrewardmap();
			JSONObject jsonObject = JSONObject.parseObject(itemBag);
			int refid = Integer.parseInt(jsonObject.get("refId").toString());
			Item item = itemDao.selectByPrimaryKey(refid);
			monRewardExc.setItem(item);
		}
		return rewardAll;
	}
	/**
	 * 每日签到或者补签到
	 * @param everyDaySingin
	 * @return
	 */
	public int geteveryDaySingin(EveryDaySingin everyDaySingin){
		int flag = 0;
		if(everyDaySingin.getRewardType() == 7|| everyDaySingin.getRewardType() == 8){
			int resultFlag = playerPropcodityDao.selectProdoity(everyDaySingin);
			if(resultFlag == 1){
				flag = playerPropcodityDao.updateplayerPropCo(everyDaySingin);
			}else{
				flag = playerPropcodityDao.insertPlayerPropcodity(everyDaySingin);
			}
		}
		flag = playerEverysinginDao.insertEveryDaySingin(everyDaySingin);
		switch (everyDaySingin.getIsType()) {
		case 1:
			if(everyDaySingin.getRewardType() == 6|| everyDaySingin.getRewardType() == 9){
				flag = playerDao.updatePlayermoney(everyDaySingin);
			}
			break;
		case 2:
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("playerId", everyDaySingin.getPlayerId());
			map.put("needMoney", needYuanBao);
			if(everyDaySingin.getRewardType() == 6|| everyDaySingin.getRewardType() == 9){
				if(everyDaySingin.getRewardType() == 6){
					everyDaySingin.setNum(everyDaySingin.getNum()-needYuanBao);
				}else{
					flag = playerDao.updatePlayerNeedMoney(map);
				}
				flag = playerDao.updatePlayermoney(everyDaySingin);
			}
			if(everyDaySingin.getRewardType() == 7|| everyDaySingin.getRewardType() == 8){
				flag = playerDao.updatePlayerNeedMoney(map);
			}
			break;
		default:
			break;
		}
		return flag;
	}
	/**
	 * 累计签到列表
	 * @param playerId
	 * @return
	 */
	public List<MonRewardExc> selectplayerSinginTolaaList(Integer playerId){
		List<MonRewardExc> alltotal = monReawarDao.selectAlltotal();
		int toltalSingin = playerEverysinginDao.selectSinginTotal(playerId);
		for(int i = 0; i<alltotal.size();i++){
			MonRewardExc rewardExc = alltotal.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("playerId", playerId);
			map.put("daylimit", rewardExc.getDaylimit());
			int resultFlag = playerEverysinginDao.selectAllsinginTotal(map);
			if(resultFlag == 1 ){
				rewardExc.setIsSinginState(0);//已签到
			}else{
				if(toltalSingin>=rewardExc.getDaylimit()){
					rewardExc.setIsSinginState(1);//可签到
				}else{
					rewardExc.setIsSinginState(-1);//不可签到
				}
			}
			String itemBag = rewardExc.getEverydayrewardmap();
			JSONObject jsonObject = JSONObject.parseObject(itemBag);
			int refid = Integer.parseInt(jsonObject.get("refId").toString());
			Item item = itemDao.selectByPrimaryKey(refid);
			rewardExc.setItem(item);
		}
		return alltotal;
	}
	
}
