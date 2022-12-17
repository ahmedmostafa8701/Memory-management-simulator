import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileManager {
    private List<Partition> partitions;
    private int generatedId = 0;

    public FileManager(List<Partition> partitions) {
        this.partitions = partitions;
        generatedId = partitions.size();
    }

    public FileManager() {
        this.partitions = new LinkedList<>();
    }

    void addPartition(int size){
        Partition partition = new Partition(generatedId++, size);
        partitions.add(partition);
    }

    public List<Partition> getPartitions() {
        return partitions;
    }
    private void addExternalFragment(int size, int position){
        Partition partition = new Partition(generatedId++, size);
        partitions.add(position, partition);
    }
    public boolean addToStorage(Process process, Policy policy){
        Partition partition = policy.addToStorage(process, partitions);
        if(partition == null){
            return false;
        }
        int remain = partition.setProcess(process);
        if(remain != 0){
            addExternalFragment(remain, partitions.indexOf(partition) + 1);
        }
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
            addPartition(size);
        }
    }
}
