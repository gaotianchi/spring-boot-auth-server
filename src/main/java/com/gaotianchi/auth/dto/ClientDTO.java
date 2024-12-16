package com.gaotianchi.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author gaotianchi
 * @since 2024/11/25 20:36
 **/
@Builder
@Data
public class ClientDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // ===================== Basic information ===================== //

    private Integer id;

    @NotBlank(groups = CreateClient.class, message = "Client ID cannot be empty")
    @Size(groups = CreateClient.class, max = 255, message = "Client ID length cannot exceed 255")
    private String clientId;

    @Size(groups = CreateClient.class, max = 255, message = "Client key length cannot exceed 255")
    private String clientSecret;

    @NotBlank(groups = CreateClient.class, message = "Client name cannot be empty")
    @Size(groups = {CreateClient.class, UpdateClientDetails.class}, max = 255, message = "Client name length cannot exceed 255")
    private String clientName;

    // ==================== == Authorization configuration ====================== //

    @NotNull(groups = CreateClient.class, message = "Authorization type cannot be empty")
    private Set<
            @NotBlank(groups = ClientDTO.class, message = "Authorization type cannot be empty")
                    String
            > authorizationGrantTypes;

    @Size(groups = CreateClient.class, max = 1000, message = "The length of the redirect URI list cannot exceed 1000")
    private Set<
            @NotBlank(message = "Redirect URI cannot be empty", groups = ClientDTO.class)
                    String
            > redirectUris;

    @Size(groups = CreateClient.class, max = 1000, message = "The length of the redirect URI list cannot be More than 1000")
    private Set<
            @NotBlank(message = "Redirect URI cannot be empty after logout", groups = ClientDTO.class)
                    String
            > postLogoutRedirectUris;

    @NotNull(groups = CreateClient.class, message = "Scope cannot be empty ")
    private Set<
            @NotBlank(message = "scope cannot be empty", groups = ClientDTO.class)
                    String
            > scopes;

    @NotNull(groups = CreateClient.class, message = "Client authentication method cannot be empty")
    private Set<
            @NotBlank(message = "Authentication method cannot be empty", groups = ClientDTO.class)
                    String
            > clientAuthenticationMethods;

    // ===================== Time related ===================== //

    private Date clientSecretExpiresAt;

    private Date clientIdIssuedAt;

    // ===================== Configuration fields ================ //

    private Integer requireProofKey;
    private Integer requireAuthorizationConsent;
    private Integer accessTokenTimeLive;
    private Integer refreshTokenTimeToLive;
    private Integer reuseRefreshTokens;

    // ===================== Validation group ===================== //

    public interface CreateClient extends Default {
    }

    public interface UpdateClientDetails extends Default {
    }

}