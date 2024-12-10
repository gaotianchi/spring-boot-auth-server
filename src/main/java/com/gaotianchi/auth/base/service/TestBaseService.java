package com.gaotianchi.auth.base.service;

import com.gaotianchi.auth.base.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Test)表服务接口
 *
 * @author gaotianchi
 * @since 2024-12-10 11:16:08
 */
public interface TestBaseService {

    void addNewTest(Test test);

    void addNewTestsInBatches(List<Test> tests);

    void removeTestById(Integer id);

    void removeTestsInBatchesByIds(List<Integer> ids);

    void updateTestById(Test test);

    void addNewOrUpdateExistingTestsInBatches(List<Test> tests);

    Test getTestById(Integer id);

    Page<Test> getTestsByPage(Test test, PageRequest pageRequest);
}
