<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
  <div id="searchForm">
   <div class="col-md-2" style="width: 200px">
		<input type="text" class="form-control search-query" name="deviceName" placeholder="设备名称">
	</div>
	<div class="col-md-2" style="padding-bottom: 0px;width: 200px;">
		<select class="form-control" name="status" onchange="javascript:formSubmit();">
			<option value="">==设备状态==</option>
			<option value="1">在线</option>
			<option value="0">离线</option>
		</select>
	</div>
	<div class="col-md-1" style="width: 105px;">
        <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
    </div>
    <div class="col-md-3" style="text-align: left;padding-bottom: unset">
        <button id="addBtn" class="btn btn-labeled btn-primary" onclick="javascript:showModal('添加设备', 'admin/device/addDevicePage/${productId}');"><span class="btn-label icon fa fa-plus"></span>添加设备</button>
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
                {field:"id",text:"ID"},
                {field:"deviceId", text:"设备ID"},
                {field:"deviceName", text:"设备名称"},
                {field:"productId", text:"产品ID"},
                {field:"deviceKey", text:"设备Key"},
                {field:"status", text:"设备状态",formatter:function(index, content, data){
                    return content == 1 ? "<font color='blue'>在线</font>" : "<font color='red'>离线</font>";
                }},
                {field:"creator", text:"创建者"},
                {field:"updator", text:"修改者"},
                {field:"createTime", text:"创建时间"},
                {field:"updateTime", text:"修改时间"},
                {field:"lastTime", text:"最后上线时间"},
                {field:"id", text:"操作", style:"text-align:center", formatter:function(index, content, data){
                	var topicUrl = "admin/device/topicListPage/"+data.productId+"/"+data.deviceName;
                	var editUrl = "admin/device/updateDevicePage/" + content;
                    var delUrl = "admin/device/delDevice/" + content;
                    return "<a href='javascript:goPage(\""+topicUrl+"\")' class='btn btn-xs btn-info'><i class='ace-icon fa fa-th-list'>Topic列表</i></a>"
                        +"&nbsp;<a href='javascript:showModal(\"修改设备信息\", \""+editUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>修改</i></a>"
                        +"&nbsp;<a href='javascript:showCfm(\"确定删除该记录\", \""+delUrl+"\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times'>删除</i></a>";
                }}
            ],	
            cls: "",
            url: _urlPath + "admin/device/queryDevicePage/${productId}",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
</script>