package com.fise.iot.mapper;

import org.apache.ibatis.annotations.Param;

import com.fise.iot.common.dao.MyMapper;
import com.fise.iot.model.AuthRole;

public interface AuthRoleMapper extends MyMapper<AuthRole> {

	AuthRole queryRoleById(@Param("roleid")Integer roleid);
	
	AuthRole queryByRolename(@Param("rolename") String rolename);
}