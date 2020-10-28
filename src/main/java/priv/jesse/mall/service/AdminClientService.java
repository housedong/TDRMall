package priv.jesse.mall.service;

import priv.jesse.mall.entity.Client;

public interface AdminClientService {

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    void delById(int id);

    void update(Client client);

    int isVip(int id);

//    public Client findById(int id);
}
