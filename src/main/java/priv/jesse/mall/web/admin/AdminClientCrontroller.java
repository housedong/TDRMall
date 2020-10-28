package priv.jesse.mall.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.jesse.mall.dao.ClientDao;
import priv.jesse.mall.entity.Client;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.AdminClientService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/client")
public class AdminClientCrontroller {
    @Autowired
    private ClientDao clientDao;

    /**
     * 打开客户管理首页
     * @return
     */
    @RequestMapping("/toList.html")
    public String toIndex() {
        return "admin/client/list";
    }
    /**
     * 打开编辑页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/toEdit.html")
    public String toEdit(int id, Map<String, Object> map) {
        System.out.println("客户id: " + id);
         Client client =clientDao.findOne(id);
        map.put("client", client);
        return "admin/client/edit";
    }
    /**
     * 获取所有客户列表
     *
     * @param pageindex
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<Client>> findAllUser(int pageindex,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Pageable pageable = new PageRequest(pageindex, pageSize, null);
        List<Client> clients = clientDao.findAll(pageable).getContent();
        return new ResultBean<>(clients);
    }

    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> geTotal() {
        Pageable pageable = new PageRequest(1, 15, null);
        int total = (int) clientDao.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    @ResponseBody
    @RequestMapping("/del.do")
    public ResultBean<Boolean> del(int id) {
        clientDao.delete(id);
        return new ResultBean<>(true);
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update.do")
    public ResultBean<Boolean> update( int id,String name,
                                       String phone,
                                       String email,
                                       String addr,
                                       Integer vip
                                       ) {
        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setPhone(phone);
        client.setEmail(email);
        client.setAddr(addr);
        client.setVip(vip);
        clientDao.save(client);
        return new ResultBean<>(true);
    }

}
