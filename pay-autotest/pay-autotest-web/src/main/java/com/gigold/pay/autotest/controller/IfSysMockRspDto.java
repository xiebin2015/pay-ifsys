/**
 * Title: IfSysMockRspDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import java.util.Map;

import com.gigold.pay.framework.web.ResponseDto;
import com.github.pagehelper.PageInfo;

/**
 * Title: IfSysMockRspDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月2日下午1:36:56
 *
 */
public class IfSysMockRspDto extends ResponseDto {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	PageInfo<Map<String,Object>> pageInfo;
	/**
	 * @return the pageInfo
	 */
	public PageInfo<Map<String, Object>> getPageInfo() {
		return pageInfo;
	}
	/**
	 * @param pageInfo the pageInfo to set
	 */
	public void setPageInfo(PageInfo<Map<String, Object>> pageInfo) {
		this.pageInfo = pageInfo;
	}

	
	

}
