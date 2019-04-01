import java.util.Date;

public class OSApp {
    public static Date[] dateMemory;
    public static Integer[] dataMemory;

    public static void main(String[] args) {
        dateMemory = new Date[(int) 1e6 + 2];
        dataMemory = new Integer[(int) 2e6 + 4];
        Scheduler s = new Scheduler();
        Thread mainthread = new Thread(s);
        mainthread.start();
//		new Thread(new App1(getRandomId()));
        Thread two = new Thread(new App1(getRandomId()));
        int pid1 = getRandomId();
        allocateMemory(pid1, 1, 3);
        new Thread(new App2(pid1));
        int pid2 = getRandomId();
        allocateMemory(pid2, 4, 3);
        Thread four = new Thread(new App2(pid2));
    }

    static int getRandomId() {
        return (int) (Math.random() * 1e6)+1;
    }

    static void allocateMemory(int pid, int x, int y) {
        dataMemory[pid*2] = x;
        dataMemory[pid*2+1] = y;
    }
    static void deallocateMemory(int pid) {
        dataMemory[pid*2] = null;
        dataMemory[pid*2+1] = null;
    }
}