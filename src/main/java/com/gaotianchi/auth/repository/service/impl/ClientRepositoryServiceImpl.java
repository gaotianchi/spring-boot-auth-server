package com.gaotianchi.auth.repository.service.impl;

import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.repository.dao.ClientRepositoryDao;
import com.gaotianchi.auth.repository.entity.Client;
import com.gaotianchi.auth.repository.service.ClientRepositoryService;
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
 * @since 2024-12-06 19:30:49
 */
@Service("clientService")
public class ClientRepositoryServiceImpl implements ClientRepositoryService {

    private final ClientRepositoryDao clientRepositoryDao;

    public ClientRepositoryServiceImpl(ClientRepositoryDao clientRepositoryDao) {
        Assert.notNull(clientRepositoryDao, "ClientRepository cannot be null");
        this.clientRepositoryDao = clientRepositoryDao;
    }

    @Override
    public void addNewClient(Client client) {
        if (clientRepositoryDao.insertClient(client) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewClientsBatch(List<Client> clients) {
        if (clientRepositoryDao.insertClientsBatch(clients) != clients.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeClientById(Integer id) {
        if (clientRepositoryDao.deleteClientById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeClientsBatchByIds(List<Integer> ids) {
        if (clientRepositoryDao.deleteClientsBatchByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateClientDetailsById(Client client) {
        if (clientRepositoryDao.updateClientById(client) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateClientsBatch(List<Client> clients) {
        if (clientRepositoryDao.insertOrUpdateClientsBatch(clients) != clients.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public Client getClientById(Integer id) {
        return clientRepositoryDao.selectClientById(id);
    }

    @Override
    public Page<Client> getClientsByPage(Client client, PageRequest pageRequest) {
        long total = clientRepositoryDao.selectClientsCount(client);
        return new PageImpl<>(clientRepositoryDao.selectClientsByPage(client, pageRequest), pageRequest, total);
    }


}
