package com.gaotianchi.auth.repository.dao;

import com.gaotianchi.auth.repository.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Client)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:40
 */
@Mapper
public interface ClientRepositoryDao {

    int insertClient(Client client);

    int insertClientsBatch(@Param("entities") List<Client> entities);

    int deleteClientById(Integer id);

    int deleteClientsBatchByIds(@Param("ids") List<Integer> ids);

    int updateClientById(Client client);

    int insertOrUpdateClientsBatch(@Param("entities") List<Client> entities);

    Client selectClientById(Integer id);

    long selectClientsCount(Client client);

    List<Client> selectClientsByPage(Client client, @Param("pageable") Pageable pageable);
}

