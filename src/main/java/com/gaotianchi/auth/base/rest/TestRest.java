package com.gaotianchi.auth.base.rest;

import com.gaotianchi.auth.base.converter.TestConverter;
import com.gaotianchi.auth.base.dto.TestDTO;
import com.gaotianchi.auth.base.entity.Test;
import com.gaotianchi.auth.base.service.TestBaseService;
import com.gaotianchi.auth.base.vo.TestVO;
import com.gaotianchi.auth.base.vo.VO;
import com.gaotianchi.auth.enums.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gaotianchi
 * @since 2024/12/10 11:17
 **/
@RestController
@RequestMapping("test")
public class TestRest {

    private final TestBaseService testBaseService;
    private final TestConverter testConverter;

    public TestRest(TestBaseService testBaseService, TestConverter testConverter) {
        this.testBaseService = testBaseService;
        this.testConverter = testConverter;
    }

    @PostMapping("")
    public VO<String> handleCreateTestRequest(
            @RequestBody
            TestDTO testDto
    ) {
        Test test = testConverter.toEntity(testDto);
        testBaseService.addNewTest(test);
        return VO.response(Code.SUCCESS, "/test/info/" + test.getId());
    }

    @PostMapping("batch")
    public VO<String> handleCreateTestsBatchRequest(
            @RequestBody
            List<TestDTO> testDTOList
    ) {
        List<Test> tests = testDTOList
                .stream()
                .map(testConverter::toEntity)
                .toList();
        testBaseService.addNewTestsInBatches(tests);
        return VO.response(Code.SUCCESS, tests
                .stream()
                .map(test -> "/test/info/" + test.getId())
                .toList()
                .toString());
    }

    @DeleteMapping("")
    public VO<Void> handleRemoveTestByIdRequest(
            @RequestParam("id")
            Integer id
    ) {
        testBaseService.removeTestById(id);
        return VO.response(Code.SUCCESS, null);
    }

    @DeleteMapping("batch")
    public VO<Void> handleRemoveTestsBatchByIdsRequest(
            @RequestParam("ids")
            List<Integer> ids
    ) {
        testBaseService.removeTestsInBatchesByIds(ids);
        return VO.response(Code.SUCCESS, null);
    }

    @PutMapping("")
    public VO<String> handleUpdateTestRequest(
            @RequestBody
            TestDTO testDto
    ) {
        Test test = testConverter.toEntity(testDto);
        testBaseService.updateTestById(test);
        return VO.response(Code.SUCCESS, "/test/info/" + test.getId());
    }

    @PutMapping("batch")
    public VO<String> handleUpdateTestsBatchRequest(
            @RequestBody
            List<TestDTO> testDTOList
    ) {
        List<Test> tests = testDTOList
                .stream()
                .map(testConverter::toEntity)
                .toList();
        testBaseService.addNewOrUpdateExistingTestsInBatches(tests);
        return VO.response(Code.SUCCESS, tests
                .stream()
                .map(test -> "/test/info/" + test.getId())
                .toList()
                .toString());
    }

    @GetMapping("info/{id}")
    public VO<TestVO> handleGetTestByIdRequest(
            @PathVariable
            Integer id
    ) {
        Test test = testBaseService.getTestById(id);
        TestVO testVO = testConverter.toVO(test);
        return VO.response(Code.SUCCESS, testVO);
    }

    @GetMapping("info-list")
    public VO<Iterable<TestVO>> handleGetTestListRequest(
            @ModelAttribute TestDTO testDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Test test = testConverter.toEntity(testDto);
        PageRequest pageRequest = org.springframework.data.domain.PageRequest.of(page, size);
        Page<Test> testPage = testBaseService.getTestsByPage(test, pageRequest);
        Page<TestVO> testVOPage = testPage.map(testConverter::toVO);
        return VO.response(Code.SUCCESS, testVOPage);
    }
}
