public class OSApp {
    public static void main(String[] args) {
        Scheduler s = new Scheduler();
        Thread mainthread = new Thread(s);
        mainthread.start();
    new Thread(new App1(getRandomId()));
//        Thread two = new Thread(new App1(getRandomId()));
//        new Thread(new App2(getRandomId(), 2, 2));
//        Thread four = new Thread(new App2(getRandomId(),3,4));
    }

    static int getRandomId() {
        return (int) (Math.random() * 1e9);
    }
}