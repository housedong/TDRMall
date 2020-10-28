package priv.jesse.mall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String name;
    /**
     * 邮件
     */
    @Column
    private String email;
    /**
     * 电话
     */
    @Column
    private String phone;
    /**
     * 地址
     */
    @Column
    private String addr;

    /**
     * vip
     */
    @Column
    private Integer vip;

    public Client(Integer id, String name, String email, String phone, String addr,  Integer vip) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.addr = addr;
        this.vip=vip;
    }

    public Client() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(email, client.email) &&
                Objects.equals(phone, client.phone) &&
                Objects.equals(addr, client.addr) &&
                Objects.equals(vip, client.vip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, addr, vip);
    }
}
