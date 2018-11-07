package cn.youai.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import cn.youai.ssm.exception.Response;
import cn.youai.ssm.po.EveryDaySingin;
import cn.youai.ssm.po.MonRewardExc;
import cn.youai.ssm.service.PlayerSinginService;
import cn.youai.ssm.util.RedisClientTemplate;
import cn.youai.ssm.util.SerializeUtil;

@Controller
@SuppressWarnings("all")
@Api(value="PlayerSinginControlller",description="玩家签到")
public class PlayerSinginController {
	@Autowired
	private PlayerSinginService playerSinginService;
	@Autowired
	private RedisClientTemplate template;
	/**
	 * 
	 * @param response
	 * @param playerId
	 * @return
	 */
	@ApiOperation(value="玩家每日签到列表",notes="玩家每日签到列表，玩家ID是必须的",httpMethod="GET",response=Response.class)
	@RequestMapping(value="/singinList.action",method=RequestMethod.GET)
	public @ResponseBody Response singinItemList(
			@ApiParam(value="返回值",name="response")
			Response response,
			@ApiParam(value="玩家ID",name="playerId")
			@RequestParam(value="playerId",required=true)Integer playerId){
		List<MonRewardExc> monReawarList = playerSinginService.selectmonReawarList(playerId);
		return response.success(monReawarList);
	}
	/**
	 * 
	 * @param everyDaySingin
	 * @param response
	 * @return
	 */
	@ApiOperation(value="玩家每日/累计签到操作",notes="玩家每日签到操作，对应的物品应为json对象",httpMethod="POST",response=Response.class)
	@RequestMapping(value="singinOption.action",method=RequestMethod.POST)
	public @ResponseBody Response singinOption(
			@ApiParam(value="签到物品属性",name="everyDaySingin为json对象")
			@RequestBody(required=true)EveryDaySingin everyDaySingin,
			@ApiParam(value="返回值",name="response")
			Response response
			){
		int flag = playerSinginService.geteveryDaySingin(everyDaySingin);
		if(flag == 1){
			return response.success(1);
		}else{
			return response.failure();
		}
	}
	
	/**
	 * 
	 * @param playerId
	 * @param response
	 * @return
	 */
	@ApiOperation(value="玩家累计签到列表",notes="玩家累计签到，玩家ID是必须的",httpMethod="GET",response=Response.class)
	@RequestMapping(value="/singinTotalList.action",method=RequestMethod.GET)
	public @ResponseBody Response totalSinginList(
			@ApiParam(value="玩家ID",name="playerId")
			@RequestParam(value="playerId",required=true)Integer playerId,
			@ApiParam(value="返回值",name="response")
			Response response
			){
		List<MonRewardExc> totalList = playerSinginService.selectplayerSinginTolaaList(playerId);
		return response.success(totalList);
	}
	
}
