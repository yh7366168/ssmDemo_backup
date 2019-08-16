package test;

/**
 * @author yh 2019-6-19 10:52:33
 * */
public class MyThreadPool {

    public static void main(String[] args) {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuNum);
    }
}
