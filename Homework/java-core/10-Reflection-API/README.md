## Homework
### Building a Mini Testing Framework Using Java Reflection API
Develop a simple yet functional Mini Testing Framework in Java that leverages the Reflection API to automatically discover, execute test methods and report results.

### Minimum requirements
- Custom Annotations:
  - `@Test`: To mark methods as test cases. Annotation must have optional `parameterSource` attribute containing the name of the method that returns the input arguments for this test
  - `@BeforeMethod`, `@AfterMethod` for setup and teardown routines.
- Test Runner Implementation:
  - Write a TestRunner class that scans the classpath and finds all classes that contain public or package-public non-static methods annotated with `@Test` annotation.
  The runner should execute each test method and catch any exceptions or assertion errors.
- Result Reporting:
  - After executing test methods, the runner should report the results.
  The console output for each test should include the test name and whether it passed or failed.
  In case of failure, provide a brief reason
  - Example:
    ```
    ========================================
    Testing framework
    ========================================

    Running tests in MathTest class
      + Test: testMin - Passed
      - Test: testMin - Failed
          Reason: Expected -1 but got 0
      + Test: testMin - Passed
      - Test: testMax - Failed
          Reason: Expected 11 but got 10
    Running tests in StringUtilsTest class
      - Test: testConcat - Failed
          Reason: Expected Hello world! but got Hello World!

    ========================================
    Summary
    ========================================
    Total tests run: 5
    Passed tests: 2
    Failed tests: 3
    ```
- Implement at least the following assertions: `assertEquals(T, T)`, `assertThrows(Runnable)`, `assertReflectionEquals(T, T)`
- Demonstrate your framework on 1+ classes containing some simple business logic (e.g Math, StringUtil)
