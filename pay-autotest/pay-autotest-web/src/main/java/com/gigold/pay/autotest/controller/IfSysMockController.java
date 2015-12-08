/**
 * Title: IfSysMockController.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.InterFaceField;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.autotest.service.InterFaceFieldService;
import com.gigold.pay.autotest.service.InterFaceService;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.util.common.StringUtil;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.framework.web.ResponseDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Title: IfSysMockController<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月30日上午11:37:39
 *
 */
@Controller
@RequestMapping("/autotest")
public class IfSysMockController extends BaseController {

	@Autowired
	IfSysMockService ifSysMockService;
	@Autowired
	InterFaceFieldService interFaceFieldService;
	@Autowired
	InterFaceService interFaceService;

	
	
	
	
	/**
	 * @return the ifSysMockService
	 */
	public IfSysMockService getIfSysMockService() {
		return ifSysMockService;
	}
	/**
	 * @param ifSysMockService the ifSysMockService to set
	 */
	public void setIfSysMockService(IfSysMockService ifSysMockService) {
		this.ifSysMockService = ifSysMockService;
	}
	/**
	 * @return the interFaceFieldService
	 */
	public InterFaceFieldService getInterFaceFieldService() {
		return interFaceFieldService;
	}
	/**
	 * @param interFaceFieldService the interFaceFieldService to set
	 */
	public void setInterFaceFieldService(InterFaceFieldService interFaceFieldService) {
		this.interFaceFieldService = interFaceFieldService;
	}
	/**
	 * @return the interFaceService
	 */
	public InterFaceService getInterFaceService() {
		return interFaceService;
	}
	/**
	 * @param interFaceService the interFaceService to set
	 */
	public void setInterFaceService(InterFaceService interFaceService) {
		this.interFaceService = interFaceService;
	}
	/**
	 * 
	 * Title: addIfSysMock<br/>
	 * Description: 新增接口测试数据<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月4日下午1:20:56
	 *
	 * @param dto
	 * @return
	 */
	@RequestMapping("/addifsysmock.do")
	public @ResponseBody ResponseDto addIfSysMock(@RequestBody IfSysMockAddReqDto dto) {
		ResponseDto reDto = new ResponseDto();
		// 验证请求参数合法性
		String code = dto.validation();
		// 没有通过则返回对应的返回码
		if (!"00000".equals(code)) {
			reDto.setRspCd(code);
			return reDto;
		}
		IfSysMock ifSysMock = null;
		try {
			ifSysMock = createBO(dto, IfSysMock.class);
		} catch (PendingException e) {
			reDto.setRspCd(CodeItem.CREATE_BO_FAILURE);
			return reDto;
		}
		boolean flag = ifSysMockService.addIfSysMock(ifSysMock);
		if (flag) {
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}
   /**
    * 
    * Title: deleteIfSysMockById<br/>
    * Description: 根据ID删除测试数据<br/>
    * @author xiebin
    * @date 2015年12月4日下午1:22:36
    *
    * @param dto
    * @return
    */
	@RequestMapping("/deleteifsysmockbyid.do")
	public @ResponseBody ResponseDto deleteIfSysMockById(@RequestBody IfSysMockDelReqDto dto) {
		ResponseDto reDto = new ResponseDto();
		boolean flag = ifSysMockService.deleteIfSysMockById(dto.getId());
		if (flag) {
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

	/**
	 * 
	 * Title: deleteIfSysMockByIfId<br/>
	 * Description: 根据接口ID删除测试数据<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月4日下午1:21:34
	 *
	 * @param dto
	 * @return
	 */
	@RequestMapping("/deleteifsysmockbyifId.do")
	public @ResponseBody ResponseDto deleteIfSysMockByIfId(@RequestBody IfSysMockDelReqDto dto) {
		ResponseDto reDto = new ResponseDto();
		boolean flag = ifSysMockService.deleteIfSysMockByIfId(dto.getIfId());
		if (flag) {
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

	/**
	 * 
	 * Title: updateIfSysMock<br/>
	 * Description: 修改测试数据<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月4日下午1:21:54
	 *
	 * @param dto
	 * @return
	 */
	@RequestMapping("/updateifsysmock.do")
	public @ResponseBody ResponseDto updateIfSysMock(@RequestBody IfSysMockAddReqDto dto) {
		ResponseDto reDto = new ResponseDto();
		// 验证请求参数合法性
		String code = dto.validation();
		// 没有通过则返回对应的返回码
		if (!"00000".equals(code)) {
			reDto.setRspCd(code);
			return reDto;
		}
		IfSysMock ifSysMock = null;
		try {
			ifSysMock = createBO(dto, IfSysMock.class);
		} catch (PendingException e) {
			reDto.setRspCd(CodeItem.CREATE_BO_FAILURE);
			return reDto;
		}
		boolean flag = ifSysMockService.updateIfSysMock(ifSysMock);
		if (flag) {
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

	/**
	 * 
	 * Title: getAllIfSys<br/>
	 * Description: 分页获取接口基本信息 列表页<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月2日下午1:47:35
	 *
	 * @param dto
	 * @return
	 */
	@RequestMapping("/getallifsys.do")
	public @ResponseBody IfSysMockRspDto getAllIfSys(@RequestBody IfSysMockPageDto dto) {
		debug("getAllIfSys方法");
		IfSysMockRspDto reDto = new IfSysMockRspDto();
		int curPageNum = dto.getPageNum();
		PageInfo<InterFaceInfo> pageInfo = null;
		PageHelper.startPage(curPageNum, Integer.parseInt(SystemPropertyConfigure.getProperty("sys.pageSize")));
		InterFaceInfo interFaceInfo=null;
		try {
			interFaceInfo = createBO(dto, InterFaceInfo.class);
		} catch (PendingException e) {
			debug("转换bo异常");
			e.printStackTrace();
		}
		List<InterFaceInfo> list = interFaceService.getAllIfSys(interFaceInfo);
		if (list != null) {
			pageInfo=new PageInfo<InterFaceInfo>(list);
			reDto.setPageInfo(pageInfo);
			reDto.setRspCd(SysCode.SUCCESS);
		} else {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

	/**
	 * 
	 * Title: getIfSysMockByIfId<br/>
	 * Description: 根据接口ID获取测试数据信息 编辑页,新增页<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月2日下午3:08:21
	 *
	 * @param dto
	 * @return
	 */
	@RequestMapping("/getifsysmockbyifid.do")
	public @ResponseBody IfStsMockRspListDto getIfSysMockByIfId(@RequestBody IfSysMockAddReqDto dto) {
		IfStsMockRspListDto reDto = new IfStsMockRspListDto();
		try {
			int ifId = dto.getId();
			// 获取接口基本信息
			InterFaceInfo interFaceInfo = interFaceService.getInterFaceById(ifId);
			if (interFaceInfo == null) {
				reDto.setRspCd(CodeItem.FAILURE);
				return reDto;
			}
			// 获取接口请求字段的JSON展示字符串
			InterFaceField interFaceField = (InterFaceField) SpringContextHolder.getBean(InterFaceField.class);
			interFaceField.setIfId(ifId);
			interFaceField.setFieldFlag("1");
			String reqJson = interFaceFieldService.getJsonStr(interFaceField);
			if (StringUtil.isNotBlank(reqJson)) {
				interFaceInfo.setReqJsonStr(reqJson);
			}
			// 获取接口响应字段的JSON展示字符串
			interFaceField.setFieldFlag("2");
			String rspJson = interFaceFieldService.getJsonStr(interFaceField);
			if (StringUtil.isNotBlank(rspJson)) {
				interFaceInfo.setRspJsonStr(rspJson);
			}
			// 获取接口测试数据
			List<IfSysMock> list = ifSysMockService.getMockInfoByIfId(ifId);
			if (list == null||list.size()==0) {
				// 如果还没有测试数据 则默认添加一条
				IfSysMock mock = (IfSysMock) SpringContextHolder.getBean(IfSysMock.class);
				mock.setIfId(ifId);
				mock.setRequestJson(reqJson);
				mock.setResponseJson(rspJson);
				interFaceInfo.getMockList().add(mock);
			} else {
				interFaceInfo.setMockList(list);
			}
			reDto.setRspCd(SysCode.SUCCESS);
			reDto.setInterFaceInfo(interFaceInfo);
		} catch (Exception e) {
			reDto.setRspCd(CodeItem.FAILURE);
		}
		return reDto;
	}

}
