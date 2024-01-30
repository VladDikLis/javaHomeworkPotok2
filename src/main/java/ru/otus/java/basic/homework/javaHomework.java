package ru.otus.java.basic.homework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class javaHomework {
    private final Object mon = new Object();
    private char currentLetter = 'A';

    public static void main(String[] args) {
        javaHomework waitNotifyApp = new javaHomework();
        ExecutorService serv = Executors.newFixedThreadPool(3);
        serv.execute(() -> {
            waitNotifyApp.printA();
        });
        serv.execute(() -> {
            waitNotifyApp.printB();
        });
        serv.execute(() -> {
            waitNotifyApp.printC();
        });
        serv.shutdown();
    }

    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        mon.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    mon.notifyAll();
                    Thread.sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        mon.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    mon.notifyAll();
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        mon.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    mon.notifyAll();
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
