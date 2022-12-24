// Пример взаимной блокировки

class Thread1 extends Thread{

    public void run(){
        Thread.currentThread().setName("Thread1");
        System.out.println(Thread.currentThread().getName() + " is started");

        synchronized (Deadlock.synchronizedObject1){

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (Deadlock.synchronizedObject2){
                System.out.println("We are in Object2 synchronized block");
            }
        }

        System.out.println(Thread.currentThread().getName() + " is ended");
    }

}


class Thread2 extends Thread{

    public void run(){
        Thread.currentThread().setName("Thread2");
        System.out.println(Thread.currentThread().getName() + " is started");

        synchronized (Deadlock.synchronizedObject2){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (Deadlock.synchronizedObject1){
                System.out.println("We are in Object1 synchronized block");
            }
        }

        System.out.println(Thread.currentThread().getName() + " is ended");
    }

}

public class Deadlock{

    public static final Object synchronizedObject1 = new Object();
    public static final Object synchronizedObject2 = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();
        thread2.start();

    }

}