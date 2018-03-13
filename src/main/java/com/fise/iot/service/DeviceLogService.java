package com.fise.iot.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.common.utils.AppUtil;
import com.fise.iot.common.utils.DateUtil;
import com.fise.iot.common.utils.StringUtil;
import com.fise.iot.mapper.DeviceLogMapper;
import com.fise.iot.model.DeviceLog;
import com.fise.iot.model.DeviceLogExample;
import com.github.pagehelper.page.PageMethod;

@Service
public class DeviceLogService extends AbstratService<DeviceLog> {

	@Autowired
	private DeviceLogMapper logMapper;

	public PageAjax<DeviceLog> queryDeviceLogPage(PageAjax<DeviceLog> page, DeviceLog deviceLog, String time) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		DeviceLogExample example = new DeviceLogExample();
		DeviceLogExample.Criteria criteria = example.createCriteria();
		if(!StringUtil.isEmpty(deviceLog.getProductId())){
			criteria.andProductIdEqualTo(deviceLog.getProductId());
		}
		if (!StringUtil.isEmpty(deviceLog.getDeviceName())) {
			criteria.andDeviceNameLike("%" + deviceLog.getDeviceName() + "%");
		}
		if (!StringUtil.isEmpty(deviceLog.getMessageId())) {
			criteria.andMessageIdEqualTo(deviceLog.getMessageId());
		}
		if (!StringUtil.isEmpty(deviceLog.getMessageId())) {
			criteria.andMessageIdEqualTo(deviceLog.getMessageId());
		}

		if (deviceLog.getType() != null) {
			criteria.andTypeEqualTo(deviceLog.getType());
		}
		String currentTime = DateUtil.getCurDateTime();
		if(!StringUtil.isEmpty(time)){
		if (time.equals("0")) {
			// 前一天的当前时间
			String oneDay = DateUtil.getBeforeDate(Calendar.DAY_OF_MONTH, -1);
			criteria.andCreateTimeBetween(oneDay, currentTime);
		}

		if (time.equals("1")) {
			//一个星期前的当前时间
		    String oneWeek = DateUtil.getBeforeDate(Calendar.DAY_OF_MONTH, -7);
			criteria.andCreateTimeBetween(oneWeek, currentTime);
		}
		if (time.equals("2")) {
			//一个月前的当前时间
		    String oneMonth = DateUtil.getBeforeDate(Calendar.MONTH, -1);
			criteria.andCreateTimeBetween(oneMonth, currentTime);
		}
		}
		example.setOrderByClause("create_time desc");
		List<DeviceLog> list = logMapper.selectByExample(example);
		return AppUtil.returnPage(list);
	}

}
