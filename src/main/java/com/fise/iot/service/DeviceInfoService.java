package com.fise.iot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.iot.common.annotation.ServiceLog;
import com.fise.iot.common.pojo.AjaxResult;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.common.utils.AppUtil;
import com.fise.iot.common.utils.DateUtil;
import com.fise.iot.common.utils.StringUtil;
import com.fise.iot.common.utils.UserUtil;
import com.fise.iot.mapper.DeviceMapper;
import com.fise.iot.model.Device;
import com.fise.iot.model.DeviceExample;
import com.github.pagehelper.page.PageMethod;

@Service
public class DeviceInfoService extends AbstratService<Device>{
	
	@Autowired
	private DeviceMapper deviceMapper;

	@ServiceLog("查询产品列表")
	public PageAjax<Device> queryDevicePage(PageAjax<Device> page, Device device,String productId) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		
		DeviceExample example=new DeviceExample();
		DeviceExample.Criteria criteria=example.createCriteria();
		criteria.andStatusNotEqualTo(2);
		criteria.andCreatorEqualTo(UserUtil.getCurrentUserName());
		if(!StringUtil.isEmpty(productId)){
			criteria.andProductIdEqualTo(productId);
		}
		if(!StringUtil.isEmpty(device.getDeviceName())){
			criteria.andDeviceNameLike("%" + device.getDeviceName()+ "%");
		}
		if(null!=device.getStatus()){
			criteria.andStatusEqualTo(device.getStatus());
		}
		List<Device> list = deviceMapper.selectByExample(example);
		return AppUtil.returnPage(list);
	}
	
	public Device queryDeviceByID(int id) {
		return deviceMapper.selectByPrimaryKey(id);
	}
	
	@ServiceLog("更新产品")
	public AjaxResult updateDevice(Device device) {
		device.setUpdateTime(DateUtil.getCurDateTime());
		deviceMapper.updateByPrimaryKeySelective(device);
		return AppUtil.returnObj(null);
	}
	
	@ServiceLog("删除设备")
	public AjaxResult delDevice(int id) {
		Device device = queryDeviceByID(id);
		if(null != device){
			device.setStatus(2);
			deviceMapper.updateByPrimaryKeySelective(device);
		}
		return AppUtil.returnObj(null);
	}
	
	
	@ServiceLog("新增设备")
	public AjaxResult addDevice(Device device) {
		//product.setAddtime(DateUtil.getCurDateTime());
		device.setCreator(UserUtil.getCurrentUserName());
		device.setUpdator(UserUtil.getCurrentUserName());
		device.setCreateTime(DateUtil.getCurDateTime());
		device.setUpdateTime(DateUtil.getCurDateTime());
		device.setLastTime(DateUtil.getCurDateTime());
		deviceMapper.insert(device);
		return AppUtil.returnObj(null);
	}

}
