package priv.jesse.mall.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Iterator;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 打开订单列表页面
     *
     * @return
     */
    @RequestMapping("/toList.html")
    public String toOrderList() {
        return "mall/order/list";
    }

    /**
     * 查询订单列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public ResultBean<List<Order>> listData(HttpServletRequest request) {

        List<Order> orders = orderService.findUserOrder(request);

        return new ResultBean<>(orders);
    }

    /**
     * 打开订单列表页面
     *
     * @return
     */
    @RequestMapping("/toCount.html")
    public String toOneCount() {
        return "mall/order/count";
    }

    /**
     * 查询日销售金额
     *
     * @param request
     * @return
     */
    @RequestMapping("/day_count.do")
    @ResponseBody
    public ResultBean<List<Order>> GetDayCount(HttpServletRequest request) {
//        double one_sell_count = 0;
//        List<Order> orders = orderService.findUserOrder(request);
//        for (Order order : orders) {
//            one_sell_count = one_sell_count + order.getTotal();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            System.out.println(dateFormat.format(order.getOrderTime()));
//        }
////        Iterator it1 = orders.iterator();
////         while (it1.hasNext()) {
////           //  tt = Double.parseDouble(tt + it1.next().total.toString());
////             Order order = (Order) it1.next();
////             tt2 = tt2+ order.getTotal();
////         }
//        System.out.println("one_sell_count: " + one_sell_count);
        List<Order> counts = orderService.getOneDayCount(request);
        System.out.println("day_counts: " + counts);
        return new ResultBean<>(counts);
    }
    /**
     * 查询周销售金额
     *
     * @param request
     * @return
     */
    @RequestMapping("/week_count.do")
    @ResponseBody
    public ResultBean<List<Order>> GetWeekCount(HttpServletRequest request) {
        List<Order> counts = orderService.getOneWeekCount(request);
        System.out.println("week_counts: " + counts);
        return new ResultBean<>(counts);
    }
    /**
     * 查询月销售金额
     *
     * @param request
     * @return
     */
    @RequestMapping("/month_count.do")
    @ResponseBody
    public ResultBean<List<Order>> GetMonthCount(HttpServletRequest request) {
        List<Order> counts = orderService.getOneMonthCount(request);
        System.out.println("counts: " + counts);
        return new ResultBean<>(counts);
    }
    /**
     * 查询季度销售金额
     *
     * @param request
     * @return
     */
    @RequestMapping("/quarter_count.do")
    @ResponseBody
    public ResultBean<List<Order>> GetQuarterCount(HttpServletRequest request) {
        List<Order> counts = orderService.getOneQuarterCount(request);
        System.out.println("counts: " + counts);
        return new ResultBean<>(counts);
    }
    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/getDetail.do")
    @ResponseBody
    public ResultBean<List<OrderItem>> getDetail(int orderId) {
        System.out.println("获取订单orderId："+orderId);
        List<OrderItem> orderItems = orderService.findItems(orderId);
        return new ResultBean<>(orderItems);
    }

    /**
     * 提交订单
     *
     * @param name
     * @param phone
     * @param addr
     * @param request
     * @param response
     */
    @RequestMapping("/submit.do")
    public void submit(Integer clientId,
                       String name,
                       String phone,
                       String addr,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {

        orderService.submit(clientId,name, phone, addr, request, response);
    }

    /**
     * 支付方法
     *
     * @param orderId
     */
    @RequestMapping("pay.do")
    @ResponseBody
    public ResultBean<Boolean> pay(int orderId, HttpServletResponse response) throws IOException {
        orderService.pay(orderId);
        return new ResultBean<>(true);
    }

    /**
     * 确认收货
     *
     * @param orderId
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("receive.do")
    @ResponseBody
    public ResultBean<Boolean> receive(int orderId, HttpServletResponse response) throws IOException {
        orderService.receive(orderId);
        return new ResultBean<>(true);
    }


}
