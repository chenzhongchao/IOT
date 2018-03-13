<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
  <div id="searchForm">
  	<div class="col-md-2" style="width: 200px">
		<input type="text" class="form-control search-query" name="productName" placeholder="产品名称">
	</div>
	<div class="col-md-2" style="padding-bottom: 0px;width: 200px;">
		<select class="form-control" name="status" onchange="javascript:formSubmit();">
			<option value="">==是否可用==</option>
			<option value="1">可用</option>
			<option value="0">不可用</option>
		</select>
	</div>
	<div class="col-md-1" style="width: 105px;">
        <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
    </div>
    <div class="col-md-3" style="text-align: left;padding-bottom: unset">
        <button id="addBtn" class="btn btn-labeled btn-primary" onclick="javascript:showModal('添加产品', 'admin/product/addProductPage');"><span class="btn-label icon fa fa-plus"></span>添加产品</button>
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
                {field:"productId",text:"产品ID", formatter:function(index, content, data){
                	var goUrl='admin/product/productMenuPage/'+content + '/product';
                    return "<a style='text-decoration:underline' tabindex='-1' href=javascript:goPage(\""+goUrl+"\")>" +content+ "</a>";
                }},
                {field:"productName", text:"产品名称"},
                {field:"productKey", text:"productKey"},
                {field:"productDesc", text:"产品描述"},
                {field:"status", text:"是否可用",formatter:function(index, content, data){
                    return content == 1 ? "<font color='blue'>可用</font>" : "<font color='red'>不可用</font>";
                }},
                {field:"creator", text:"创建者"},
                {field:"updator", text:"修改者"},
                {field:"createTime", text:"创建时间"},
                {field:"updateTime", text:"更新时间"},
                {field:"id", text:"操作", style:"text-align:center", formatter:function(index, content, data){
                    var editUrl = "admin/product/updateInfoPage/" + content;
                    var delUrl = "admin/product/delProduct/" + content;
                    return "<a href='javascript:showModal(\"修改产品信息\", \""+editUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>修改</i></a>"
                        + "&nbsp;<a href='javascript:showCfm(\"确定删除该记录\", \""+delUrl+"\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times'>删除</i></a>";
                }}
            ],	
            cls: "",
            url: _urlPath + "admin/product/queryProductPage",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
</script>