<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal" enctype="multipart/form-data" method="post">
	<input name="id" value="${product.id}" type="text" hidden="hidden">
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="productName"><font color="red">*</font>产品名称</label>
		<div class="col-sm-5">
			<input type="text" id="productName" name="productName" value="${product.productName}" class="form-control" />
		    <span style="color: black; font-size:12px">支持中文、英文字母、数字和下划线，长度限制4~30，中文算2位</span>
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="productDesc">产品描述</label>
		<div class="col-sm-8">
			<input type="text" id="productDesc" name="productDesc" value="${product.productDesc}" class="form-control" />
		</div>
	</div>
	
	 <div class="form-group">
        <label class="col-sm-3 control-label" for="status">是否可用：</label>
        <div class="col-sm-8">
            <div class="radio">
				<label style="display: inline-block; width: 50px;">
					<input value="1" name="status" type="radio" class="ace" <c:if test="${1 == product.status}">checked</c:if> />是        
				</label>
				<label style="display: inline-block; width: 50px;">
					<input value="0" name="status" type="radio" class="ace" <c:if test="${0 == product.status}">checked</c:if> />否          
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
