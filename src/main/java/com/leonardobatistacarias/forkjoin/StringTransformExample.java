package com.leonardobatistacarias.forkjoin;

import com.leonardobatistacarias.util.DataSet;

import java.util.ArrayList;
import java.util.List;

import static com.leonardobatistacarias.util.CommonUtil.delay;
import static com.leonardobatistacarias.util.CommonUtil.stopWatch;
import static com.leonardobatistacarias.util.LoggerUtil.log;

public class StringTransformExample {

    public static void main(String[] args) {

        stopWatch.start();
        List<String> resultList = new ArrayList<>();
        List<String> names = DataSet.namesList();
        log("names : "+ names);

        names.forEach((name)->{
            String newValue = addNameLengthTransform(name);
            resultList.add(newValue);
        });
        stopWatch.stop();
        log("Final Result : "+ resultList);
        log("Total Time Taken : "+ stopWatch.getTime());
    }


    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}
