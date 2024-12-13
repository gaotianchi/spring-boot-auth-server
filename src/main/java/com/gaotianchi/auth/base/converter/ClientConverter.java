package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.ClientDTO;
import com.gaotianchi.auth.base.entity.Client;
import com.gaotianchi.auth.base.vo.ClientVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @author gaotianchi
 * @since 2024/11/30 19:50
 **/
@Mapper(componentModel = "spring")
public interface ClientConverter {

    @Mapping(target = "clientAuthenticationMethods", expression = "java(setToString(clientDto.getClientAuthenticationMethods()))")
    @Mapping(target = "authorizationGrantTypes", expression = "java(setToString(clientDto.getAuthorizationGrantTypes()))")
    @Mapping(target = "scopes", expression = "java(setToString(clientDto.getScopes()))")
    @Mapping(target = "redirectUris", expression = "java(setToString(clientDto.getRedirectUris()))")
    @Mapping(target = "postLogoutRedirectUris", expression = "java(setToString(clientDto.getPostLogoutRedirectUris()))")
    Client toEntity(ClientDTO clientDto);


    @Mapping(target = "clientAuthenticationMethods", expression = "java(stringToSet(client.getClientAuthenticationMethods()))")
    @Mapping(target = "authorizationGrantTypes", expression = "java(stringToSet(client.getAuthorizationGrantTypes()))")
    @Mapping(target = "scopes", expression = "java(stringToSet(client.getScopes()))")
    @Mapping(target = "redirectUris", expression = "java(stringToSet(client.getRedirectUris()))")
    @Mapping(target = "postLogoutRedirectUris", expression = "java(stringToSet(client.getPostLogoutRedirectUris()))")
    ClientVO toVO(Client client);


    default String setToString(Set<String> setData) {
        if (ObjectUtils.isEmpty(setData)) {
            return null;
        }
        return StringUtils.collectionToCommaDelimitedString(setData);
    }

    default Set<String> stringToSet(String stringData) {
        if (stringData == null) {
            return null;
        }
        return StringUtils.commaDelimitedListToSet(stringData);
    }
}