package com.imooc.controller;

import com.imooc.VO.ProductInfoVO;
import com.imooc.VO.ProductVO;
import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 买家商品
 * Created by 廖师兄
 * 2017-05-12 14:08
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private  ProductService productService;

    @Autowired
    private  CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //查询类目（一次性查询
        List<Integer> categoryTypeList = new ArrayList<Integer>();
        List<ProductCategory>  categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //数据拼装

        ResultVO resultVO =new ResultVO();
        ProductVO productVO =new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();
        ProductInfoVO productInfoVO1 = new ProductInfoVO();

        resultVO.setCode(1);
        resultVO.setMsg("成功");
        resultVO.setData(Arrays.asList(productVO));

        productVO.setCategoryName("热榜");
        productVO.setCategoryType(1);
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO,productInfoVO1));

        productInfoVO.setProductId("123456");
        productInfoVO.setProductName("皮蛋粥");
     //   productInfoVO.setProductPrice(1.2f);
        productInfoVO.setProductDescription("好吃的皮蛋粥");
        productInfoVO.setProductIcon("http://ww1.sinaimg.cn/mw690/bfdcef89gw1f9cf7iwi24j20h802sjrz.jpg");
        return resultVO;
    }
}
