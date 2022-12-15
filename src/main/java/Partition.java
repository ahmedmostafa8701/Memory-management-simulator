public class Partition {
    private int id;
    private int size;

    private Process process;

    public Partition(int size) {
        this.size = size;
    }

    public Partition(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Process getProcess() {
        return process;
    }

    public int setProcess(Process process) {
        this.process = process;
        int remain = size - process.getSize();
        size = process.getSize();
        return remain;
    }
    @Override
    public String toString() {
        return "Partition" + id;
    }
}
