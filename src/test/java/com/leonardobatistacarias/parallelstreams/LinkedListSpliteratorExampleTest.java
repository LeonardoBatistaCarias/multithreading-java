package com.leonardobatistacarias.parallelstreams;

import com.leonardobatistacarias.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListSpliteratorExampleTest {

    LinkedListSpliteratorExample linkedListSpliteratorExample = new LinkedListSpliteratorExample();

    @Test
    void linkedListSpliteratorExample() {

        int size = 1000000;
        LinkedList<Integer> integerList = DataSet.generateIntegerLinkedList(size);

        linkedListSpliteratorExample.multiplyEachValue(integerList, 2, true);

        assertEquals(size, integerList.size());
    }

}