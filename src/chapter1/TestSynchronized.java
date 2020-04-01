package chapter1;

/**
 * @Author easychill
 * @Date 2020/4/1 14:51
 * @Version 1.0
 * 测试了一些锁的基本特性，
 */
public class TestSynchronized {
    String name="";
    Double balance=0.00;

    public synchronized void set(String name,double balance) throws InterruptedException {
        this.name = name;
        Thread.sleep(2000);
        this.balance = balance;
    }

    public /*synchronized*/ Double getBalance(){
        return this.balance;
    }
    public static void main(String[] args) throws InterruptedException {
        /*
        * getBalance方法不加锁的话，会有下面的脏读问题（synchronized和非synchronized方法的同时执行），
        * 但是加锁之后就不会有数据的脏读，但是效率会变低
        * */

        TestSynchronized t = new TestSynchronized();
        new Thread(()-> {
            try {
                t.set("sjt",1000.00);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        System.out.println(t.getBalance());
        //这里睡1秒有可能拿不到1000这个余额，因为线程设置余额前睡了2秒，上一步睡1秒，这里睡一秒，可能刚好还没赋值，就走到了输出，
        //也可能已经赋值完了，然后读到了
        Thread.sleep(1000);
        System.out.println(t.getBalance());
        //但是这句是肯定能读到的！
        System.out.println(t.getBalance());
    }
}
