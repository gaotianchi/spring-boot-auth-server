package com.gaotianchi.auth.service;

import com.gaotianchi.auth.repository.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * (Client)表服务接口
 *
 * @author gaotianchi
 * @since 2024-11-28 20:19:44
 */
public interface ClientService extends RegisteredClientRepository {

    Client getClientById(Integer id);

    Page<Client> getClientsByPage(Client client, PageRequest pageRequest);
}
