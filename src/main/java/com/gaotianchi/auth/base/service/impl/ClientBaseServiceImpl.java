package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.ClientBaseDao;
import com.gaotianchi.auth.base.entity.Client;
import com.gaotianchi.auth.base.service.ClientBaseService;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
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
        if (clientBaseDao.insertClient(client) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewClientsInBatches(List<Client> clients) {
        if (clientBaseDao.insertClientsInBatches(clients) != clients.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeClientById(Integer id) {
        if (clientBaseDao.deleteClientById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeClientsInBatchesByIds(List<Integer> ids) {
        if (clientBaseDao.deleteClientsInBatchesByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateClientById(Client client) {
        if (clientBaseDao.updateClientById(client) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateExistingClientsInBatches(List<Client> clients) {
        if (clientBaseDao.insertOrUpdateExistingClientsInBatches(clients) != clients.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
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
