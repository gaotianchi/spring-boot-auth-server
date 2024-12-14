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

import java.util.List;

import static com.gaotianchi.auth.utils.RestTool.getPageRequest;

@RestController
@RequestMapping("action-log")
@Validated
public class ActionLogBaseRest {
    private final ActionLogBaseService actionLogBaseService;
    private final ActionLogConverter actionLogConverter;

    public ActionLogBaseRest(ActionLogBaseService actionLogBaseService, ActionLogConverter actionLogConverter) {
        this.actionLogBaseService = actionLogBaseService;
        this.actionLogConverter = actionLogConverter;
    }

    @PostMapping("")
    public VO<String> handleAddNewActionLogRequest(
            @Validated(ActionLogDTO.CreateActionLog.class) @RequestBody ActionLogDTO actionLogDto) {
        ActionLog actionLog = actionLogConverter.toEntity(actionLogDto);
        actionLogBaseService.addNewActionLog(actionLog);
        return VO.response(Code.SUCCESS, "/action-log/" + actionLog.getId());
    }

    @PostMapping("batch")
    public VO<Void> handleAddNewActionLogsInBatchesRequest(
            @RequestBody
            List<ActionLogDTO> actionLogDtoList
    ) {
        List<ActionLog> actionLogs = actionLogDtoList
                .stream()
                .map(actionLogConverter::toEntity)
                .toList();
        actionLogBaseService.addNewActionLogsInBatches(actionLogs);
        return VO.response(Code.SUCCESS, null);
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

    @DeleteMapping("batch")
    public VO<Void> handleRemoveActionLogsInBatchesRequest(
            @RequestBody
            List<Integer> idList
    ) {
        actionLogBaseService.removeActionLogsInBatchesByIds(idList);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<Void> handleUpdateActionLogByIdRequest(
            @RequestParam("id")
            @NotNull(message = "id cannot be null")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id,
            @RequestBody ActionLogDTO actionLogDto) {
        ActionLog actionLog = actionLogConverter.toEntity(actionLogDto);
        actionLogBaseService.updateActionLogById(actionLog);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("batch")
    public VO<Void> handleUpdateActionLogsInBatchesRequest(
            @RequestBody
            List<ActionLogDTO> actionLogDtoList
    ) {
        List<ActionLog> actionLogs = actionLogDtoList
                .stream()
                .map(actionLogConverter::toEntity)
                .toList();
        actionLogBaseService.addNewOrUpdateExistingActionLogsInBatches(actionLogs);
        return VO.response(Code.SUCCESS, null);
    }

    @GetMapping("info")
    public VO<ActionLogVO> handleGetActionLogByIdRequest(
            @RequestParam("id")
            @Min(value = 1, message = "id must be greater than or equal to 1")
            Integer id) {
        ActionLog actionLog = actionLogBaseService.getActionLogById(id);
        ActionLogVO actionLogVO = actionLogConverter.toVO(actionLog);
        return VO.response(Code.SUCCESS, actionLogVO);
    }

    @GetMapping("info-list")
    public VO<Page<ActionLogVO>> handleGetActionLogListRequest(
            @ModelAttribute ActionLogDTO actionLogDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id:asc") List<String> sorts
    ) {
        ActionLog actionLog = actionLogConverter.toEntity(actionLogDto);
        PageRequest pageRequest = getPageRequest(page, size, sorts);
        Page<ActionLog> actionLogPage = actionLogBaseService.getActionLogsByPage(actionLog, pageRequest);
        Page<ActionLogVO> actionLogVOPage = actionLogPage.map(actionLogConverter::toVO);
        return VO.response(Code.SUCCESS, actionLogVOPage);
    }
}