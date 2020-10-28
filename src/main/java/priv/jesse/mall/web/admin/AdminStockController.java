package priv.jesse.mall.web.admin;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.ClassificationService;
import priv.jesse.mall.service.ProductService;
import priv.jesse.mall.utils.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/stock")
public class AdminStockController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ClassificationService classificationService;

    /**
     * 打开库存首页
     * @return
     */
    @RequestMapping("/toList.html")
    public String toIndex() {
        return "admin/stock/list";
    }

    @RequestMapping("/toEdit.html")
    public String toEdit(int id, Map<String, Object> map) {
        Product product = productService.findById(id);
        Classification classification = classificationService.findById(product.getCsid());
        product.setCategorySec(classification);
        map.put("product", product);

        return "admin/stock/edit";

    }

    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<Product>> listProduct(int pageindex,
                                                 @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        Pageable pageable = new PageRequest(pageindex, pageSize, null);
        List<Product> list = productService.findAll(pageable).getContent();
        return new ResultBean<>(list);
    }

    @ResponseBody
    @RequestMapping("/getTotal")
    public ResultBean<Integer> getTotal() {
        Pageable pageable = new PageRequest(1, 15, null);
        int total = (int) productService.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update.do")
    public void update(int id,
                       Integer stock,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        Product product = productService.findById(id);
        System.out.println("提示到达库存stock"+stock );
        int Stock =0;
        Stock=product.getStock()+stock;
        product.setStock(Stock);
        boolean flag = false;
        try {
            productService.update(product);
            flag = true;
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (!flag) {
            request.setAttribute("message", "更新失败！");
        }
        response.sendRedirect("toList.html");
    }



}
