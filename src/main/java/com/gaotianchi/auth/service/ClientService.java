package com.gaotianchi.auth.service;

import com.gaotianchi.auth.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.List;

/**
 * (Client)表服务接口
 *
 * @author gaotianchi
 * @since 2024-11-28 20:19:44
 */
public interface ClientService extends RegisteredClientRepository {

    void addNewClient(Client client);

    void addNewClientsBatch(List<Client> clients);

    void removeClientById(Integer id);

    void removeClientsBatchByIds(List<Integer> ids);

    void updateClientDetails(Client client);

    void addNewOrUpdateClientsBatch(List<Client> clients);

    Client getClientById(Integer id);

    Page<Client> getClientsByPage(Client client, PageRequest pageRequest);
}
