import java.util.List;

public class WorstFit implements Policy{
    @Override
    public Partition addToStorage(Process process, List<Partition> partitions) {
        int idx = 0;
        int size = -1;
        for(int i = 0; i < partitions.size(); i++){
            if(partitions.get(i).getSize() > size && partitions.get(i).getProcess() == null){
                size = partitions.get(i).getSize();
                idx = i;
            }
        }
        if(idx != 0){
            partitions.get(idx).setProcess(process);
            return partitions.get(idx);
        }
        return null;
    }
}