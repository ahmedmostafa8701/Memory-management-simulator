import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private List<Process> processes;
    private List<Partition> partitions;

    public FileManager(List<Process> processes, List<Partition> partitions) {
        this.processes = processes;
        this.partitions = partitions;
    }

    public FileManager() {
        this.processes = new ArrayList<>();
        this.partitions = new ArrayList<>();
    }

    void addPartition(Partition partition){
        partitions.add(partition);
    }
    void addProcess(Process process){
        processes.add(process);
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public List<Partition> getPartitions() {
        return partitions;
    }
    private void addExternalFragment(int size){
        Partition partition = new Partition(partitions.size(), size);
        addPartition(partition);
    }
    public void addToStorage(Process process, Policy policy){
        Partition partition = policy.addToStorage(process, partitions);
        int remain = partition.setProcess(process);
        addExternalFragment(remain);
    }
    public void compaction(){
        int size = 0;
        for (int i = 0; i < partitions.size(); i++) {
            if(partitions.get(i).getProcess() == null){
                if(i == partitions.size() - 1){
                    break;
                }
            }
        }
    }
}
