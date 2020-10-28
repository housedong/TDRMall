package priv.jesse.mall.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import priv.jesse.mall.dao.ClientDao;
import priv.jesse.mall.entity.Client;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user/client")
public class ClientController {
    @Autowired
    ClientDao clientDao;

    /**
     * 添加客户信息
     */
    @RequestMapping("/register.do")
    public void register(
                         String name,
                         String phone,
                         String email,
                         String addr,
                         Integer vip,
                         HttpServletResponse response) throws IOException {

        Client client = new Client();
        client.setName(name);
        client.setPhone(phone);
        client.setEmail(email);
        client.setAddr(addr);
        client.setVip(vip);
        clientDao.save(client);

        // 注册完成后重定向到登录页面
        response.sendRedirect("/mall/index.html");
    }
}
