public class Task1 {
    public static void main(String[] args) {
        Thread timerThread = new Thread(() -> {
            int seconds = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                    seconds++;
                    System.out.println("Час від запуску: " + seconds + " сек.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread messageThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println("--- Минуло 5 секунд ---");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        timerThread.start();
        messageThread.start();
    }
}