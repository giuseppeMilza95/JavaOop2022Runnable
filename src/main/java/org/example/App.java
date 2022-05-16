package org.example;

import org.example.callable.CallableSorter;
import org.example.task12.jdbc.ObjectToSql;
import org.example.task12.jdbc.entity.Student;
import org.example.threads.Count;

import java.util.*;
import java.util.concurrent.*;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) throws InterruptedException, ExecutionException {


        // 9A

        System.out.println("*********************Task 9A*********************\n");
        Thread firstThread = new Thread(new Count("\u001B[31m"));
        Thread secondThread = new Thread(new Count("\u001B[32m"));
        firstThread.start();
        secondThread.start();


        //TASK 10
        System.out.println("*********************Task 10*********************\n");

        Map<Integer, ArrayList<Integer>> mapListOfInteger = Map.of(0, new ArrayList<>(Arrays.asList(5, 8, 9, 10, 3, 2, 0)), 1, new ArrayList<>(Arrays.asList(10, 2, 1, 9, 15)), 2, new ArrayList<>(Arrays.asList(10, 1, 20, 30, 40, 2, 11, 32, 44, 90, 99, 13, 21, 25)), 3, new ArrayList<>(Arrays.asList(6, 5, 4, 3, 2, 1, 0)));
        ExecutorService executor = Executors.newFixedThreadPool(4);


        try {
            //One By One
            for (int i = 0; i < 4; i++) {
                long start = Calendar.getInstance().getTimeInMillis();
                CallableSorter callableSorter = new CallableSorter(mapListOfInteger.get(i));
                callableSorter.call();
                long end = Calendar.getInstance().getTimeInMillis();
                long delta = end - start;
                System.out.println("Time taken to sort the array: " + delta);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // Same time


        CallableSorter callableSorter = new CallableSorter(mapListOfInteger.get(0));
        CallableSorter callableSorter1 = new CallableSorter(mapListOfInteger.get(1));
        CallableSorter callableSorter2 = new CallableSorter(mapListOfInteger.get(2));
        CallableSorter callableSorter3 = new CallableSorter(mapListOfInteger.get(3));


        //First Approach
        long start = Calendar.getInstance().getTimeInMillis();
        executor.submit(() -> {

            try {
                callableSorter.call();
                callableSorter1.call();
                callableSorter2.call();
                callableSorter3.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        long end = Calendar.getInstance().getTimeInMillis();
        long delta = end - start;
        System.out.println("Time taken to sort the array: " + delta);

        //Second Approach
        start = Calendar.getInstance().getTimeInMillis();
        List<CallableSorter> tasks = new ArrayList<>();
        tasks.add(callableSorter);
        tasks.add(callableSorter1);
        tasks.add(callableSorter2);
        tasks.add(callableSorter3);

        List<Future<Object>> resultList = executor.invokeAll(tasks);
        end = Calendar.getInstance().getTimeInMillis();
        delta = end - start;
        System.out.println("Time taken to sort the array: " + delta);

        for (Future<Object> future : resultList) {
            System.out.println("The result is: " + future.get() + " and task done is " + future.isDone());
        }


        // Third Approach

        FutureTask<Object> futureTask = new FutureTask<>(callableSorter);
        FutureTask<Object> futureTask1 = new FutureTask<>(callableSorter1);
        FutureTask<Object> futureTask2 = new FutureTask<>(callableSorter2);
        FutureTask<Object> futureTask3 = new FutureTask<>(callableSorter3);
        long timeStartFuture = Calendar.getInstance().getTimeInMillis();
        executor.execute(futureTask);
        executor.execute(futureTask1);
        executor.execute(futureTask2);
        executor.execute(futureTask3);
        long timeEndFuture = Calendar.getInstance().getTimeInMillis();
        long timeNeededFuture = timeEndFuture - timeStartFuture;
        System.out.println("Time taken to sort the array: " + timeNeededFuture);

        executor.shutdown();
        //Task 12 // 13 // 14
        System.out.println("*********************Task 12-13-14*********************\n");

        ObjectToSql objectToSql = new ObjectToSql();
        objectToSql.insert(new Student("Giuseppe", "Tumminello", "pino@live.it", "Male"));


    }


}
