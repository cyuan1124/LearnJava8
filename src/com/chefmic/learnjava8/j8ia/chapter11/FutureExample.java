package com.chefmic.learnjava8.j8ia.chapter11;

import java.util.concurrent.*;

/**
 * Created by cyuan on 12/26/16.
 */
public class FutureExample {

    public void executeFuture() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }
        });

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private Double doSomeLongComputation() {
        return null;
    }

}
