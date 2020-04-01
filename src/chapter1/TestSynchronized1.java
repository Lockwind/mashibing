package chapter1;

/**
 * @Author easychill
 * @Date 2020/4/1 15:31
 * @Version 1.0
 * 测试锁的：可重入 特性：同一线程拿锁里面的锁是不会被拒绝的。
 */
public class TestSynchronized1 {
    public synchronized void m1(String s) throws InterruptedException {
        System.out.println("m1 start"+s);
        Thread.sleep(1000);
        m2(s);
        System.out.println("m1 end"+s);
    }
    public synchronized void m2(String s){
        System.out.println("m2 start"+s);
        System.out.println("m2 end"+s);
    }

    public static void main(String[] args) throws InterruptedException {
        new TestSynchronized1().m1("线程1");
//        new Thread(()-> {
//            try {
//                System.out.println("new thread");
//                new TestSynchronized1().m1("线程2");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
