<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
  <div id="searchForm">
  	<div class="col-md-2" style="width: 200px">
		<input type="text" class="form-control search-query" name="deviceName" placeholder="设备名称">
	</div>
	<div class="col-md-2" style="width: 200px">
		<input type="text" class="form-control search-query" name="messageId" placeholder="messageId">
	</div>
	
	<div class="col-md-2" style="padding-bottom: 0px;width: 200px;">
		<select class="form-control" name="type" onchange="javascript:formSubmit();">
			<option value="">==消息类型==</option>
			<option value="1">上行</option>
			<option value="2">下行</option>
		</select>
	</div>
	
	<div class="col-md-2" style="padding-bottom: 0px;width: 200px;">
		<select class="form-control" name="time" onchange="javascript:formSubmit();">
			<option value="">==创建时间==</option>
			<option value="0">一天内</option>
			<option value="1">一个星期内</option>
			<option value="2">一个月内</option>
		</select>
	</div>
	
	<div class="col-md-1" style="width: 105px;">
        <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
    </div>
    
  </div>
</div>
<div class="openAppGrid">
	<div id="openAppGrid"></div>
</div>
<script type="text/javascript">
    $(function (){
        $("#openAppGrid").sgrid({
            columns:[
                {field:"productId",text:"产品ID"},
                {field:"deviceName", text:"设备名称"},
                {field:"messageId", text:"messageId"},
                {field:"type", text:"消息类型",formatter:function(index, content, data){
                    return content == 1 ? "<font color='blue'>上行</font>" : "<font color='red'>下行</font>";
                }},
                {field:"detail", text:"内容"},
                {field:"statusDesc", text:"状态以及原因分析"},
                {field:"createTime", text:"创建时间"}
            ],	
            cls: "",
            url: _urlPath + "admin/log/queryDeviceLogPage/${productId}",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
</script>