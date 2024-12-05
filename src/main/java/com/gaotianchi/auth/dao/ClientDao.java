package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.entity.Client;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Client)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-11-27 21:35:13
 */
@Mapper
public interface ClientDao {

    int insertClient(Client client);

    int deleteClientById(Integer id);

    int updateClientById(Client client);

    Client selectClientById(Integer id);

    Client selectClientByClientIdOrClientName(String clientIdOrClientName);
}

