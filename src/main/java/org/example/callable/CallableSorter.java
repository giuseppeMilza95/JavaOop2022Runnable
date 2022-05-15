package org.example.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CallableSorter implements Callable<Object> {
    private List<Integer> integerList;

    public CallableSorter(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public CallableSorter() {
    }

    @Override
    public Object call() throws Exception {

        int n = integerList.size();
        int temp = 0;

        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < (n - i - 1); j++) {

                if (integerList.get(j) > integerList.get(j + 1)) {
                    temp = integerList.get(j);
                    integerList.set(j, integerList.get(j + 1));
                    integerList.set(j + 1, temp);
                }
            }

        System.out.println(integerList);
        return integerList;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }
}




