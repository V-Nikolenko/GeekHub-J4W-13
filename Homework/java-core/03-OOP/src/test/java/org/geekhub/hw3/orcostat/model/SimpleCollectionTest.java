package org.geekhub.hw3.orcostat.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleCollectionTest {

    @Test
    void can_create() {
        new SimpleCollection();
    }

    @Test
    void can_create_and_init() {
        new SimpleCollection("first", "second");
    }

    @Test
    void can_add_element() {
        Collection collection = new SimpleCollection();

        collection.add(new Object());

        assertEquals(1, collection.size());
    }

    @Test
    void can_add_two_elements() {
        Collection collection = new SimpleCollection();

        collection.add(new Object());
        collection.add(new Object());

        assertEquals(2, collection.size());
    }

    @Test
    void can_add_different_type_elements() {
        Collection collection = new SimpleCollection("first");

        collection.add(new Object());
        collection.add("string");
        collection.add(new int[0]);

        assertEquals(4, collection.size());
    }

    @Test
    void cannot_add_undefined_element() {
        Collection collection = new SimpleCollection();

        collection.add(null);

        assertEquals(0, collection.size());
    }

    @Test
    void size_is_consistent() {
        Collection collection = new SimpleCollection();

        collection.add(new Object());

        assertEquals(collection.size(), collection.getElements().length);
    }

    @Test
    void content_is_protected_from_modification() {
        Collection collection = new SimpleCollection();
        String first = "first";
        String second = "second";
        collection.add(first);
        collection.add(second);

        collection.getElements()[0] = null;
        collection.getElements()[1] = null;

        assertEquals(collection.getElements()[0], first);
        assertEquals(collection.getElements()[1], second);
    }
}
