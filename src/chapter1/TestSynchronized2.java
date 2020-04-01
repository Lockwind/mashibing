package chapter1;

/**
 * @Author easychill
 * @Date 2020/4/1 15:44
 * @Version 1.0
 * 继承类子类调用super.m的时候必须是“可重入的”
 */
public class TestSynchronized2 {
    synchronized void m() throws InterruptedException {
        System.out.println("m start");
        Thread.sleep(1000);
        System.out.println("m end");
    }
    public static void main(String[] args) throws InterruptedException {
        new T().m();
    }
}

class T extends TestSynchronized2{
    @Override
    synchronized void m() throws InterruptedException {
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }
}
