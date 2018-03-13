<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
  <div id="searchForm">
	  <div class="col-md-3" style="text-align: left;padding-bottom: unset">
        <button id="backBtn" class="btn btn-labeled btn-primary" onclick="javascript:goPage('admin/product/productMenuPage/${productId}/device');">返回上一页</button>
     </div>
  </div>
</div>
<div class="openAppGrid">
	<div id="openAppGrid">
	</div>
</div>
<script type="text/javascript">
    $(function (){
        $("#openAppGrid").sgrid({
            columns:[
                {field:"topicUrl", text:"设备的Topic",sortColumn:"topicUrl"},
                {field:"operAuth",sortColumn:"operAuth",text:"设备具有的权限",formatter:function(index, content, data){
                	if(content == 1)
                	return "<font color='blue'>订阅</font>";
                	if(content == 2)
                    return "<font color='blue'>发布</font>";
                	if(content == 3)
                    return "<font color='blue'>订阅和发布</font>";
                }},
                {field:"messageNum",sortColumn:"messageNum", text:"发布消息数"},
                {field:"id", text:"操作", style:"text-align:center", formatter:function(index, content, data){
                	var publishUrl = "admin/device/devicePublishPage/" + content + "?topicUrl="+data.topicUrl;
                    return "<a href='javascript:showModal(\"发布消息\", \""+publishUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>发布消息</i></a>";
                }}
            ],	
            cls: "",
            url: _urlPath + "admin/device/topicResultPage/${productId}/${deviceName}",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
</script>
