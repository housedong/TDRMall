package priv.jesse.mall.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.UserService;
import priv.jesse.mall.utils.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    /**
     * 打开用户列表页面
     * @return
     */
    @RequestMapping("/toList.html")
    public String toList() {
        return "admin/user/list";
    }

    /**
     * 打开编辑页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/toEdit.html")
    public String toEdit(int id, Map<String, Object> map) {
        User user = userService.findById(id);
        map.put("user", user);
        return "admin/user/edit";
    }

    /**
     * 打开新增页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/toAdd.html")
    public String toAdd() {
        //System.out.println("提示到达admin/user/add" );
        return "admin/user/add";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/add.do")
    public void register(String username,
                         Integer department,
                         String name,
                         String phone,
                         String password,
                         String email,
                         String addr,
                         HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        User user = new User();
        user.setDepartment(department);
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setAddr(addr);
        int id = userService.create(user);
        // 注册完成后重定向到登录页面
        if (id <= 0) {
            request.setAttribute("message", "添加失败！");
            request.getRequestDispatcher("toAdd.html").forward(request, response);
        } else {
            request.getRequestDispatcher("toEdit.html?id=" + id).forward(request, response);
        }
    }

    /**
     * 获取所有员工列表
     *
     * @param pageindex
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<User>> findAllUser(int pageindex,
                                              @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        Pageable pageable = new PageRequest(pageindex, pageSize, null);
        List<User> users = userService.findAll(pageable).getContent();
        return new ResultBean<>(users);
    }

    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> geTotal() {
        Pageable pageable = new PageRequest(1, 15, null);
        int total = (int) userService.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    @ResponseBody
    @RequestMapping("/del.do")
    public ResultBean<Boolean> del(int id) {
        userService.delById(id);
        return new ResultBean<>(true);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update.do")
    public ResultBean<Boolean> update(int id,String username,
                                      String password,String name,
                                      String phone,String email,Integer department,
                                      String addr) {
        // 更新前先查询
        User user = userService.findById(id);
        user.setId(id);
        user.setDepartment(department);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setAddr(addr);
        user.setEmail(email);
        user.setPhone(phone);
        userService.update(user);
        return new ResultBean<>(true);
    }
}
