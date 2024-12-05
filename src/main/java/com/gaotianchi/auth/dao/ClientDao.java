package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Client)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-11-27 21:35:13
 */
@Mapper
public interface ClientDao {

    int insertClient(Client client);

    int insertClientsBatch(List<Client> clients);

    int deleteClientById(Integer id);

    int deleteClientsBatchByIds(List<Integer> clientIds);

    int updateClientById(Client client);

    int insertOrUpdateClientsBatch(List<Client> clients);

    Client selectClientById(Integer id);

    long selectClientsCount(Client client);

    List<Client> selectClientsByPage(Client client, Pageable pageable);

    Client selectClientByClientIdOrClientName(String clientIdOrClientName);
}

