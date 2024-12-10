package com.gaotianchi.auth.base.rest;

import com.gaotianchi.auth.base.converter.ActionLogConverter;
import com.gaotianchi.auth.base.dto.ActionLogDTO;
import com.gaotianchi.auth.base.entity.ActionLog;
import com.gaotianchi.auth.base.service.ActionLogBaseService;
import com.gaotianchi.auth.base.vo.ActionLogVO;
import com.gaotianchi.auth.base.vo.VO;
import com.gaotianchi.auth.enums.Code;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("action-log")
@Validated
public class ActionLogRest {
    private final ActionLogBaseService actionLogBaseService;
    private final ActionLogConverter actionLogConverter;

    public ActionLogRest(ActionLogBaseService actionLogBaseService, ActionLogConverter actionLogConverter) {
        this.actionLogBaseService = actionLogBaseService;
        this.actionLogConverter = actionLogConverter;
    }

    @PostMapping("")
    public VO<String> handleCreateActionLogRequest(
            @Validated(ActionLogDTO.CreateActionLog.class) @RequestBody ActionLogDTO actionLogDto) {
        ActionLog actionLog = actionLogConverter.toEntity(actionLogDto);
        actionLogBaseService.addNewActionLog(actionLog);
        return VO.response(Code.SUCCESS, "/action-log/" + actionLog.getId());
    }

    @DeleteMapping("")
    public VO<Void> handleRemoveActionLogByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be null")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id) {
        actionLogBaseService.removeActionLogById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @GetMapping("info/{id}")
    public VO<ActionLogVO> handleGetActionLogByIdRequest(
            @PathVariable("id")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id) {
        ActionLog actionLog = actionLogBaseService.getActionLogById(id);
        ActionLogVO actionLogVO = actionLogConverter.toVO(actionLog);
        return VO.response(Code.SUCCESS, actionLogVO);
    }

    @GetMapping("info-list")
    public VO<Page<ActionLogVO>> handleGetActionLogListRequest(
            @ModelAttribute ActionLogDTO actionLogDto,
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "page must be greater than or equal to 0")
            int page,
            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "size must be greater than or equal to 1")
            int size) {
        ActionLog actionLog = actionLogConverter.toEntity(actionLogDto);
        Page<ActionLog> actionLogPage = actionLogBaseService.getActionLogsByPage(actionLog, PageRequest.of(page, size));
        Page<ActionLogVO> actionLogVOPage = actionLogPage.map(actionLogConverter::toVO);
        return VO.response(Code.SUCCESS, actionLogVOPage);
    }
}