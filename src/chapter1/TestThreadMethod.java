package chapter1;

/**
 * @Author easychill
 * @Date 2020/3/31 16:34
 * @Version 1.0
 */
public class TestThreadMethod {
    static void testSleep(){
        new Thread(()->{
           for (int i=0;i<5;i++){
               try {
                   System.out.println(i+"testSleep");
                   Thread.sleep(500);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
        }).start();
    }

    static void testYield(){
        new Thread(()->{
            for (int i=0;i<100;i++){
               System.out.println(i+"A+testYield");
               if(i%2==0){ Thread.yield();}
            }
        }).start();

        new Thread(()->{
            for (int i=0;i<100;i++){
                System.out.println(i+"B+testYield");
                if(i%2==0){ Thread.yield();}
            }
        }).start();
    }

    static void testJoin(){
        Thread t1 = new Thread(()->{
            for (int i=0;i<100;i++){
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(i+"A+testJoin");
            }
        });

        Thread t2 = new Thread(()->{
            for (int i=0;i<100;i++){
                try {
                    System.out.println(i+"B+testJoin");
                    t1.join();
//                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t1.start();
        t2.start();
    }
    public static void main(String[] args) {
        //sleep()：线程暂停一会
//        testSleep();
        //Yield()：当前线程挂起，进入等待序列，等待系统调用其他线程（有可能调用到了，刚才放回去的线程）
//        testYield();
        /**
        * 这个方法，如果不进睡眠，t1，启动后，马上开始执行，同时，t2马上启动，所有会有t1的部分输出，
         * 跑到前面，跑了几个以后，t2也开始运行，所以，输出一个t2的输出以后（0B+testJoin）
         * t1.join()，t1插队，把t1跑完以后，跑完t2。
         * 稍微给一点睡眠，t2就有足够的时间来先执行（0B+testJoin），因为t2的睡眠在后面，所以t2要先输出一次
        * */
        testJoin();
    }

}
