package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.TestBaseDao;
import com.gaotianchi.auth.base.entity.Test;
import com.gaotianchi.auth.base.service.TestBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024-12-10 11:16:08
 */
@Service("testBaseService")
public class TestBaseServiceImpl implements TestBaseService {

    private final TestBaseDao testBaseDao;

    public TestBaseServiceImpl(TestBaseDao testBaseDao) {
        this.testBaseDao = testBaseDao;
    }

    @Override
    public void addNewTest(Test test) {
        testBaseDao.insertTest(test);
    }

    @Override
    public void addNewTestsInBatches(List<Test> tests) {
        testBaseDao.insertTestsInBatches(tests);
    }

    @Override
    public void removeTestById(Integer id) {
        testBaseDao.deleteTestById(id);
    }

    @Override
    public void removeTestsInBatchesByIds(List<Integer> ids) {
        testBaseDao.deleteTestsInBatchesByIds(ids);
    }

    @Override
    public void updateTestById(Test test) {
        testBaseDao.updateTestById(test);
    }

    @Override
    public void addNewOrUpdateExistingTestsInBatches(List<Test> tests) {
        testBaseDao.insertOrUpdateExistingTestsInBatches(tests);
    }

    @Override
    public Test getTestById(Integer id) {
        return testBaseDao.selectTestById(id);
    }

    @Override
    public Page<Test> getTestsByPage(Test test, PageRequest pageRequest) {
        long total = testBaseDao.countByTest(test);
        List<Test> testList = testBaseDao.selectTestsByPage(test, pageRequest);
        return new PageImpl<>(testList, pageRequest, total);
    }
}
