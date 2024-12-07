package com.gaotianchi.auth.infrastructure.service;

import com.gaotianchi.auth.infrastructure.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Client)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-07 11:24:27
 */
public interface ClientBaseService {

    void addNewClient(Client client);

    void addNewClientsBatch(List<Client> clients);

    void removeClientById(Integer id);

    void removeClientsBatchByIds(List<Integer> ids);

    void updateClientDetailsById(Client client);

    void addNewOrUpdateClientsBatch(List<Client> clients);

    Client getClientById(Integer id);

    Page<Client> getClientsByPage(Client client, PageRequest pageRequest);
}
