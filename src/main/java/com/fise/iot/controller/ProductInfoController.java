package com.fise.iot.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fise.iot.common.annotation.Authority;
import com.fise.iot.common.annotation.ControllerLog;
import com.fise.iot.common.pojo.AjaxResult;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.model.Product;
import com.fise.iot.service.ProductInfoService;

/**
 *产品基本信息controller
 */
@Controller
@RequestMapping("/admin/product/")
public class ProductInfoController {
	/**日志*/
	public static Logger logger = LoggerFactory.getLogger(ProductInfoController.class);
	
	@Autowired
	private ProductInfoService productService;
	
	@Authority(opCode = "0401", opName = "产品基本信息界面")
	@RequestMapping("productinfoPage")
	public String productPage() {
		return "product/product_info";
	}
	
	@ControllerLog("查询产品列表")
	@RequestMapping("queryProductPage")
	@ResponseBody
	@Authority(opCode = "0401", opName = "查询产品列表")
	public PageAjax<Product> queryProductPage(PageAjax<Product> page, Product product) {
		return productService.queryProductPage(page, product);
	}
	
	@Authority(opCode = "040102", opName = "更新产品页面")
	@RequestMapping("updateInfoPage/{id}")
	public String updateInfoPage(@PathVariable("id") int id, Map<String, Object> map) {
		Product product = productService.queryProductByID(id);
		map.put("product", product);
		return "product/product_update";
	}

	@ControllerLog("修改产品")
	@RequestMapping("updateProduct")
	@ResponseBody
	@Authority(opCode = "040102", opName = "修改产品")
	public AjaxResult updateProduct(Product product) {
		return productService.updateProduct(product);
	}
	
	@ControllerLog("删除产品")
	@RequestMapping("delProduct/{id}")
	@ResponseBody
	@Authority(opCode = "040103", opName = "删除产品")
	public AjaxResult delProduct(@PathVariable("id") int id) {
		return productService.delProduct(id);
	}
	
	@Authority(opCode = "040104", opName = "添加产品页面")
	@RequestMapping("addProductPage")
	public String addProductPage(Map<String, Object> map) {
		//生成唯一的productKey
		String productKey=productService.createProductKey();
		map.put("productKey", productKey);
		return "product/product_add";
	}

	@ControllerLog("添加产品")
	@RequestMapping("addProduct")
	@ResponseBody
	@Authority(opCode = "040104", opName = "添加产品")
	public AjaxResult addProduct(Product product) {
		return productService.addProduct(product);
	}
	
	@Authority(opCode = "040105", opName = "产品菜单界面")
	@RequestMapping("productMenuPage/{productId}/{pageName}")
	public String productMenu(@PathVariable("productId") String productId, 
							  @PathVariable("pageName") String pageName,
							  Map<String, Object> map) {
		map.put("productId", productId);
		map.put("pageName", pageName);
		return "product/product_menu";
	}
	
	@Authority(opCode = "040106", opName = "产品详细")
	@RequestMapping("productDetail/{productId}")
	public String productDetail(@PathVariable("productId") String productId, Map<String, Object> map) {
		Product product = new Product();
		product.setProductId(productId);
		List<Product> list = productService.queryList(product);
		product = list.get(0);
		map.put("product", product);
		return "product/product_detail";
	}

}
