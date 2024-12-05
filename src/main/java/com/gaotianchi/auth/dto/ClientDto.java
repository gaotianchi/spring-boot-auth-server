package com.gaotianchi.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
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

    @NotBlank(message = "客户端ID不能为空", groups = CreateClient.class)
    @Size(max = 255, message = "客户端ID长度不能超过255", groups = CreateClient.class)
    private String clientId;

    @Size(max = 255, message = "客户端密钥长度不能超过255", groups = CreateClient.class)
    private String clientSecret;

    @NotBlank(message = "客户端名称不能为空", groups = CreateClient.class)
    @Size(max = 255, message = "客户端名称长度不能超过255", groups = {CreateClient.class, UpdateClientDetails.class})
    private String clientName;

    @Builder.Default
    private Date clientIdIssuedAt = new Date();
    @NotNull(message = "授权类型不能为空", groups = CreateClient.class)
    private Set<@NotBlank(message = "授权类型不能为空", groups = ClientDto.class) String> authorizationGrantTypes;

    private Date clientSecretExpiresAt;
    @Size(max = 1000, message = "重定向URI列表长度不能超过1000", groups = CreateClient.class)
    private Set<@NotBlank(message = "重定向URI不能为空", groups = ClientDto.class) String> redirectUris;
    @Size(max = 1000, message = "注销后重定向URI列表长度不能超过1000", groups = CreateClient.class)
    private Set<@NotBlank(message = "注销后重定向URI不能为空", groups = ClientDto.class) String> postLogoutRedirectUris;
    @NotNull(message = "作用域不能为空", groups = CreateClient.class)
    private Set<@NotBlank(message = "作用域不能为空", groups = ClientDto.class) String> scopes;
    @NotNull(message = "客户端认证方式不能为空", groups = CreateClient.class)
    private Set<@NotBlank(message = "认证方式不能为空", groups = ClientDto.class) String> clientAuthenticationMethods;

    public interface CreateClient extends Default {
    }

    public interface UpdateClientDetails extends Default {
    }

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
