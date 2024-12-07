package com.gaotianchi.auth.dao;

import com.gaotianchi.auth.infrastructure.dao.ClientBaseDao;
import com.gaotianchi.auth.infrastructure.entity.Client;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Client)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-11-27 21:35:13
 */
@Mapper
public interface ClientDao extends ClientBaseDao {

    Client selectClientByClientIdOrClientName(String clientIdOrClientName);
}

