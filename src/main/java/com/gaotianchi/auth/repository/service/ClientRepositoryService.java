package com.gaotianchi.auth.repository.service;

import com.gaotianchi.auth.repository.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Client)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-06 19:28:40
 */
public interface ClientRepositoryService {

    void addNewClient(Client client);

    void addNewClientsBatch(List<Client> clients);

    void removeClientById(Integer id);

    void removeClientsBatchByIds(List<Integer> ids);

    void updateClientDetailsById(Client client);

    void addNewOrUpdateClientsBatch(List<Client> clients);

    Client getClientById(Integer id);

    Page<Client> getClientsByPage(Client client, PageRequest pageRequest);
}
