/**
 * Title: SimpleThreadPool.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.threadpool;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.service.IfSysMockService;
import com.github.pagehelper.PageInfo;

/**
 * Title: SimpleThreadPool<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月27日下午5:46:16
 *
 */
@Service
public class IfsysCheckThreadPool {
	@Autowired
	IfSysMockService ifSysMockService;
	// 获取核心数
	private static final int CPUCORECOUNT = Runtime.getRuntime().availableProcessors();
	private static final ExecutorService executor = Executors.newFixedThreadPool(CPUCORECOUNT + 1);

	public void execure() {
		// 当前页
		int curPageNum = 1;
		// 总页数
		int pages = 1;
		while (curPageNum <= pages) {
			PageInfo<Map<String, Object>> pageInfo = ifSysMockService.getAllIfSys(curPageNum);
			List<Map<String, Object>> ifsyslist = pageInfo.getList();
			// 创建线程
			Runnable worker = new CheckThread(ifSysMockService, ifsyslist);
			executor.execute(worker);
			pages = pageInfo.getPages();
			curPageNum++;
		}
		executor.shutdown();
		while (!executor.isTerminated()) { //
		}

	}

}