<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal">
      <div class="col-sm-10 ">
		<span style="color: black; font-size:12px">注意：如果该Topic正在被应用使用，请谨慎操作，以防应用出现异常。</span>
	  </div>
	  <div class="form-group">
        <label class="col-sm-3 control-label" for="topicUrl"><font color="red">*</font>topic：</label>
        <div class="col-sm-8">
            <input class="form-control" style="border:0px" type="text" id="topicUrl" name="topicUrl" value="${topicUrl}" readonly/>
            <div id="validation-deviceId" class="validate-error help-block"></div>
        </div>
      </div>
      
     <div class="form-group">
        <label class="col-sm-3 control-label" for="content"><font color="red">*</font>消息内容：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="content" name="content"/>
            <div id="validation-content" class="validate-error help-block"></div>
        </div>
     </div>
    
   <div class="form-group">
        <label class="col-sm-3 control-label" for="status">Qos：</label>
        <div class="col-sm-8">
            <div class="radio">
				<label style="display: inline-block; width: 50px;">
					<input checked value="0" name="qos" type="radio" class="ace"/>0
				</label>
				<label style="display: inline-block; width: 50px;">
					<input value="1" name="qos" type="radio" class="ace"/>1
				</label>
			</div>
        </div>  
    </div>
</form>
<script type="text/javascript">
	 submit = function(){
		frmValidate();
	    var data = $("#submitForm").serialize();
		ajaxRequest("admin/device/devicePublish/"+"${id}", data);
	}
</script>