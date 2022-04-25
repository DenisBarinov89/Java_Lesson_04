
//версия 2 - тут только два потока работают через notify(), а третий просто ждет секунду, затем просыпается.
public class Tachometr2 extends Thread {

    static int secLeftFromStart = 0;

    synchronized void Stopwatch() {

        while (true) {
            try {
                Thread.sleep(1000);
                secLeftFromStart++;
                System.out.println(secLeftFromStart + " sec left.");
                notify();
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    synchronized void Printer() {
        while (true) {
            try {

                if (secLeftFromStart % 5 == 0 && secLeftFromStart !=0) {
                    System.out.println("MESSAGE: 5 seconds left");
                }
                notify();
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
              wait(1000);
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
