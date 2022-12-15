public class Process {
    private String name;
    private int size;

    public Process(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return name;
    }
}
