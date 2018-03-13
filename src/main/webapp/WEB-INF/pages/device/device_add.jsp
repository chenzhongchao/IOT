<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal">
	  <div class="form-group">
        <label class="col-sm-3 control-label" for="deviceId"><font color="red">*</font>设备ID：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="deviceId" name="deviceId" placeholder="请填写设备ID"/>
            <div id="validation-deviceId" class="validate-error help-block"></div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="deviceName"><font color="red">*</font>设备名称：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="deviceName" name="deviceName" placeholder="请填写设备名称"/>
            <div id="validation-deviceName" class="validate-error help-block"></div>
        </div>
    </div>
    
   <div class="form-group">
        <label class="col-sm-3 control-label" for="deviceKey"><font color="red">*</font>设备Key：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="deviceKey" name="deviceKey" placeholder="请填写设备Key" />
            <div id="validation-deviceKey" class="validate-error help-block"></div>
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-3 control-label" for="status">设备状态：</label>
        <div class="col-sm-8">
            <div class="radio">
				<label style="display: inline-block; width: 50px;">
					<input checked value="1" name="status" type="radio" class="ace"/>在线
				</label>
				<label style="display: inline-block; width: 50px;">
					<input value="0" name="status" type="radio" class="ace"/>离线
				</label>
			</div>
        </div>
    </div>
</form>
<script type="text/javascript">
	 submit = function(){
		frmValidate();
	    var data = $("#submitForm").serialize();
	    data = data + "&productId=${productId}";
		ajaxRequest("admin/device/addDevice", data);
	}
</script>