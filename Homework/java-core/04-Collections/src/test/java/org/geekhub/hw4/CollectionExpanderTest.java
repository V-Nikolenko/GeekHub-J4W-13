package org.geekhub.hw4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CollectionExpanderTest {
    private final CollectionExpander expander = new CollectionExpander();

    @Test
    void shouldReturnMinValueForCollection_list() {
        var expectedResult = 12.0;
        var actualResult = expander.getMinValue(List.of(12, 22, 34));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnMinValueForCollection_set() {
        var expectedResult = 12.0;
        var actualResult = expander.getMinValue(Set.of(12, 22, 34));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnMinValueForCollection_whenGetEmptyCollection() {
        var actualResult = expander.getMinValue(Set.of());

        assertEquals(Double.MAX_VALUE, actualResult);
    }

    @Test
    void shouldReturnMaxValueForCollection_list() {
        var expectedResult = 34.0;
        var actualResult = expander.getMaxValue(List.of(12, 22, 34));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnMaxValueForCollection_set() {
        var expectedResult = 34.0;
        var actualResult = expander.getMaxValue(Set.of(12, 22, 34));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnMaxValueForCollection_whenGetEmptyCollection() {
        var actualResult = expander.getMaxValue(Set.of());

        assertEquals(Double.MIN_VALUE, actualResult);
    }

    @Test
    void shouldReturnSummary_ofAllCollectionElements_list() {
        var actualResult = expander.getSum(List.of(15, 15));

        assertEquals(30, actualResult);
    }

    @Test
    void shouldReturnSummary_ofAllCollectionElements_set() {
        var actualResult = expander.getSum(Set.of(15, 16));

        assertEquals(31, actualResult);
    }

    @Test
    void shouldReturnSummary_ofAllCollectionElements_emptySet() {
        var actualResult = expander.getSum(Set.of());

        assertEquals(0.0, actualResult);
    }

    @Test
    void shouldJoinAllValues_fromAnyCollection() {
        var expectedResult = "Test/Test1/AnotherTest";
        var actualResult = expander.join(List.of("Test", "Test1", "AnotherTest"), '/');

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldJoinAllValues_fromAnyCollection_emptyCollection() {
        var actualResult = expander.join(List.of(), '/');

        assertEquals("", actualResult);
    }

    @Test
    void shouldReturnOrderOfTheElements_inTheList() {
        var expectedResult = List.of(3.0, 2.0, 1.0);
        var actualResult = expander.reversed(List.of(1, 2, 3));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldSplitCollection_intoAChunk() {
        var expectedResult = List.of(
            List.of(1, 4, 7), List.of(2, 5), List.of(3, 6));
        var actualResult = expander.chunked(List.of(1, 2, 3, 4, 5, 6, 7), 3);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldRemove_onlyOneElement_byIndex() {
        List<String> requestList = new ArrayList<>();
        requestList.add("test1");
        requestList.add("test2");
        requestList.add("test3");
        requestList.add("test4");

        var actualResult = expander.dropElements(requestList, 3);
        var expectedResult = List.of("test1", "test2", "test3");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldRemove_onlyAllSameElements() {
        List<String> requestList = new ArrayList<>();
        requestList.add("test1");
        requestList.add("test");
        requestList.add("test");
        requestList.add("test");

        var actualResult = expander.dropElements(requestList, "test");
        var expectedResult = List.of("test1");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnList_ofParticularClassWith_oneElement() {
        List<RuntimeException> expectedResult = new ArrayList<>();
        var exception = new RuntimeException();
        expectedResult.add(exception);

        var actualResult = expander.getClassList(exception);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldRemoveDuplicates_andNull_orderMustLeft() {
        var ob1 = new Object();
        var obj2 = new Object();
        List<Object> requestList = new ArrayList<>();
        requestList.add(obj2);
        requestList.add(obj2);
        requestList.add(obj2);
        requestList.add(ob1);
        requestList.add(ob1);
        requestList.add(null);

        var actualResult = expander.removeDuplicatesAndNull(requestList);

        var expectedResult = List.of(obj2, ob1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGroupElements() {
        var obj1 = new Object();
        var obj2 = new Object();
        var obj3 = new Object();

        var expectedMap = Map.of(
            obj1, List.of(obj1, obj1),
            obj2, List.of(obj2),
            obj3, List.of(obj3, obj3)
        );
        var actualMap = expander.grouping(
            List.of(obj1, obj1, obj2, obj3, obj3)
        );

        assertEquals(expectedMap, actualMap);
    }

    @Test
    void shouldMerge_twoMaps() {
        var requestMap1 = new HashMap<String, Integer>();
        requestMap1.put("1", 1);
        requestMap1.put("2", 2);

        var requestMap2 = new HashMap<String, Integer>();
        requestMap1.put("3", 3);
        requestMap1.put("4", 4);

        var actualResult = expander.merge(requestMap1, requestMap2);
        var expectedResult = Map.of(
            "1", 1,
            "2", 2,
            "3", 3,
            "4", 4
        );

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldUpdateValue_withDefaultOne_whereValueIsNull() {
        Map<Integer, String> requestMap = new HashMap<>();
        requestMap.put(1, null);
        requestMap.put(2, "test");

        var actualMap = expander.applyForNull(requestMap, "default");
        var expectedMap = Map.of(
            1, "default",
            2, "test"
        );

        assertEquals(expectedMap, actualMap);
    }

    @Test
    void shouldCollectListFromTwoMaps() {
        var map1 = Map.of(
            1, 2,
            2, 90,
            3, 30,
            9, 23
        );

        var map2 = Map.of(
            2, 1,
            45, 3,
            56, 90,
            76, 43,
            23, 9
        );

        var actualResult = expander.collectingList(map1, map2);
        var expectedResult = Set.of(1, 2, 9, 23, 3);

        assertEquals(expectedResult, actualResult);
    }
}
