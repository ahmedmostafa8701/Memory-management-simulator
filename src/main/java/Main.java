import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        List<Process> processes = new ArrayList<Process>();
        System.out.println("Program starts...");
        System.out.print("Enter number of partitions: ");
        int num_partitions = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter partitions info:-");
        for(int i = 0; i < num_partitions; i++){
            System.out.print("Partition"+(i)+" size: ");
            int size = scanner.nextInt();
            fileManager.addPartition(size);
            System.out.println("--------------------------");
        }
        System.out.print("Enter number of processes: ");
        int num_processes = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter processes info:-");
        for(int i = 0; i < num_processes; i++){
            System.out.print("Process"+(i+1)+" name: ");
            String name = scanner.nextLine();
            System.out.print("Process"+(i+1)+" size: ");
            int size = scanner.nextInt();
            scanner.nextLine();
            processes.add(new Process(name,size));
            System.out.println("--------------------------");
        }
        while(true){
            List<Partition> partitions = new LinkedList<>();
            for (Partition partition : fileManager.getPartitions()) {
                Partition partition1 = new Partition(partition.getId(), partition.getSize());
                partitions.add(partition1);
            }
            FileManager fileManager1 = new FileManager(partitions);
            System.out.println("Select policy you want to apply: ");
            System.out.println("1)First fit");
            System.out.println("2)Best fit");
            System.out.println("3)Worst fit");
            System.out.println("4)Exit.");
            System.out.print("choice: ");
            int choice = scanner.nextInt();
            Policy policy = null;
            if(choice == 1){
                policy = new FirstFit();
            }else if (choice == 2){
                policy = new BestFit();
            }else if(choice == 3){
                policy = new WorstFit();
            }else{
                break;
            }
            if(!(1<=choice&&choice<=4)){
                System.out.println("Wrong option, please try again!");
                System.out.println("-------------------------------");
                continue;
            }
            List<Process> failed = new LinkedList<>();
            for(var process : processes){
                boolean added = fileManager1.addToStorage(process,policy);
                if(!added){
                    failed.add(process);
                }
            }
            for (Partition partition : fileManager1.getPartitions()) {
                System.out.println(partition);
            }
            for (Process process : failed) {
                System.out.println(process+" can not be allocated!");
            }
            System.out.println("Do you want to compact? 1)yes, 2)no");
            choice = scanner.nextInt();
            if(choice == 1){
                fileManager1.compaction();
                for (int i = 0; i < failed.size(); i++) {
                    boolean added = fileManager1.addToStorage(failed.get(i), policy);
                    if(added){
                        failed.remove(i);
                        i--;
                    }
                }
                for (Partition partition : fileManager1.getPartitions()) {
                    System.out.println(partition);
                }
                for (Process process : failed) {
                    System.out.println(process+" can not be allocated!");
                }
            }
        }
        scanner.close();
    }
}
/*
6
90
20
5
30
120
80
4
p1
15
p2
90
p3
30
p4
100
 */