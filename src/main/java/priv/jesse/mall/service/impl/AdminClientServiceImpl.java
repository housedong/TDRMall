package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.ClientDao;
import priv.jesse.mall.service.AdminClientService;
import priv.jesse.mall.entity.Client;
import java.util.List;

@Service
public class AdminClientServiceImpl implements AdminClientService {
    @Autowired
    private ClientDao clientDao;


//    @Override
//    public Client findById(int id) {
//        return clientDao.getOne(id);
//    }
//
////    @Override
////    public Page<Client> findAll(Pageable pageable) {
////        return adminUserDao.findAll(pageable);
////    }
//
////    @Override
////    public List<Client> findAllExample(Example<Client> example) {
////        return adminUserDao.findAll(example);
////    }
//
//    @Override
//    public void update(Client client) {
//        clientDao.save(client);
//    }

    @Override
    public void update(Client client) {
        clientDao.save(client);
    }

    @Override
    public void delById(int id) {
        clientDao.delete(id);
    }

    @Override
    public int isVip(int id) {
      Client client = clientDao.findOne(id);
        System.out.println("判断clientDao存在"+client);
      if(client==null){
          return 0;
      }else{
          return client.getVip();
      }
    }

}
