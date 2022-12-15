import java.util.List;

public interface Policy {
    public Partition addToStorage(Process process, List<Partition> partitions);
}
