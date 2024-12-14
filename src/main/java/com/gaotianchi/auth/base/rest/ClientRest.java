package com.gaotianchi.auth.base.rest;


import com.gaotianchi.auth.base.converter.ClientConverter;
import com.gaotianchi.auth.base.dto.ClientDTO;
import com.gaotianchi.auth.base.entity.Client;
import com.gaotianchi.auth.base.service.ClientBaseService;
import com.gaotianchi.auth.base.vo.ClientVO;
import com.gaotianchi.auth.base.vo.VO;
import com.gaotianchi.auth.enums.Code;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.gaotianchi.auth.utils.RestTool.getPageRequest;

/**
 * @author gaotianchi
 * @since 2024/11/26 12:51
 **/
@RestController
@RequestMapping("client")
public class ClientRest {

    private final ClientBaseService clientBaseService;

    private final ClientConverter clientConverter;

    public ClientRest(ClientBaseService clientBaseService, ClientConverter clientConverter) {
        this.clientBaseService = clientBaseService;
        this.clientConverter = clientConverter;
    }

    @PostMapping("")
    public VO<String> handleAddNewClientRequest(
            @RequestBody
            @Validated(ClientDTO.CreateClient.class)
            ClientDTO clientDto
    ) {
        Client client = clientConverter.toEntity(clientDto);
        clientBaseService.addNewClient(client);
        return VO.response(Code.SUCCESS, "/client/info/" + client.getId());
    }

    @PostMapping("batch")
    public VO<String> handleAddNewClientInBatchesRequest(
            @RequestBody
            @Validated(ClientDTO.CreateClient.class)
            List<ClientDTO> clientDTOList
    ) {
        List<Client> clientList = clientDTOList
                .stream()
                .map(clientConverter::toEntity)
                .toList();
        clientBaseService.addNewClientsInBatches(clientList);
        List<String> uris = new ArrayList<>();
        clientList.forEach(client -> uris.add("/client/info/" + client.getId()));
        return VO.response(Code.SUCCESS, uris.toString());
    }

    @DeleteMapping("")
    public VO<Void> handleRemoveClientByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be empty")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id
    ) {
        clientBaseService.removeClientById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @DeleteMapping("batch")
    public VO<Void> handleRemoveClientInBatchesByIdsRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be empty")
            @Size(min = 1, message = "At least one id is required")
            List<Integer> ids
    ) {
        clientBaseService.removeClientsInBatchesByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<String> handleUpdateClientByIdRequest(
            @RequestBody
            @Validated(ClientDTO.UpdateClientDetails.class)
            ClientDTO clientDto
    ) {
        Client client = clientConverter.toEntity(clientDto);
        clientBaseService.updateClientById(client);
        return VO.response(Code.SUCCESS, "/client/info/" + client.getId());
    }

    @PutMapping("batch")
    public VO<String> handleAddNewClientOrUpdateExistingClientInBatchesRequest(
            @RequestBody
            @Validated(ClientDTO.UpdateClientDetails.class)
            List<ClientDTO> clientDTOList
    ) {
        List<Client> clientList = clientDTOList
                .stream()
                .map(clientConverter::toEntity)
                .toList();
        clientBaseService.addNewOrUpdateExistingClientsInBatches(clientList);
        List<String> uris = new ArrayList<>();
        clientList.forEach(client -> uris.add("/client/info/" + client.getId()));
        return VO.response(Code.SUCCESS, uris.toString());
    }

    @GetMapping("info")
    public VO<ClientVO> handleGetClientByIdRequest(
            @RequestParam
            @NotNull(message = "id can not be null")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id
    ) {
        Client client = clientBaseService.getClientById(id);
        ClientVO clientVO = clientConverter.toVO(client);
        return VO.response(Code.SUCCESS, clientVO);
    }

    @GetMapping("info-list")
    public VO<Page<ClientVO>> handleGetClientByIdRequest(
            @ModelAttribute ClientDTO clientDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id:asc") List<String> sorts
    ) {
        Client client = clientConverter.toEntity(clientDto);
        PageRequest pageRequest = getPageRequest(page, size, sorts);
        Page<Client> clientPage = clientBaseService.getClientsByPage(client, pageRequest);
        Page<ClientVO> clientVOPage = clientPage.map(clientConverter::toVO);
        return VO.response(Code.SUCCESS, clientVOPage);
    }
}
