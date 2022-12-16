import java.util.List;

public class FirstFit implements Policy{
    @Override
    public Partition addToStorage(Process process, List<Partition> partitions) {
        for (Partition partition : partitions) {
            if(partition.getProcess() == null && partition.getSize() >= process.getSize()){
                return partition;
            }
        }
        return null;
    }
}