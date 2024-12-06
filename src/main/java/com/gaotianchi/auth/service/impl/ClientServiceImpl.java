package com.gaotianchi.auth.service.impl;

import com.gaotianchi.auth.dao.ClientDao;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import com.gaotianchi.auth.repository.entity.Client;
import com.gaotianchi.auth.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.List;
import java.util.Set;


/**
 * 客户端表(Client)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-11-24 20:35:24
 */
@Service("clientService")
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        Assert.notNull(clientDao, "clientRepository cannot be null");
        this.clientDao = clientDao;
    }

    private static AuthorizationGrantType resolveAuthorizationGrantType(String authorizationGrantType) {
        if (AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.AUTHORIZATION_CODE;
        } else if (AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.CLIENT_CREDENTIALS;
        } else if (AuthorizationGrantType.REFRESH_TOKEN.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.REFRESH_TOKEN;
        }
        return new AuthorizationGrantType(authorizationGrantType);              // Custom authorization grant type
    }

    private static ClientAuthenticationMethod resolveClientAuthenticationMethod(String clientAuthenticationMethod) {
        if (ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue().equals(clientAuthenticationMethod)) {
            return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
        } else if (ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue().equals(clientAuthenticationMethod)) {
            return ClientAuthenticationMethod.CLIENT_SECRET_POST;
        } else if (ClientAuthenticationMethod.NONE.getValue().equals(clientAuthenticationMethod)) {
            return ClientAuthenticationMethod.NONE;
        }
        return new ClientAuthenticationMethod(clientAuthenticationMethod);      // Custom client authentication method
    }

    public static RegisteredClient toRegisteredClient(Client client) {

        Set<String> clientAuthenticationMethods = StringUtils.commaDelimitedListToSet(
                client.getClientAuthenticationMethods());
        Set<String> authorizationGrantTypes = StringUtils.commaDelimitedListToSet(
                client.getAuthorizationGrantTypes());
        Set<String> redirectUris = StringUtils.commaDelimitedListToSet(
                client.getRedirectUris());
        Set<String> clientScopes = StringUtils.commaDelimitedListToSet(
                client.getScopes());
        Set<String> postLogoutRedirectUris = StringUtils.commaDelimitedListToSet(
                client.getPostLogoutRedirectUris());

        return RegisteredClient.withId(client.getClientId())
                .id(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientName(client.getClientName())
                .clientIdIssuedAt(client.getClientIdIssuedAt().toInstant())
                .clientAuthenticationMethods(authenticationMethods ->
                        clientAuthenticationMethods.forEach(method ->
                                authenticationMethods.add(resolveClientAuthenticationMethod(method))))
                .authorizationGrantTypes(grantTypes ->
                        authorizationGrantTypes.forEach(type ->
                                grantTypes.add(resolveAuthorizationGrantType(type))))
                .redirectUris(uris -> uris.addAll(redirectUris))
                .scopes(scopes -> scopes.addAll(clientScopes))
                .postLogoutRedirectUris(uris -> uris.addAll(postLogoutRedirectUris))
                .clientSettings(ClientSettings.builder()
                        .requireProofKey(client.getRequireProofKey() == 1)
                        .requireAuthorizationConsent(client.getRequireAuthorizationConsent() == 1)
                        .build())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofSeconds(client.getAccessTokenTimeLive()))
                        .refreshTokenTimeToLive(Duration.ofSeconds(client.getRefreshTokenTimeToLive()))
                        .reuseRefreshTokens(client.getReuseRefreshTokens() == 1)
                        .build())
                .build();
    }

    @Override
    public void addNewClient(Client client) {
        if (clientDao.insertClient(client) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewClientsBatch(List<Client> clients) {
        if (clientDao.insertClientsBatch(clients) != clients.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeClientById(Integer id) {
        if (clientDao.deleteClientById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeClientsBatchByIds(List<Integer> ids) {
        if (clientDao.deleteClientsBatchByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateClientDetails(Client client) {
        if (clientDao.updateClientById(client) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateClientsBatch(List<Client> clients) {
        if (clientDao.insertOrUpdateClientsBatch(clients) != clients.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public Client getClientById(Integer id) {
        return clientDao.selectClientById(id);
    }

    @Override
    public Page<Client> getClientsByPage(Client client, PageRequest pageRequest) {
        long total = clientDao.selectClientsCount(client);
        return new PageImpl<>(clientDao.selectClientsByPage(client, pageRequest), pageRequest, total);
    }

    @Override
    public void save(RegisteredClient registeredClient) {
    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = getClientById(Integer.valueOf(id));
        return toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientDao.selectClientByClientIdOrClientName(clientId);
        return toRegisteredClient(client);
    }
}
