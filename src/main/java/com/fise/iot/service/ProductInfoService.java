package com.fise.iot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.iot.common.annotation.ServiceLog;
import com.fise.iot.common.pojo.AjaxResult;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.common.utils.AppUtil;
import com.fise.iot.common.utils.DateUtil;
import com.fise.iot.common.utils.StringUtil;
import com.fise.iot.common.utils.UserUtil;
import com.fise.iot.mapper.ProductMapper;
import com.fise.iot.model.Product;
import com.fise.iot.model.ProductExample;
import com.github.pagehelper.page.PageMethod;

@Service
public class ProductInfoService extends AbstratService<Product> {
	
	@Autowired
	private ProductMapper productMapper;

	@ServiceLog("查询产品列表")
	public PageAjax<Product> queryProductPage(PageAjax<Product> page, Product product) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		
		ProductExample example=new ProductExample();
		ProductExample.Criteria criteria=example.createCriteria();
		criteria.andStatusNotEqualTo(2);
		criteria.andCreatorEqualTo(UserUtil.getCurrentUserName());
		if(!StringUtil.isEmpty(product.getProductName())){
			criteria.andProductNameLike("%" + product.getProductName()+ "%");
		}
		if(null!=product.getStatus()){
			criteria.andStatusEqualTo(product.getStatus());
		}
		List<Product> list = productMapper.selectByExample(example);
		return AppUtil.returnPage(list);
	}
	
	public Product queryProductByID(int id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@ServiceLog("更新产品")
	public AjaxResult updateProduct(Product product) {
		product.setUpdator(UserUtil.getCurrentUserName());
		product.setUpdateTime(DateUtil.getCurDateTime());
		productMapper.updateByPrimaryKeySelective(product);
		return AppUtil.returnObj(null);
	}
	
	@ServiceLog("删除产品")
	public AjaxResult delProduct(int id) {
		Product product = queryProductByID(id);
		if(null != product){
			product.setStatus(2);
			productMapper.updateByPrimaryKeySelective(product);
		}
		return AppUtil.returnObj(null);
	}
	
	@ServiceLog("新增产品")
	public AjaxResult addProduct(Product product) {
		//product.setAddtime(DateUtil.getCurDateTime());
		
		product.setCreator(UserUtil.getCurrentUserName());
		product.setUpdator(UserUtil.getCurrentUserName());
		product.setCreateTime(DateUtil.getCurDateTime());
		product.setUpdateTime(DateUtil.getCurDateTime());
		productMapper.insert(product);
		return AppUtil.returnObj(null);
	}
	
	public String createProductKey(){
		String productKey="";
	    List<Product> products=	productMapper.selectAll();
	    while(true){
	    	productKey=StringUtil.makeCode(6, false);
	    	for(Product product:products){
	    		if(product.getProductKey().equals(productKey)){
	    			continue;
	    		}
	    	}
	    	break;
	    }
		return productKey;
	}
	
	public String getProductIdByKey(String productKey) {
		ProductExample example=new ProductExample();
		ProductExample.Criteria criteria=example.createCriteria();
		if(!StringUtil.isEmpty(productKey)) {
			criteria.andProductKeyEqualTo(productKey);
		}
		List<Product> products =productMapper.selectByExample(example);
		if(products.size()==0) {
			return "";
		}
		Product product=products.get(0);
		String productId=product.getProductId();
		return productId;
	}
}
