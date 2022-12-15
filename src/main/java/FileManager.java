import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private List<Partition> partitions;

    public FileManager(List<Partition> partitions) {
        this.partitions = partitions;
    }

    public FileManager() {
        this.partitions = new ArrayList<>();
    }

    void addPartition(Partition partition){
        partition.setId(partitions.size());
        partitions.add(partition);
    }

    public List<Partition> getPartitions() {
        return partitions;
    }
    private void addExternalFragment(int size){
        Partition partition = new Partition(size);
        addPartition(partition);
    }
    public boolean addToStorage(Process process, Policy policy){
        Partition partition = policy.addToStorage(process, partitions);
        if(partition == null){
            return false;
        }
        int remain = partition.setProcess(process);
        addExternalFragment(remain);
        return true;
    }
    public void compaction(){
        int size = 0;
        int i;
        for (i = 0; i < partitions.size(); i++) {
            if(partitions.get(i).getProcess() == null){
                if(i == partitions.size() - 1){
                    break;
                }
                size += partitions.get(i).getSize();
                partitions.remove(i);
                i--;
            }
        }
        if(i == partitions.size() - 1){
            partitions.get(i).setSize(partitions.get(i).getSize() + size);
        }else {
            addExternalFragment(size);
        }
    }
}
