package com.qxk.springall.springbootmvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@SpringBootTest
class SpringBootMvcApplicationTests {
    int i;

    @Test
    void contextLoads() throws ExecutionException, InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                SpringBootMvcApplicationTests tests = new SpringBootMvcApplicationTests();
                tests.i = 1;
            }
        },"");
        a.start();
        System.out.println("aaaa");
        AThread s = new AThread();
        s.start();
        System.out.println("ssss");
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });
        Thread thread = new Thread(integerFutureTask);
        System.out.println(integerFutureTask.get());
        thread.start();
        System.out.println(integerFutureTask.get());


    }

    class AThread extends Thread{

    }

}
