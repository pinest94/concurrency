package chap1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloWorldTest {

    int numberA = 0;
    int numberB = 0;
    boolean checked = false;

    @Test
    void thread10000() throws InterruptedException {
        for(int i = 0; i<10_000; i++) {
            helloWorldTest();
            checked = false;
        }

        System.out.println("numberA : " + numberA);
        System.out.println("numberB : " + numberB);
    }

    @Test
    void thread10000ByLambda() throws InterruptedException {
        for(int i = 0; i<10_000; i++) {
            helloWorldTestByLambda();
            checked = false;
        }

        System.out.println("numberA : " + numberA);
        System.out.println("numberB : " + numberB);
    }

    private void helloWorldTest() throws InterruptedException {

        Thread thread = new Thread() {
            @Override
            public void run() {
                if(!checked) {
                    numberA++;
                    checked = true;
                }
            }
        };

        thread.start();
        Thread.yield();
        if(!checked) {
            numberB++;
            checked = true;
        }
        thread.join();
    }

    private void helloWorldTestByLambda() throws InterruptedException {
        Thread thread = new Thread(() -> {
            if(!checked) {
                numberA++;
                checked = true;
            }
        });

        thread.start();
        Thread.yield();
        if(!checked) {
            numberB++;
            checked = true;
        }
        thread.join();
    }
}