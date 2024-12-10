package com.gaotianchi.auth.base.dao;

import com.gaotianchi.auth.base.entity.Test;
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
public interface TestBaseDao {

    int insertTest(Test test);

    int insertTestsInBatches(@Param("entities") List<Test> entities);

    int deleteTestById(Integer id);

    int deleteTestsInBatchesByIds(@Param("ids") List<Integer> ids);

    int updateTestById(Test test);

    int insertOrUpdateExistingTestsInBatches(@Param("entities") List<Test> entities);

    Test selectTestById(Integer id);

    long countByTest(Test test);

    List<Test> selectTestsByPage(Test test, @Param("pageable") Pageable pageable);
}

