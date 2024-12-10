package com.gaotianchi.auth.infrastructure.dao;

import com.gaotianchi.auth.infrastructure.entity.Client;
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

    int insertClient(Client client);

    int insertClientsInBatches(@Param("entities") List<Client> entities);

    int deleteClientById(Integer id);

    int deleteClientsInBatchesByIds(@Param("ids") List<Integer> ids);

    int updateClientById(Client client);

    int insertOrUpdateExistingClientsInBatches(@Param("entities") List<Client> entities);

    Client selectClientById(Integer id);

    long countByClient(Client client);

    List<Client> selectClientsByPage(Client client, @Param("pageable") Pageable pageable);
}

