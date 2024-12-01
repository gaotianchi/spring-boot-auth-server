package com.gaotianchi.auth.rest;


import com.gaotianchi.auth.converter.ClientConverter;
import com.gaotianchi.auth.dto.ClientDto;
import com.gaotianchi.auth.entity.Client;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.service.ClientService;
import com.gaotianchi.auth.vo.ClientVO;
import com.gaotianchi.auth.vo.VO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

/**
 * @author gaotianchi
 * @since 2024/11/26 12:51
 **/
@RestController
@RequestMapping("client")
public class ClientRest {

    private final ClientService clientService;

    private final ClientConverter clientConverter;

    public ClientRest(ClientService clientService, ClientConverter clientConverter) {
        this.clientService = clientService;
        this.clientConverter = clientConverter;
    }

    @PostMapping("")
    public VO<String> insert(@Valid @RequestBody ClientDto clientDto) {
        Client client = clientConverter.toEntity(clientDto);
        clientService.insert(client);
        return VO.response(Code.SUCCESS, "/client/info/" + client.getId());
    }

    @DeleteMapping("{id}")
    public VO<Void> deleteById(@PathVariable @NotNull(message = "id 不能为空") @Min(value = 1, message = "id 必须大于等于 1") Integer id) {
        clientService.deleteById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<String> update(@Valid @RequestBody ClientDto clientDto) {
        Client client = clientConverter.toEntity(clientDto);
        clientService.update(client);
        return VO.response(Code.SUCCESS, "/client/info/" + client.getId());
    }

    @GetMapping("info/{id}")
    public VO<ClientVO> getInfoById(@PathVariable @NotNull(message = "id 不能为空") @Min(value = 1, message = "id 必须大于等于 1") Integer id) {
        Client client = clientService.findById(id);
        ClientVO clientVO = clientConverter.toVO(client);
        return VO.response(Code.SUCCESS, clientVO);
    }
}
