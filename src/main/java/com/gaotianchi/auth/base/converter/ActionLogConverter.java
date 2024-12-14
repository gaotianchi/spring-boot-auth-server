package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.ActionLogDTO;
import com.gaotianchi.auth.base.entity.ActionLog;
import com.gaotianchi.auth.base.vo.ActionLogVO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ActionLogConverter {

    ActionLog toEntity(ActionLogDTO dto);

    ActionLogVO toVO(ActionLog entity);
}