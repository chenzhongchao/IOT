package com.fise.iot.mapper;

import java.util.List;

import com.fise.iot.common.dao.MyMapper;
import com.fise.iot.model.AuthRoleOperation;

public interface AuthRoleOperationMapper extends MyMapper<AuthRoleOperation> {

	void batchInsert(List<AuthRoleOperation> list);

	void delRoleOpers(List<AuthRoleOperation> list);
}