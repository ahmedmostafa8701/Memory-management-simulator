import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<Partition> partitions = new ArrayList<Partition>();
        List<Process> processes = new ArrayList<Process>();

        System.out.println("Program starts...");
        System.out.print("Enter number of partitions: ");
        int num_partitions = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter partitions info:-");
        for(int i = 0; i < num_partitions; i++){
            System.out.print("Partition"+(i+1)+" name: ");
            String name = scanner.nextLine();
            System.out.print("Partition"+(i+1)+" size: ");
            int size = scanner.nextInt();
            scanner.nextLine();
            partitions.add(new Partition(i,name, size));
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

        FileManager fileManager = new FileManager(partitions);

        while(true){
            System.out.println("Select policy you want to apply: ");
            System.out.println("1)First fit");
            System.out.println("2)Worst fit");
            System.out.println("3)Best fit");
            System.out.println("4)Exit.");
            System.out.print("choice: ");

            int choice = scanner.nextInt();

            Policy policy = null;
            if(choice == 1){
                policy = new FirstFit();
            }else if (choice == 2){
                policy = new WorstFit();
            }else if(choice == 3){
                policy = new BestFit();
            }else{
                break;
            }
            if(!(1<=choice&&choice<=4)){
                System.out.println("Wrong option, please try again!");
                System.out.println("-------------------------------");
                continue;
            }

            for(var process : processes){
                boolean added = fileManager.addToStorage(process,policy);
                if(!added){
                    System.out.println(process.getName()+" can not be allocated!");
                }
            }
            fileManager.getPartitions().forEach(partition -> {
                System.out.print(partition.getName()+" ("+partition.getSize()+" KB) => ");
                System.out.println(partition.getProcess()!=null?partition.getProcess().getName():"External fragment:");
            });

            System.out.println("Do you want to compact? 1)yes, 2)no");
            choice = scanner.nextInt();
            if(choice == 1){
                fileManager.compaction();
            }
        }
        scanner.close();
    }
}
/*
6
par0
90
par1
20
par2
5
par3
30
par4
120
par5
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