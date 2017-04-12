// Counter.java

class Counter {
    private int counter;

    public synchronized int next() {
        return ++counter;
    }

    public int current() {
        return counter;
    }
}
