package server;

/*
1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
 */

public class SomeThreads {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args){
        SomeThreads threads = new SomeThreads();
        Thread t = new Thread(threads::printA);
        Thread t1 = new Thread(threads::printB);
        Thread t2 = new Thread(threads::printC);

        t.start();
        t1.start();
        t2.start();
    }

    public void printA(){
        synchronized(monitor){
            try{
                for (int i = 0; i<5; i++){
                    while(currentLetter !='A'){
                        monitor.wait();
                    }
                    System.out.print('A');
                    currentLetter = 'B';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB(){
        synchronized(monitor){
            try {
                for (int i = 0; i<5; i++){
                    while(currentLetter != 'B'){
                        monitor.wait();
                    }
                    System.out.print('B');
                    currentLetter = 'C';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC(){
        synchronized(monitor){
            try{
                for (int i = 0; i<5; i++){
                    while(currentLetter != 'C'){
                        monitor.wait();
                    }
                    System.out.print('C');
                    currentLetter = 'A';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
