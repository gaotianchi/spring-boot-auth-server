package com.gaotianchi.auth.base.service.impl;

import com.gaotianchi.auth.base.dao.TestBaseDao;
import com.gaotianchi.auth.base.entity.Test;
import com.gaotianchi.auth.base.service.TestBaseService;
import com.gaotianchi.auth.enums.Code;
import com.gaotianchi.auth.exception.SQLException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * (Test)表服务实现类
 *
 * @author gaotianchi
 * @since 2024-12-10 11:16:08
 */
@Service("testBaseService")
public class TestBaseServiceImpl implements TestBaseService {

    private final TestBaseDao testBaseDao;

    public TestBaseServiceImpl(TestBaseDao testBaseDao) {
        Assert.notNull(testBaseDao, "TestRepository cannot be null");
        this.testBaseDao = testBaseDao;
    }

    @Override
    public void addNewTest(Test test) {
        if (testBaseDao.insertTest(test) != 1) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void addNewTestsInBatches(List<Test> tests) {
        if (testBaseDao.insertTestsInBatches(tests) != tests.size()) {
            throw new SQLException(Code.SQL_INSERT_ERROR);
        }
    }

    @Override
    public void removeTestById(Integer id) {
        if (testBaseDao.deleteTestById(id) != 1) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void removeTestsInBatchesByIds(List<Integer> ids) {
        if (testBaseDao.deleteTestsInBatchesByIds(ids) != ids.size()) {
            throw new SQLException(Code.SQL_DELETE_ERROR);
        }
    }

    @Override
    public void updateTestById(Test test) {
        if (testBaseDao.updateTestById(test) != 1) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public void addNewOrUpdateExistingTestsInBatches(List<Test> tests) {
        if (testBaseDao.insertOrUpdateExistingTestsInBatches(tests) != tests.size()) {
            throw new SQLException(Code.SQL_UPDATE_ERROR);
        }
    }

    @Override
    public Test getTestById(Integer id) {
        return testBaseDao.selectTestById(id);
    }

    @Override
    public Page<Test> getTestsByPage(Test test, PageRequest pageRequest) {
        long total = testBaseDao.countByTest(test);
        return new PageImpl<>(testBaseDao.selectTestsByPage(test, pageRequest), pageRequest, total);
    }


}
