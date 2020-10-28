package priv.jesse.mall.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.jesse.mall.entity.Count;
import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.OrderService;
import priv.jesse.mall.service.UserService;
import priv.jesse.mall.service.exception.LoginException;
import priv.jesse.mall.dao.CountDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CountDao countDao;

    /**
     * 打开注册页面
     *
     * @return
     */
    @RequestMapping("/toRegister.html")
    public String toRegister() {
        return "mall/user/register";
    }

    /**
     * 打开登录页面
     *
     * @return
     */
    @RequestMapping("/toLogin.html")
    public String toLogin() {
        return "mall/user/login";
    }

    /**
     * 访问运输部
     *
     * @return
     */
    @RequestMapping("/toTransport.html")
    public String toTransport() {
        return "mall/department/transport";
    }

    /**
     * 访问财务部
     *
     * @return
     */
    @RequestMapping("/toFinance.html")
    public String toFinance() {
        return "mall/department/finance";
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @RequestMapping("/login.do")
    public void login(String username,
                      String password,
                      HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        User user = userService.checkLogin(username, password);
        if (user != null) {
            //登录成功 重定向到首页
            request.getSession().setAttribute("user", user);
            //int department = user.getDepartment();
            System.out.println("getDepartment: "+user.getDepartment());
            if(user.getDepartment() == 1){
                response.sendRedirect("/mall/index.html");
                //return;
                //return;
            }else if(user.getDepartment() == 2){
                response.sendRedirect("/mall/user/toFinance.html");
                // return;
            }else if(user.getDepartment() == 3){
                response.sendRedirect("/mall/user/toTransport.html");
                // return;
            }
        } else {
            throw new LoginException("登录失败！ 用户名或者密码错误");
        }

    }

//    /**
//     * 注册
//     */
//    @RequestMapping("/register.do")
//    public void register(String username,
//                         String password,
//                         String name,
//                         String phone,
//                         String email,
//                         String addr,
//                         HttpServletResponse response) throws IOException {
//        User user = new User();
//        user.setUsername(username);
//        user.setPhone(phone);
//        user.setPassword(password);
//        user.setName(name);
//        user.setEmail(email);
//        user.setAddr(addr);
//        userService.create(user);
//        // 注册完成后重定向到登录页面
//        response.sendRedirect("/mall/user/toLogin.html");
//    }

    /**
     * 登出
     */
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/mall/index.html");
    }

    /**
     * 验证用户名是否唯一
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUsername.do")
    public ResultBean<Boolean> checkUsername(String username){
        List<User> users = userService.findByUsername(username);
        if (users==null||users.isEmpty()){
            return new ResultBean<>(true);
        }
        return new ResultBean<>(false);
    }

    /**
     * 如发生错误 转发到这页面
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/error.html")
    public String error(HttpServletResponse response, HttpServletRequest request) {
        return "error";
    }

    /**
     * 获取所有订单的总数
     * @return
     */
    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> getTotal() {
        Pageable pageable = new PageRequest(1, 10, null);
        int total = (int) orderService.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    /**
     * 获取所有订单
     * @param pageindex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<Order>> listData(int pageindex,
                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Pageable pageable = new PageRequest(pageindex, pageSize, null);
        List<Order> list = orderService.findAll(pageable).getContent();
        return new ResultBean<>(list);
    }

    /**
     * 获取订单项
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDetail.do")
    public ResultBean<List<OrderItem>> getDetail(int orderId) {
        List<OrderItem> list = orderService.findItems(orderId);
        return new ResultBean<>(list);
    }

    /**
     * 发货
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/send.do")
    public ResultBean<Boolean> send(int id) {
        orderService.updateStatus(id,3);
        return new ResultBean<>(true);
    }

    /**
     * 获取每个销售员业绩
     * @return
     */
    @ResponseBody
    @RequestMapping("/getData.do")
    public ResultBean<List<Count>> getSellListData(HttpServletRequest request) {
        List<Count> counts = countDao.findAll();
        System.out.println(counts);
        return new ResultBean<>(counts);
    }
    /**
     * 获取每个销售员昨日业绩
     * @return
     */
    @ResponseBody
    @RequestMapping("/getYesterdayData.do")
    public ResultBean<List<Count>> getYesterdayCount(HttpServletRequest request) {
        List<Count> counts = countDao.getYesterdayCount();
        System.out.println(counts);
        return new ResultBean<>(counts);
    }
    /**
     * 获取每个销售员本周业绩
     * @return
     */
    @ResponseBody
    @RequestMapping("/getWeekCount.do")
    public ResultBean<List<Count>> getWeekCount(HttpServletRequest request) {
        List<Count> counts = countDao.getWeekCount();
        System.out.println(counts);
        return new ResultBean<>(counts);
    }
    /**
     * 获取每个销售员本月业绩
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMonthCount.do")
    public ResultBean<List<Count>> getMonthCount(HttpServletRequest request) {
        List<Count> counts = countDao.getMonthCount();
        System.out.println(counts);
        return new ResultBean<>(counts);
    }
    /**
     * 获取每个销售员本季度业绩
     * @return
     */
    @ResponseBody
    @RequestMapping("/getQuarterCount.do")
    public ResultBean<List<Count>> getQuarterCount(HttpServletRequest request) {
        List<Count> counts = countDao.getQuarterCount();
        System.out.println(counts);
        return new ResultBean<>(counts);
    }


}
