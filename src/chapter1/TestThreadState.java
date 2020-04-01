package chapter1;

/**
 * @Author easychill
 * @Date 2020/4/1 14:02
 * @Version 1.0
 */
public class TestThreadState {
    static class MyThread extends Thread{
        @Override
        public void run() {
            for (int i=0;i<10;i++){
                try {
                    Thread.sleep(500);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        Thread thread = new MyThread();
        try {
            //新建状态
            System.out.println("1"+thread.getState());
            thread.start();
            //RUNNABLE内部有两个状态ready就绪状态，running运行状态
            System.out.println("2"+thread.getState());
            Thread.sleep(1000);
            //TIMED_WAITING等待状态，wait
            System.out.println("3"+thread.getState());
            //线程跑完，程序在向后
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //TERMINATED结束状态，还有一个blocked阻塞状态
        System.out.println("4"+thread.getState());
    }
}
