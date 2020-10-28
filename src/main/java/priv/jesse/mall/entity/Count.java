package priv.jesse.mall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 订单项
 */
@Entity
public class Count implements Serializable {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    /**
     * 销售员id
     */
    @Column
    private Integer user_id;
    /**
     * 销售员姓名
     */
    @Column
    private String user_name;
    /**
     * 时间
     */
    @Column
    private Date time;
    /**
     * 货物成本
     */
    @Column
    private Double cost;
    /**
     * 销售总金额
     */
    @Column
    private Double total;
    /**
     * 家电收益
     */
    @Column
    private Double earnings;

    public Count(Integer id,Integer user_id,String user_name,Date time,Double cost,Double total,Double earnings){
        this.id=id;
        this.user_id=user_id;
        this.user_name=user_name;
        this.time=time;
        this.cost=cost;
        this.total=total;
        this.earnings=earnings;

    }

    public Count() {
        super();
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count count = (Count) o;
        return Double.compare(count.cost, cost) == 0 &&
                Double.compare(count.total, total) == 0 &&
                Double.compare(count.earnings, earnings) == 0 &&
                Objects.equals(id, count.id) &&
                Objects.equals(user_id, count.user_id) &&
                Objects.equals(user_name, count.user_name) &&
                Objects.equals(time, count.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, user_name, time, cost, total, earnings);
    }


}
