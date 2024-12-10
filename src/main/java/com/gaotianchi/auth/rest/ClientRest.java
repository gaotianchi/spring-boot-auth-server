package com.gaotianchi.auth.rest;


import com.gaotianchi.auth.converter.ClientConverter;
import com.gaotianchi.auth.dto.ClientDto;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.infrastructure.entity.Client;
import com.gaotianchi.auth.infrastructure.service.ClientBaseService;
import com.gaotianchi.auth.vo.ClientVO;
import com.gaotianchi.auth.vo.VO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public VO<String> handleCreateClientRequest(
            @RequestBody
            @Validated(ClientDto.CreateClient.class)
            ClientDto clientDto
    ) {
        Client client = clientConverter.toEntity(clientDto);
        clientBaseService.addNewClient(client);
        return VO.response(Code.SUCCESS, "/client/info/" + client.getId());
    }

    @PostMapping("batch")
    public VO<String> handleCreateClientsBatchRequest(
            @RequestBody
            @Validated(ClientDto.CreateClient.class)
            List<ClientDto> clientDtoList
    ) {
        List<Client> clientList = clientDtoList
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
            @NotNull(message = "id 不能为空")
            @Min(value = 1, message = "id 必须大于等于 1")
            Integer id
    ) {
        clientBaseService.removeClientById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @DeleteMapping("batch")
    public VO<Void> handleRemoveClientBatchByIdsRequest(
            @RequestParam("ids")
            @NotNull(message = "ids 不能为空")
            @Size(min = 1, message = "至少需要提供一个 id")
            List<Integer> ids
    ) {
        clientBaseService.removeClientsInBatchesByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<String> handleUpdateClientDetailsRequest(
            @RequestBody
            @Validated(ClientDto.UpdateClientDetails.class)
            ClientDto clientDto
    ) {
        Client client = clientConverter.toEntity(clientDto);
        clientBaseService.updateClientById(client);
        return VO.response(Code.SUCCESS, "/client/info/" + client.getId());
    }

    @PutMapping("batch")
    public VO<String> handleUpdateClientsBatchRequest(
            @RequestBody
            @Validated(ClientDto.UpdateClientDetails.class)
            List<ClientDto> clientDtoList
    ) {
        List<Client> clientList = clientDtoList
                .stream()
                .map(clientConverter::toEntity)
                .toList();
        clientBaseService.addNewOrUpdateExistingClientsInBatches(clientList);
        List<String> uris = new ArrayList<>();
        clientList.forEach(client -> uris.add("/client/info/" + client.getId()));
        return VO.response(Code.SUCCESS, uris.toString());
    }

    @GetMapping("info/{id}")
    public VO<ClientVO> handleGetClientByIdRequest(
            @PathVariable
            @NotNull(message = "id 不能为空")
            @Min(value = 1, message = "id 必须大于等于 1")
            Integer id
    ) {
        Client client = clientBaseService.getClientById(id);
        ClientVO clientVO = clientConverter.toVO(client);
        return VO.response(Code.SUCCESS, clientVO);
    }

    @GetMapping("info-list")
    public VO<Page<ClientVO>> handleGetClientListRequest(
            @ModelAttribute ClientDto clientDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Client client = clientConverter.toEntity(clientDto);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Client> clientPage = clientBaseService.getClientsByPage(client, pageRequest);
        Page<ClientVO> clientVOPage = clientPage.map(clientConverter::toVO);
        return VO.response(Code.SUCCESS, clientVOPage);
    }
}
