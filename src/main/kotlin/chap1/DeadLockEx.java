package chap1;

import java.util.Random;

class Chopstick {
    private int id;
    public Chopstick(int id) { this.id = id; }
    public int getId() { return id; }
}

class DeadLockEx extends Thread {
    private Chopstick left, right;
    private Random random;
    private int thinkCount;

    public DeadLockEx(Chopstick left, Chopstick right) {
        this.left = left; this.right = right;
        random = new Random();
    }

    public void run() {
        try {
            while(true) {
                ++thinkCount;
                if (thinkCount % 10 == 0)
                    System.out.println("Philosopher " + this + " has thought " + thinkCount + " times");
                Thread.sleep(random.nextInt(1000));     // Think for a while
                synchronized(left) {                    // Grab left chopstick // <label id="code.syncleft"/>
                    synchronized(right) {                 // Grab right chopstick // <label id="code.syncright"/>
                        Thread.sleep(random.nextInt(1000)); // Eat for a while
                    }
                }
            }
        } catch(InterruptedException e) {}
    }
}
