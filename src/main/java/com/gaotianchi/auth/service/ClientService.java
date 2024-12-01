package com.gaotianchi.auth.service;

import com.gaotianchi.auth.entity.Client;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * (Client)表服务接口
 *
 * @author gaotianchi
 * @since 2024-11-28 20:19:44
 */
public interface ClientService extends RegisteredClientRepository {

    void insert(Client client);

    void deleteById(Integer id);

    void update(Client client);

    Client findById(Integer id);
}
