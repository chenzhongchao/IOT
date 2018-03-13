<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal">
	  <div class="form-group">
        <label class="col-sm-3 control-label" for="productId"><font color="red">*</font>产品ID：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="productId" name="productId" placeholder="请填写产品ID"/>
            <div id="validation-productId" class="validate-error help-block"></div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="productName"><font color="red">*</font>产品名称：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="productName" name="productName" placeholder="请填写产品名称"/>
            <div id="validation-productName" class="validate-error help-block"></div>
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-3 control-label" for="productKey"><font color="red">*</font>productKey：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" style="border:0px" id="productKey" name="productKey" value="${productKey}" readonly/><font color="red">(已生成唯一的productKey)</font>
            <div id="validation-productKey" class="validate-error help-block"></div>
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-3 control-label" for="productDesc">产品描述：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="productDesc" name="productDesc" placeholder="请填写产品描述" />
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-3 control-label" for="status">是否可用：</label>
        <div class="col-sm-8">
            <div class="radio">
				<label style="display: inline-block; width: 50px;">
					<input checked value="1" name="status" type="radio" class="ace"/>是
				</label>
				<label style="display: inline-block; width: 50px;">
					<input value="0" name="status" type="radio" class="ace"/>否
				</label>
			</div>
        </div>
    </div>
</form>
<script type="text/javascript">
	 submit = function(){
		frmValidate();
	    var data = $("#submitForm").serialize();
		ajaxRequest("admin/product/addProduct", data);
	}
</script>