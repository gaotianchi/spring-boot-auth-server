package com.gaotianchi.auth.repository.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * (Client)实体类
 *
 * @author gaotianchi
 * @since 2024-12-06 19:47:32
 */
@Data
@Builder
public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = 680659810582326373L;

    private Integer id;
    private String clientId;
    private Date clientIdIssuedAt;
    private String clientSecret;
    private Date clientSecretExpiresAt;
    private String clientName;
    private String authorizationGrantTypes;
    private String redirectUris;
    private String scopes;
    private String clientAuthenticationMethods;
    private String postLogoutRedirectUris;
    private Integer requireProofKey;
    private Integer requireAuthorizationConsent;
    private Integer accessTokenTimeLive;
    private Integer refreshTokenTimeToLive;
    private Integer reuseRefreshTokens;
}

