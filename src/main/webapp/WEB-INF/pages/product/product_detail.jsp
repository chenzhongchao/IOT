<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="content-wrapper">
	<div class="col-sm-3 panel panel-success" style="padding: unset;" align="center">
		<div class="panel-heading">
			<h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">产品信息</a></h4>
		</div>
		<div id="collapseOne" class="panel-collapse collapse in" >
			<div class="panel-body" style="padding: unset; margin: unset">
				<table class="table-condensed" style="padding: unset; margin: unset">
					<tr class="active"><td class="text-right">产品ID：</td><td>${product.productId}</td></tr>
					<tr class="active"><td class="text-right">产品名称：</td><td>${product.productName}</td></tr>
					<tr class="active"><td class="text-right">产品key：</td><td>${product.productKey}</td></tr>
					<tr class="active"><td class="text-right">产品描述：</td><td>${product.productDesc}</td></tr>
				</table>
			</div>
		</div>
	</div>
</div>

