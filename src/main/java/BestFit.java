import java.util.List;

public class BestFit implements Policy{
    @Override
    public Partition addToStorage(Process process, List<Partition> partitions) {
        int idx = -1;
        int size = Integer.MAX_VALUE;
        for(int i = 0; i < partitions.size(); i++){
            if(partitions.get(i).getProcess() == null && partitions.get(i).getSize() < size && partitions.get(i).getSize() >= process.getSize()){
                size = partitions.get(i).getSize();
                idx = i;
            }
        }
        if(idx == -1){
            return null;
        }
        return partitions.get(idx);
    }
}
