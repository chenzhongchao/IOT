<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div >
	
	<ul id="profile-tabs" class="nav nav-tabs">
        <li  >
        <a class="list-group-item", 
				href="javascript:loadOperations('admin/product/productDetail/${productId}')"><h5 class="list-group-item-heading">基本信息</h5></a> 
        </li>
        <li  >
        <a class="list-group-item", 
				href="javascript:loadOperations('admin/device/deviceinfoPage/${productId}')"><h5 class="list-group-item-heading">设备管理</h5></a> 
        </li>
        <li  >
        <a class="list-group-item", 
				href="javascript:loadOperations('admin/message/messageinfoPage/${productId}')"><h5 class="list-group-item-heading">消息通信</h5></a>
        </li>
        <li  >
        <a  class="list-group-item",
				href="javascript:loadOperations('admin/log/deviceLogPage/${productId}')"><h5 class="list-group-item-heading">日志服务</h5></a>
        </li>

    </ul>
   <button id="backBtn" class="btn btn-labeled btn-primary" onclick="javascript:goPage('admin/product/productinfoPage');" style="float: right;">返回产品页</button>
	
</div>


<br></br>
<div >
	<div id="pageShow">
	
	</div>
</div>

 <script type="text/javascript">
 		var _urlPath = "${ctx}/";
        //点击加载
        loadOperations = function(url){
            $.ajax({
        		url : _urlPath + url,
        		type : "post",
        		dataType : 'html',
        		success : function(req) {
        			$("#pageShow").html(req);
        		},
        		error : function(req) {
        			if (404 == req.status) {
        				goPage(_urlPath + "/404");
        			} else {
        				goPage(_urlPath + "/500");
        			}
        		}
        	});
        }
        
        $(function(){
        	
        	if('${pageName}' == 'product'){
        		loadOperations('admin/product/productDetail/${productId}');
        	};
        	if('${pageName}' == 'device'){
        		loadOperations('admin/device/deviceinfoPage/${productId}');
        	};
        });

    </script>