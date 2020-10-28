package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import priv.jesse.mall.entity.Count;
import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.OrderItem;

import java.util.List;

public interface CountDao extends JpaRepository<Count, Integer> {


    @Query(value = "select * FROM `count` c where DATEDIFF(c.time,NOW())='-1'",nativeQuery = true)
    List<Count> getYesterdayCount();

    @Query(value = "select * FROM `count` c where YEARWEEK( date_format(c.time,'%Y-%m-%d' )) = YEARWEEK( now()) ",nativeQuery = true)
    List<Count> getWeekCount();

    @Query(value = "select * FROM `count` c where DATE_FORMAT(c.time, '%Y%m') = DATE_FORMAT( CURDATE(),'%Y%m')",nativeQuery = true)
    List<Count> getMonthCount();

    @Query(value = "select * FROM `count` c where QUARTER(c.time)=QUARTER(now()) ",nativeQuery = true)
    List<Count> getQuarterCount();



}
