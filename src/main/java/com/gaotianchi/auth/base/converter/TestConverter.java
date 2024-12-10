package com.gaotianchi.auth.base.converter;

import com.gaotianchi.auth.base.dto.TestDTO;
import com.gaotianchi.auth.base.entity.Test;
import com.gaotianchi.auth.base.vo.TestVO;
import org.mapstruct.Mapper;

/**
 * @author gaotianchi
 * @since 2024/12/10 11:23
 **/
@Mapper(componentModel = "spring")
public interface TestConverter {

    Test toEntity(TestDTO testDto);

    TestVO toVO(Test test);
}
