<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal" enctype="multipart/form-data" method="post">
	<input name="id" value="${topic.id}" type="text" hidden="hidden">
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="topicUrl"><font color="red">*</font>Topic类：</label>
		<div class="col-sm-8">
			<input type="text" style="border:0px ;display: inline-block; width:70%" id="prefix" name="prefix" value="${prefix}" class="form-control" readyonly/>
		    <input type="text" style="display: inline-block; width:25%" id="suffix" name="suffix" value="${suffix}" class="form-control" />
		</div>
	</div>
	
	<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="operAuth"><font color="red">*</font>设备权限： </label>
		<div class="col-sm-5">
		<select class="form-control" name="operAuth" onchange="javascript:formSubmit();">
			<option value="">==请选择操作权限==</option>
			<option value="1">订阅</option>
			<option value="2">发布</option>
			<option value="3">发布和订阅</option>
		</select>
	   </div>
	</div>
	
	<div class="form-group">
        <div class="col-sm-12">
        	<label class="col-sm-3 control-label" for="productName"></label>
            <div style="font-size: 14px" class="validation-productKey col-sm-8">1.Topic格式必须以“/”进行分层，区分每个类目。其中前两个类目已经规定好，第一个代表产品标识ProductKey，第二个类目${deviceName}通配deviceName。简单来说，Topic类：/pk/${deviceName}/update是具体Topic:/pk/mydevice/update或者/pk/yourdevice/update的集合</br>
            	2.只能包含字母，数字和下划线（_）命名每级类目，每级类目不能为空</br>
            	3.输入的Topic类长度不能超过64字节
            </div>
        </div>
    </div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="topicDesc">Topic描述：</label>
		<div class="col-sm-8">
			<input type="text" id="topicDesc" name="topicDesc" value="${topic.topicDesc}" class="form-control" />
		</div>
	</div>
</form>

<script type="text/javascript">
	submit = function(){
		frmValidate();
		var data = $("#submitForm").serialize();
		ajaxRequest("admin/message/updateMessage", data);
	}
</script>
