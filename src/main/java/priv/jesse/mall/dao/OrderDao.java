package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import priv.jesse.mall.entity.AdminUser;
import priv.jesse.mall.entity.Order;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {

    /**
     * 更改订单状态
     * @param state
     * @param id
     */
    @Modifying
    @Transactional
    @Query(value = "update `order` o set o.state=?1 where o.id=?2",nativeQuery = true)
    void updateState(int state,int id);

    //o.user_id=?1
    @Query(value = "select * FROM `order` o where DATEDIFF(o.order_time,NOW())='-1' and o.user_id=?1",nativeQuery = true)
    List<Order> getYesterDaycount(int userId);

    //o.user_id=?1
    @Query(value = "select * FROM `order` o where to_days(o.order_time) = to_days(now()) and o.user_id=?1",nativeQuery = true)
    List<Order> getDaycount(int userId);

   @Query(value = "select * FROM `order` o where YEARWEEK( date_format(o.order_time,'%Y-%m-%d' )) = YEARWEEK( now()) and o.user_id=?1",nativeQuery = true)
   List<Order> getWeekcount(int userId);

    @Query(value = "select * FROM `order` o where DATE_FORMAT(o.order_time, '%Y%m') = DATE_FORMAT( CURDATE(),'%Y%m') and o.user_id=?1",nativeQuery = true)
    List<Order> getMonthcount(int userId);

    @Query(value = "select * FROM `order` o where QUARTER(o.order_time)=QUARTER(now()) and o.user_id=?1",nativeQuery = true)
    List<Order> getQuartercount(int userId);

//    @Query(value = "select a.click_date as order_time,ifnull(b.count,0) as total\n" +
//            "    from (\n" +
//            "            SELECT curdate() as click_date\n" +
//            "    union all\n" +
//            "    SELECT date_sub(curdate(), interval 1 day) as click_date\n" +
//            "    union all\n" +
//            "    SELECT date_sub(curdate(), interval 2 day) as click_date\n" +
//            "    union all\n" +
//            "    SELECT date_sub(curdate(), interval 3 day) as click_date\n" +
//            "    union all\n" +
//            "    SELECT date_sub(curdate(), interval 4 day) as click_date\n" +
//            "    union all\n" +
//            "    SELECT date_sub(curdate(), interval 5 day) as click_date\n" +
//            "    union all\n" +
//            "    SELECT date_sub(curdate(), interval 6 day) as click_date\n" +
//            ") a left join (\n" +
//            "            select date(order_time) as datetime, sum(total) as count\n" +
//            "    from `order`\n" +
//            "    WHERE user_id=?1\n" +
//            "    group by date(order_time)\n" +
//            ") b on a.click_date = b.datetime",nativeQuery = true)
//    List getWeekcount(int userId);


    /**
     * 查找用户的订单
     * @param userId
     * @return
     */
    List<Order> findByUserId(int userId);


}
