package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.jesse.mall.dao.ClientDao;
import priv.jesse.mall.dao.OrderDao;
import priv.jesse.mall.dao.OrderItemDao;
import priv.jesse.mall.dao.ProductDao;
import priv.jesse.mall.entity.*;
import priv.jesse.mall.service.AdminClientService;
import priv.jesse.mall.service.OrderService;
import priv.jesse.mall.service.ProductService;
import priv.jesse.mall.service.ShopCartService;
import priv.jesse.mall.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AdminClientService adminClientService;


    @Override
    public Order findById(int id) {
        return orderDao.getOne(id);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderDao.findAll(pageable);
    }

    @Override
    public List<Order> findAllExample(Example<Order> example) {
        return orderDao.findAll(example);
    }

    @Override
    public void update(Order order) {
        orderDao.save(order);
    }

    @Override
    public int create(Order order) {
        Order order1 = orderDao.save(order);
        return order1.getId();
    }

    @Override
    public void delById(int id) {
        orderDao.delete(id);
    }

    /**
     * 查询订单项详情
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> findItems(int orderId) {
        List<OrderItem> list = orderItemDao.findByOrderId(orderId);
        for (OrderItem orderItem : list) {
            Product product = productDao.findOne(orderItem.getProductId());
            System.out.println("获取img："+product);
            orderItem.setProduct(product);
        }
        return list;
    }

    /**
     * 更改订单状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(int id, int status) {
        Order order = orderDao.findOne(id);
        order.setState(status);
        orderDao.save(order);
    }

    /**
     * 查找用户的订单列表
     *
     * @param request
     * @return
     */
    @Override
    public List<Order> findUserOrder(HttpServletRequest request) {
        //从session中获取登录用户的id，查找他的订单
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("请登录！");
        User loginUser = (User) user;
        List<Order> orders = orderDao.findByUserId(loginUser.getId());
        return orders;
    }

    /**
     * 查找日销售业绩列表
     *
     * @param request
     * @return
     */
    @Override
    public List<Order> getOneDayCount(HttpServletRequest request){
        //从session中获取登录用户的id，查找他的订单
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("请登录！");
        User loginUser = (User) user;
        List<Order> orders = orderDao.getDaycount(loginUser.getId());
        return orders;
    }
    /**
     * 查找周销售业绩列表
     *
     * @param request
     * @return
     */
    @Override
    public List<Order> getOneWeekCount(HttpServletRequest request){
        //从session中获取登录用户的id，查找他的订单
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("请登录！");
        User loginUser = (User) user;
        List<Order> orders = orderDao.getWeekcount(loginUser.getId());
        return orders;
    }
    /**
     * 查找月销售业绩列表
     *
     * @param request
     * @return
     */
    @Override
    public List<Order> getOneMonthCount(HttpServletRequest request){
        //从session中获取登录用户的id，查找他的订单
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("请登录！");
        User loginUser = (User) user;
        List<Order> orders = orderDao.getMonthcount(loginUser.getId());
        return orders;
    }
    /**
     * 查找季度销售业绩列表
     *
     * @param request
     * @return
     */
    @Override
    public List<Order> getOneQuarterCount(HttpServletRequest request){
        //从session中获取登录用户的id，查找他的订单
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("请登录！");
        User loginUser = (User) user;
        List<Order> orders = orderDao.getQuartercount(loginUser.getId());
        return orders;
    }

   /**
     * 支付
     *
     * @param orderId
     */
    @Override
    public void pay(int orderId) {
        //具体逻辑就不实现了，直接更改状态为 待发货
        Order order = orderDao.findOne(orderId);
        if (order == null)
            throw new RuntimeException("订单不存在");
        orderDao.updateState(STATE_WAITE_SEND,order.getId());
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
    @Override
    @Transactional
    public void submit(Integer clientId,String name, String phone, String addr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object user = request.getSession().getAttribute("user");
        System.out.println("上传的clientId"+clientId);
        int vip =0;
        if (user == null)
            throw new LoginException("请登录！");
        User loginUser = (User) user;
        Order order = new Order();

        order.setName(xssEncode(name));
        order.setPhone(phone);
        order.setAddr(addr);
        order.setOrderTime(new Date());
        order.setUserId(loginUser.getId());
        order.setState(STATE_NO_PAY);
        List<OrderItem> orderItems = shopCartService.listCart(request);
        Double total = 0.0;
        Double cost = 0.0;
        order.setTotal(total);
        order.setCost(cost);
        order = orderDao.save(order);

        if (clientId == null){
            vip=0;
        }else{
           vip = adminClientService.isVip(clientId);
            System.out.println("判断adminClientService.isVip(clientId)"+vip);
        }

        for (OrderItem orderItem : orderItems) {
            System.out.println("查看orderItem数据"+orderItem);

            Integer  productId = orderItem.getProductId();
            Integer count = orderItem.getCount();
            Product product =productService.findById(productId);
            Integer stock =product.getStock()-count;
            product.setStock(stock);

            orderItem.setOrderId(order.getId());
            total += orderItem.getSubTotal();
            cost += orderItem.getSubCost();
            orderItemDao.save(orderItem);
        }
        if (vip==0){
            order.setTotal(total);
        }else {
            total=total*0.88;
            order.setTotal(total);
        }
        order.setCost(cost);
        orderDao.save(order);
        //重定向到订单列表页面
        response.sendRedirect("/mall/order/toList.html");
    }
    private static String xssEncode(String s) {//XSS静态过滤方法
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append('＞');// 全角大于号
                    break;
                case '<':
                    sb.append('＜');// 全角小于号
                    break;
                case '\'':
                    sb.append('‘');// 全角单引号
                    break;
                case '\"':
                    sb.append('“');// 全角双引号
                    break;
                case '&':
                    sb.append('＆');// 全角
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                case '#':
                    sb.append('＃');// 全角井号
                    break;
                case '(':
                    sb.append('（');//
                    break;
                case ')':
                    sb.append('）');//
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 确认收货
     *
     * @param orderId
     */
    @Override
    public void receive(int orderId) {
        Order order = orderDao.findOne(orderId);
        if (order == null)
            throw new RuntimeException("订单不存在");
        orderDao.updateState(STATE_COMPLETE,order.getId());
    }
}
