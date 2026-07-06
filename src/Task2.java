import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task2 {

    public static class FizzBuzz {
        private int n;
        private int current = 1;
        private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        public FizzBuzz(int n) {
            this.n = n;
        }

        public synchronized void fizz() {
            while (current <= n) {
                if (current % 3 == 0 && current % 5 != 0) {
                    queue.add("fizz");
                    current++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }

        public synchronized void buzz() {
            while (current <= n) {
                if (current % 5 == 0 && current % 3 != 0) {
                    queue.add("buzz");
                    current++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }

        public synchronized void fizzbuzz() {
            while (current <= n) {
                if (current % 15 == 0) {
                    queue.add("fizzbuzz");
                    current++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }

        public synchronized void number() {
            while (current <= n) {
                if (current % 3 != 0 && current % 5 != 0) {
                    queue.add(String.valueOf(current));
                    current++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }

        public void printQueue() {
            System.out.println(String.join(", ", queue));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(15);

        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzbuzz);
        Thread threadD = new Thread(fizzBuzz::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();

        fizzBuzz.printQueue();
    }
}