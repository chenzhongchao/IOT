<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
  <div id="searchForm">
  	<div class="col-md-2" style="width: 200px">
		<input type="text" class="form-control search-query" name="productId" placeholder="产品ID">
	</div>

	<div class="col-md-1" style="width: 105px;">
        <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
    </div>
    <div class="col-md-3" style="text-align: left;padding-bottom: unset">
        <button id="addBtn" class="btn btn-labeled btn-primary" onclick="javascript:showModal('定义Topic类', 'admin/message/addMessagePage/${productId}');"><span class="btn-label icon fa fa-plus"></span>定义Topic类</button>
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
                {field:"topicUrl", text:"Topic列表"},
                {field:"operAuth", text:"操作权限",formatter:function(index, content, data){
                	if(content == 1)
                	return "<font color='blue'>订阅</font>";
                	if(content == 2)
                    return "<font color='blue'>发布</font>";
                	if(content == 3)
                    return "<font color='blue'>订阅和发布</font>";
                }},
                {field:"topicDesc", text:"描述"},
                {field:"id", text:"操作", style:"text-align:center", formatter:function(index, content, data){
                    var editUrl = "admin/message/updateMessagePage/" +content;
                    var delUrl = "admin/message/delMessage/" + content;
                    return "<a href='javascript:showModal(\"编辑Topic类\", \""+editUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>编辑</i></a>"
                        + "&nbsp;<a href='javascript:showCfm(\"确定删除该记录\", \""+delUrl+"\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times'>删除</i></a>";
                }}
            ],	
            cls: "",
            url: _urlPath + "admin/message/queryMessagePage/${productId}",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
</script>