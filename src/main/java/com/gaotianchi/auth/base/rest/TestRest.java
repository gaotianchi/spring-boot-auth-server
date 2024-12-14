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
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gaotianchi.auth.utils.RestTool.getPageRequest;

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
    public VO<String> handleAddNewTestRequest(
            @RequestBody
            TestDTO testDto
    ) {
        Test test = testConverter.toEntity(testDto);
        testBaseService.addNewTest(test);
        return VO.response(Code.SUCCESS, "/test/info/" + test.getId());
    }

    @PostMapping("batch")
    public VO<String> handleAddNewTestsInBatchesRequest(
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
    public VO<Void> handleRemoveTestsInBatchesByIdsRequest(
            @RequestParam("id")
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
    public VO<String> handleAddNewOrUpdateExistingTestsInBatchesRequest(
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

    @GetMapping("info")
    public VO<TestVO> handleGetTestByIdRequest(
            @RequestParam("id")
            Integer id
    ) {
        Test test = testBaseService.getTestById(id);
        TestVO testVO = testConverter.toVO(test);
        return VO.response(Code.SUCCESS, testVO);
    }

    /**
     * Handles the request to retrieve a paginated and sorted list of Test entities.
     * <p>
     * This method accepts the following parameters:
     * <ul>
     *     <li> testDto: DTO that contains filter criteria for the Test entities.</li>
     *     <li> page: The page number to retrieve (default is 0, i.e., the first page).</li>
     *     <li> size: The number of items per page (default is 10).</li>
     *     <li> sort: A list of sorting criteria in the format "field,direction" (e.g., "name,asc" or "age,desc").</li>
     * </ul>
     * The method converts the input DTO to an entity, constructs a dynamic sorting object based on the provided sorting
     * criteria, and then uses a `PageRequest` object to fetch a paginated result from the service layer.
     * The result is then mapped to a list of TestVO objects and returned as a response.
     * </p>
     *
     * @param testDto the filter criteria in the form of a TestDTO object
     * @param page    the page number for pagination (default is 0)
     * @param size    the number of records per page (default is 10)
     * @param sorts   a list of sorting criteria in the format "field,direction" (e.g., "name,asc")
     * @return a VO containing a paginated list of TestVO objects
     * @see TestDTO
     * @see TestVO
     * @see Sort
     * @see PageRequest
     */
    @GetMapping("info-list")
    public VO<Iterable<TestVO>> handleGetTestsByPageRequest(
            @ModelAttribute TestDTO testDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id:asc") List<String> sorts
    ) {
        Test test = testConverter.toEntity(testDto);
        PageRequest pageRequest = getPageRequest(page, size, sorts);
        Page<Test> testPage = testBaseService.getTestsByPage(test, pageRequest);
        Page<TestVO> testVOPage = testPage.map(testConverter::toVO);
        return VO.response(Code.SUCCESS, testVOPage);
    }
}
