# REST Response Pattern Using VO and Code

The REST endpoints should consistently use the `VO` class and `Code` enum for responses. Here's the correct pattern:

```java
@GetMapping("example")
public VO<ExampleVO> handleExampleRequest() {
    // On success:
    return VO.response(Code.SUCCESS, exampleVO);
    
    // On failure:
    return VO.response(Code.FAIL, null);
}
```

Key points:

1. All REST endpoints should return `VO<T>` where T is the appropriate response type
2. Use the static `VO.response()` method to create responses
3. Use the `Code` enum values (SUCCESS, FAIL) to indicate status
4. The message will be automatically set from the Code enum
5. Timestamp will be automatically set by the VO constructor

The current implementation needs to be updated to consistently follow this pattern across all REST controllers.