package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.jesse.mall.entity.Client;

import java.util.List;

public interface ClientDao extends JpaRepository<Client,Integer> {

}
