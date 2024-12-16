package com.gaotianchi.auth.dao.base;

import com.gaotianchi.auth.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Test)表数据库访问层
 *
 * @author gaotianchi
 * @since 2024-12-10 11:13:40
 */
@Mapper
public interface TestBaseDAO {

    void insertTest(Test test);

    void insertTestsInBatches(@Param("entities") List<Test> entities);

    void deleteTestById(Integer id);

    void deleteTestsInBatchesByIds(@Param("ids") List<Integer> ids);

    void updateTestById(Test test);

    void insertOrUpdateExistingTestsInBatches(@Param("entities") List<Test> entities);

    Test selectTestById(Integer id);

    long countByTest(Test test);

    List<Test> selectTestsByPage(@Param("entity") Test test, @Param("pageable") Pageable pageable);
}

