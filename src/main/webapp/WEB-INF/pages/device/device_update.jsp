<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal" enctype="multipart/form-data" method="post">
	<input name="id" value="${device.id}" type="text" hidden="hidden">
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="deviceId"><font color="red">*</font>设备ID</label>
		<div class="col-sm-8">
			<input type="text" id="deviceId" name="deviceId" value="${device.deviceId}" class="form-control" />
		</div>
	</div> 
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="deviceName"><font color="red">*</font>设备名称</label>
		<div class="col-sm-5">
			<input type="text" id="deviceName" name="deviceName" value="${device.deviceName}" class="form-control" />
		    <span style="color: black; font-size:12px">支持中文、英文字母、数字和下划线，长度限制4~30，中文算2位</span>
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="deviceKey">设备Key</label>
		<div class="col-sm-8">
			<input type="text" id="deviceKey" name="deviceKey" value="${device.deviceKey}" class="form-control" readonly/>
		</div>
	</div>
	
	 <div class="form-group">
        <label class="col-sm-3 control-label" for="status">设备状态：</label>
        <div class="col-sm-8">
            <div class="radio">
				<label style="display: inline-block; width: 50px;">
					<input value="1" name="status" type="radio" class="ace" <c:if test="${1 == device.status}">checked</c:if> />在线     
				</label>
				<label style="display: inline-block; width: 50px;">
					<input value="0" name="status" type="radio" class="ace" <c:if test="${0 == device.status}">checked</c:if> />离线          
				</label>
			</div>
        </div>
    </div>

</form>

<script type="text/javascript">
	submit = function(){
		frmValidate();
		var data = $("#submitForm").serialize();
		ajaxRequest("admin/product/updateProduct", data);
	}
</script>
