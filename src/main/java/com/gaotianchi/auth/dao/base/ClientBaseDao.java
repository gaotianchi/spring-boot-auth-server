package com.gaotianchi.auth.dao.base;

import com.gaotianchi.auth.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Client)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:27
 */
@Mapper
public interface ClientBaseDao {

    void insertClient(Client client);

    void insertClientsInBatches(@Param("entities") List<Client> entities);

    int deleteClientById(Integer id);

    void deleteClientsInBatchesByIds(@Param("ids") List<Integer> ids);

    void updateClientById(Client client);

    void insertOrUpdateExistingClientsInBatches(@Param("entities") List<Client> entities);

    Client selectClientById(Integer id);

    long countByClient(Client client);

    List<Client> selectClientsByPage(Client client, @Param("pageable") Pageable pageable);
}

