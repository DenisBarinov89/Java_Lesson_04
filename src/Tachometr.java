
//версия 1, с notifyAll() удалось запустить только с параметром (1) в методе wait
public class Tachometr extends Thread {

    static int secLeftFromStart = 0;

    synchronized void Stopwatch() {

        while (true) {
            try {
                Thread.sleep(1000);
                secLeftFromStart++;
                System.out.println(secLeftFromStart + " sec left.");
                notifyAll();
                wait(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    synchronized void Printer() {
        while (true) {
            try {

                if (secLeftFromStart % 5 == 0) {
                    System.out.println("MESSAGE: 5 seconds left");
                }
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

   synchronized void PrinterSevenSeconds() {
        while (true) {
            if (secLeftFromStart % 7 == 0 && secLeftFromStart != 0) {
                System.out.println("MESSAGE: 7 seconds left");
            }
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) {

        Tachometr tachometr = new Tachometr();
        Thread one = new Thread(tachometr::Printer);
        Thread two = new Thread(tachometr::Stopwatch);
        Thread three = new Thread(tachometr::PrinterSevenSeconds);

        two.start();
        one.start();
        three.start();
    }
}
