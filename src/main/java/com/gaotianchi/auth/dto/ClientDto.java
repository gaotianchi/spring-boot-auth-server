package com.gaotianchi.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * 客户端创建 DTO
 *
 * @author gaotianchi
 * @since 2024/11/25 20:36
 **/
@Builder
@Data
public class ClientDto {

    @NotBlank(message = "客户端ID不能为空")
    @Size(max = 255, message = "客户端ID长度不能超过255")
    private String clientId;

    @Builder.Default
    private Date clientIdIssuedAt = new Date();

    @Size(max = 255, message = "客户端密钥长度不能超过255")
    private String clientSecret;

    private Date clientSecretExpiresAt;

    @NotBlank(message = "客户端名称不能为空")
    @Size(max = 255, message = "客户端名称长度不能超过255")
    private String clientName;

    @NotNull(message = "授权类型不能为空")
    private Set<@NotBlank(message = "授权类型不能为空") String> authorizationGrantTypes;

    @Size(max = 1000, message = "重定向URI列表长度不能超过1000")
    private Set<@NotBlank(message = "重定向URI不能为空") String> redirectUris;

    @Size(max = 1000, message = "注销后重定向URI列表长度不能超过1000")
    private Set<@NotBlank(message = "注销后重定向URI不能为空") String> postLogoutRedirectUris;

    @NotNull(message = "作用域不能为空")
    private Set<@NotBlank(message = "作用域不能为空") String> scopes;

    @NotNull(message = "客户端认证方式不能为空")
    private Set<@NotBlank(message = "认证方式不能为空") String> clientAuthenticationMethods;

    @Builder.Default
    private Integer requireProofKey = 1;

    @Builder.Default
    private Integer requireAuthorizationConsent = 0;

    @Builder.Default
    private Integer accessTokenTimeLive = 1800;

    @Builder.Default
    private Integer refreshTokenTimeToLive = 302400;

    @Builder.Default
    private Integer reuseRefreshTokens = 0;
}
