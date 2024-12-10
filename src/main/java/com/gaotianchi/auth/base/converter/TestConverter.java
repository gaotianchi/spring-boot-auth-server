package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.TestDTO;
import com.gaotianchi.auth.base.entity.Test;
import com.gaotianchi.auth.base.vo.TestVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author gaotianchi
 * @since 2024/12/10 11:23
 **/
@Mapper(componentModel = "spring")
public interface TestConverter {

    @Mapping(target = "id", ignore = true)
    Test toEntity(TestDTO testDto);

    TestVO toVO(Test test);
}
