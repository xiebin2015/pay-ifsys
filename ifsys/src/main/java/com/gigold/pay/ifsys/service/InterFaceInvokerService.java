/**
 * Title: InterFaceInvokerService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.ifsys.bo.InterFaceInvoker;
import com.gigold.pay.ifsys.dao.InterFaceInvokerDao;

/**
 * Title: InterFaceInvokerService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月23日下午3:22:52
 *
 */
@Service
public class InterFaceInvokerService extends Domain {
    /** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
    InterFaceInvokerDao interFaceInvokerDao;
	/**
	 * 
	 * Title: addInterFaceInvoker<br/>
	 * Description: 新增接口关注者<br/>
	 * @author xiebin
	 * @date 2015年11月23日下午3:26:25
	 *
	 * @param invoker
	 * @return
	 */
	public boolean addInterFaceInvoker(InterFaceInvoker invoker){
		boolean flag=false;
		try{
			flag=interFaceInvokerDao.addInterFaceInvoker(invoker);
		}catch(Exception e){
			debug("数据库异常   添加接口关注者失败");
			return false;
		}
		return flag;
		
	}
	
	/**
	 * 
	 * Title: getInvokerList<br/>
	 * Description: 获取接口关注列表<br/>
	 * @author xiebin
	 * @date 2015年11月23日下午5:01:01
	 *
	 * @param invoker
	 * @return
	 */
	public List<Map<String,Object>> getInvokerList(InterFaceInvoker invoker){
		List<Map<String,Object>> list=null;
		try{
			list=interFaceInvokerDao.getInvokerList(invoker);
		}catch(Exception e){
			debug("数据库异常   获取关注列表失败");
		}
		return list;
	}
	
	
}