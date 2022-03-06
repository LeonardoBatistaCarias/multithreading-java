package com.leonardobatistacarias.parallelstreams;

import com.leonardobatistacarias.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayListSpliteratorExampleTest {

    ArrayListSpliteratorExample arrayListSpliteratorExample = new ArrayListSpliteratorExample();

    @Test
    void arrayListSpliteratorExample() {
        int size = 1000000;
        ArrayList<Integer> integerList = DataSet.generateArrayList(size);

        arrayListSpliteratorExample.multiplyEachValue(integerList, 2, false);

        assertEquals(size, integerList.size());
    }

}