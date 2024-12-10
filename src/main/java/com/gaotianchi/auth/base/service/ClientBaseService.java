package com.gaotianchi.auth.base.service;

import com.gaotianchi.auth.base.entity.Client;
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

    void addNewClientsInBatches(List<Client> clients);

    void removeClientById(Integer id);

    void removeClientsInBatchesByIds(List<Integer> ids);

    void updateClientById(Client client);

    void addNewOrUpdateExistingClientsInBatches(List<Client> clients);

    Client getClientById(Integer id);

    Page<Client> getClientsByPage(Client client, PageRequest pageRequest);
}
