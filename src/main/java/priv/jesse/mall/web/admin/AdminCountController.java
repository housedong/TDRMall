package priv.jesse.mall.web.admin;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.jesse.mall.dao.CountDao;
import priv.jesse.mall.dao.OrderDao;
import priv.jesse.mall.dao.UserDao;
import priv.jesse.mall.entity.Count;
import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/count")
public class AdminCountController {
    @Autowired
    private CountDao countDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

     /**
     * 打开销量统计首页
     * @return
     */
    @RequestMapping("/toList.html")
    public String toIndex() {
        return "admin/count/list";
    }

    /**
     * 定时记录每日的销售情况
     */
    public  void saveSellData() {
        List<User> users= userDao.findByDepartment();
        System.out.println("users: " + users);
        for (User user : users) {
            Count count = new Count();
            double one_sell_count = 0.0;
            double one_cost_count = 0.0;
            double one_earnings_count = 0.0;
           // String date ;
            count.setTotal(one_sell_count);
            count.setCost(one_cost_count);
            count.setEarnings(one_earnings_count);
            List<Order> orders = orderDao.getYesterDaycount(user.getId());
            for (Order order : orders) {

                one_sell_count = one_sell_count + order.getTotal();
                one_cost_count = one_cost_count + order.getCost();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println(dateFormat.format(order.getOrderTime()));
               // date = dateFormat.format(order.getOrderTime());

            }
            one_earnings_count = one_sell_count-one_cost_count;
            count.setTotal(one_sell_count);
            count.setCost(one_cost_count);
            count.setEarnings(one_earnings_count);
            count.setUser_id(user.getId());
            count.setUser_name(user.getName());
            count.setTime(new Date());
            countDao.save(count);
            System.out.println("销售总价: " + one_sell_count +"家电成本:"+one_cost_count+"收益："+one_earnings_count);
        }
    }

    /**
     * 获取每个销售员业绩
     * @return
     */
    @ResponseBody
    @RequestMapping("/getData.do")
    public ResultBean<List<Count>> getSellListData(HttpServletRequest request) {
//        List<User> users= UserDao.findByDepartment();
//        System.out.println("users: " + users);
//        for (User user : users) {
//            Count count = new Count();
//            double one_sell_count = 0.0;
//            double one_cost_count = 0.0;
//            double one_earnings_count = 0.0;
//           // String date ;
//            count.setTotal(one_sell_count);
//            count.setCost(one_cost_count);
//            count.setEarnings(one_earnings_count);
//            List<Order> orders = orderDao.getYesterDaycount(user.getId());
//            for (Order order : orders) {
//
//                one_sell_count = one_sell_count + order.getTotal();
//                one_cost_count = one_cost_count + order.getCost();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                System.out.println(dateFormat.format(order.getOrderTime()));
//               // date = dateFormat.format(order.getOrderTime());
//
//            }
//            one_earnings_count = one_sell_count-one_cost_count;
//            count.setTotal(one_sell_count);
//            count.setCost(one_cost_count);
//            count.setEarnings(one_earnings_count);
//            count.setUser_id(user.getId());
//            count.setUser_name(user.getName());
//            count.setTime(new Date());
//            countDao.save(count);
//            System.out.println("销售总价: " + one_sell_count +"家电成本:"+one_cost_count+"收益："+one_earnings_count);
//        }
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
