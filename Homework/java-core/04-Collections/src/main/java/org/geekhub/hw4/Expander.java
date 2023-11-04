package org.geekhub.hw4;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Expander {

    //can accept any collection with any number
    //return double value if you get an empty collection return double maximum value
    double getMinValue(Collection<? extends Number> collection);

    //can accept any collection with any number
    //return double value if you get an empty collection return double minimum value
    double getMaxValue(Collection<? extends Number> collection);

    //can accept any collection with any number
    //return a double if you get an empty collection return 0.0
    double getSum(Collection<? extends Number> collection);

    //must combine the entire object into one line, each element must be separated by the provided delimiter,
    // it is better to use StringBuilder, no separator is needed after the last element;
    String join(Collection<?> collection, char delimiter);

    //Get a list of numbers, reverse the order of elements, return a double list
    List<Double> reversed(List<? extends Number> collection);

    //Get any collection, split it into a collection list, size determined by amount
    //a fragment must have the same number of elements, the maximum difference in size is 1
    List<List<Object>> chunked(Collection<?> collection, int amount);

    //if criterion is int, only one element by index should be deleted if criterion is an object
    //should remove all the same elements from the list
    //can take a list on any object
    List<?> dropElements(List<?> list, Object criteria);

    // should get any instance of the class and return a parameterized collection with the type of that instance
    //and with the passed object inside
    <T> List<T> getClassList(T t);

    //must remove all duplicates and nulls, order must be preserved
    <T> List<T> removeDuplicatesAndNull(List<T> collection);

    //must group all items separately and return a map where the key itself is the value and the value is the collection
    //all duplicate keys
    <T> Map<T, Collection<T>> grouping(Collection<T> collection);

    //should merge two map into one
    <T, U> Map<T, U> merge(Map<T, U> map1, Map<T, U> map2);

    //should set default value for each entry where value is null
    <T, U> Map<T, U> applyForNull(Map<T, U> map, U defaultValue);

    //must return a collection of elements where each element must meet the following requirements
    // be key in first or second map
    //if this is the key in the first, then the second map should have this value
    //if this is the key in second then the first map should have this value
    <T> Collection<T> collectingList(Map<T, T> map1, Map<T, T> map2);
}
