package com.gaotianchi.auth.base.dto;

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
public class ClientDTO {

    // ===================== 基本信息 =====================

    @NotBlank(groups = CreateClient.class, message = "客户端ID不能为空")
    @Size(groups = CreateClient.class, max = 255, message = "客户端ID长度不能超过255")
    private String clientId;

    @Size(groups = CreateClient.class, max = 255, message = "客户端密钥长度不能超过255")
    private String clientSecret;

    @NotBlank(groups = CreateClient.class, message = "客户端名称不能为空")
    @Size(groups = {CreateClient.class, UpdateClientDetails.class}, max = 255, message = "客户端名称长度不能超过255")
    private String clientName;

    // ===================== 授权配置 =====================

    @NotNull(groups = CreateClient.class, message = "授权类型不能为空")
    private Set<
            @NotBlank(groups = ClientDTO.class, message = "授权类型不能为空")
                    String
            > authorizationGrantTypes;

    @Size(groups = CreateClient.class, max = 1000, message = "重定向URI列表长度不能超过1000")
    private Set<
            @NotBlank(message = "重定向URI不能为空", groups = ClientDTO.class)
                    String
            > redirectUris;

    @Size(groups = CreateClient.class, max = 1000, message = "注销后重定向URI列表长度不能超过1000")
    private Set<
            @NotBlank(message = "注销后重定向URI不能为空", groups = ClientDTO.class)
                    String
            > postLogoutRedirectUris;

    @NotNull(groups = CreateClient.class, message = "作用域不能为空")
    private Set<
            @NotBlank(message = "作用域不能为空", groups = ClientDTO.class)
                    String
            > scopes;

    @NotNull(groups = CreateClient.class, message = "客户端认证方式不能为空")
    private Set<
            @NotBlank(message = "认证方式不能为空", groups = ClientDTO.class)
                    String
            > clientAuthenticationMethods;

    // ===================== 时间相关 =====================

    private Date clientSecretExpiresAt;
    private Date clientIdIssuedAt;

    // ===================== 配置字段 =====================

    private Integer requireProofKey;
    private Integer requireAuthorizationConsent;
    private Integer accessTokenTimeLive;
    private Integer refreshTokenTimeToLive;
    private Integer reuseRefreshTokens;

    // ===================== 验证组 =====================

    public interface CreateClient extends Default {
    }

    public interface UpdateClientDetails extends Default {
    }
}
