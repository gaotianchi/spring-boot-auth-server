package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.ClientBaseDao;
import com.gaotianchi.auth.base.entity.Client;
import com.gaotianchi.auth.base.service.ClientBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * (Client)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-12-07 11:29:50
 */
@Service("clientBaseService")
public class ClientBaseServiceImpl implements ClientBaseService {

    private final ClientBaseDao clientBaseDao;

    public ClientBaseServiceImpl(ClientBaseDao clientBaseDao) {
        Assert.notNull(clientBaseDao, "ClientRepository cannot be null");
        this.clientBaseDao = clientBaseDao;
    }

    @Override
    public void addNewClient(Client client) {
        clientBaseDao.insertClient(client);
    }

    @Override
    public void addNewClientsInBatches(List<Client> clients) {
        clientBaseDao.insertClientsInBatches(clients);
    }

    @Override
    public void removeClientById(Integer id) {
        clientBaseDao.deleteClientById(id);
    }

    @Override
    public void removeClientsInBatchesByIds(List<Integer> ids) {
        clientBaseDao.deleteClientsInBatchesByIds(ids);
    }

    @Override
    public void updateClientById(Client client) {
        clientBaseDao.updateClientById(client);
    }

    @Override
    public void addNewOrUpdateExistingClientsInBatches(List<Client> clients) {
        clientBaseDao.insertOrUpdateExistingClientsInBatches(clients);
    }

    @Override
    public Client getClientById(Integer id) {
        return clientBaseDao.selectClientById(id);
    }

    @Override
    public Page<Client> getClientsByPage(Client client, PageRequest pageRequest) {
        long total = clientBaseDao.countByClient(client);
        return new PageImpl<>(clientBaseDao.selectClientsByPage(client, pageRequest), pageRequest, total);
    }


}
