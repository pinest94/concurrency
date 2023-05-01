package chap1;

public class Counting {
    public static void main(String[] args) throws InterruptedException {
        class Counter {
            private int count = 0;

            /***
             * synchronized를 추가하지 않으면 원하는 값이 나오지 않는다.
             */
            public synchronized void increment() { count++; }

            public int getCount() { return count; }
        }
        final Counter counter = new Counter();
        class CountingThread extends Thread {
            @Override
            public void run() {
                for(int x = 0; x<10_000; x++) {
                    counter.increment();
                }
            }
        }

        CountingThread c1 = new CountingThread();
        CountingThread c2 = new CountingThread();

        c1.start();
        c2.start();

        c1.join();
        c2.join();

        System.out.println(counter.getCount());
    }
}
