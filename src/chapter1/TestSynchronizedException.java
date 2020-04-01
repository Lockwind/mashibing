package chapter1;

/**
 * @Author easychill
 * @Date 2020/4/1 15:54
 * @Version 1.0
 * 异常锁：
 * 程序在执行的过程中，如果出现了异常，默认情况锁是会被释放的
 * 所以，在并发处理的过程中，有异常一定要小心，不然程序可能不会按照预期的样子走
 * 下面的例子中，t1抛出了异常，其他线程（t2）就会进入同步代码区（程序乱入）
 *
 */
public class TestSynchronizedException {
    synchronized void m() throws InterruptedException {
        int count = 0;
        System.out.println(Thread.currentThread().getName()+" start");
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()+" count = "+count);
            Thread.sleep(1000);
            if (count==5){
                //人为异常,异常之后锁就会被释放，t2就会开始运行(正常情况，这里是死循环，t2是不可能拿到这个方法的)
                int i = 1/0;
//                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestSynchronizedException e = new TestSynchronizedException();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    e.m();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };
        new Thread(r,"t1").start();
        Thread.sleep(1000);
        new Thread(r,"t2").start();
    }
}
