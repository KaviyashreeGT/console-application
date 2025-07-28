package Rental_system;
import java.util.*;

class Vehicle{
    String number;
    String name;
    double cost;
    boolean isAvailable;
    public Vehicle(String number,String name,double cost,boolean isAvailable){
        this.number=number;
        this.name=name;
        this.cost=cost;
        this.isAvailable=true;
    }
}
class Member{
    int id;
    String name;
    double deposit;
    List<Vehicle>borrowList=new ArrayList<>();
    public Member(int id,String name,double deposit){
        this.id=id;
        this.name=name;
        this.deposit=deposit;
    }
    
}


public class RentalSystem {
    static Map<String,Vehicle>vehicles=new HashMap<>();
    static Map<Integer,Member>members=new HashMap<>();
    static Scanner sc=new Scanner(System.in);
    static int idcount=0;
    public static void main(String[] args) {
        while(true){
        System.out.println("Welcome to rental system");
        System.out.println("Enter Admin/User/exit:");
        String role=sc.next();

        if(role.toLowerCase().equals("admin")){
            System.out.println("Enter the username:");
            String username=sc.next();
            System.out.println("Enter the password");
            String password=sc.next();
            if(username.toLowerCase().equals("admin")&&password.equals("password")){
                System.out.println("welcome admin");
                adminMenu();
            }else{
                System.out.println("Incorrect password/username");
            }
        }
        else if(role.toLowerCase().equals("user")){
            System.out.println("Exiting/newuser:");
            String exist=sc.next();
            System.out.println("Enter the username:");
            String username=sc.next();
            double deposit=0;
            if(exist.toLowerCase().equals("newuser")){
                 idcount++;
                 System.out.println("Enter the deposit amount :");
                 deposit=sc.nextInt();
                 System.out.println("your id:"+idcount);
                 members.put(idcount,new Member(idcount, username, deposit));
            }
            userMenu(idcount);
            
        }else{
            return;
        }

    }
    }
    public static void adminMenu(){
        while(true){
            System.out.println("1.view vehicle list \n2.view member list \n3.add vehicle \n4.remove vehicle \n5.view Borrower List\n6.logout");
            int choice=sc.nextInt();
            switch (choice) {
                case 1:
                    viewVehicle();
                    break;
                case 2:
                    viewMember();
                    break;
                case 3:
                    addVehicle();
                    break;
                case 4:
                    removeVehicle();
                    break;
                case 5:
                    viewBorrower();
                    break;
                case 6:
                return;
                default:
                    System.out.println("invalid choice");
            }
        }
    }

    public static void addVehicle(){
        System.out.println("Enter vehicle number:");
        String number=sc.next();
        System.out.println("Enter vehicle name:");
        String name=sc.next();
        System.out.println("Enter vehicle cost:");
        double cost=sc.nextDouble();
        vehicles.put(number, new Vehicle(number, name,cost,true));
        System.out.println("vehicle added successfully.");
    }
    public static void removeVehicle(){
       System.out.println("Enter vehicle number:");
        String number=sc.next();
        vehicles.remove(number);
    }
    public static void viewVehicle(){
        System.out.println("vehicle list:");
        for(Vehicle v:vehicles.values()){
            if(v.isAvailable){
            System.out.println("vehicle number : "+v.number+"-"+"vehicle name: "+v.name);
        }}
    }
    public static void viewMember(){
        System.out.println("Members list:");
        for(Member m:members.values()){
            System.out.println("member id : "+m.id+" - "+" Name "+m.name);
        }
    }
    public static void viewBorrower(){
        for(Member m:members.values()){
            System.out.println("member id : "+m.id+" - "+" Name "+m.name);
            System.out.println("Borrow list of "+ m.name);
            for(Vehicle v:m.borrowList){
                System.out.println(v.number+"-"+v.name);
            }

        }
    }
    public static void userMenu(int idcount){
        while(true){
            System.out.println("UserMenu");
             System.out.println("1.viewVehicles\n2.borrow vehicles\3.return vehicles\4.logout");
             int choice=sc.nextInt();
             switch(choice){
                case 1:
                    viewVehicle();
                    break;
                case 2:
                    borrowVehicle(idcount);
                    break;
                case 3:
                    returnVehicle(idcount);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("invalid choice");

             }
        }
    }
    public static void borrowVehicle(int idcount){
        System.out.println("Enter the vehicle number:");
        String number=sc.next();
        Vehicle vehicle=vehicles.get(number);
        double cost=vehicle.cost; 
        Member member =members.get(idcount);
        if(cost<=member.deposit){
            member.deposit-=cost;
            System.out.println("Borrowed successfully");
            member.borrowList.add(new Vehicle(number, vehicle.name, cost,false));
            vehicle.isAvailable=false;

        }
        else{
            System.out.println("insufficient amount");
            System.out.println("Enter deposit amount");
            double amount=sc.nextDouble();
            member.deposit+=amount;
        }


    }
    public static void returnVehicle(int idcount){
        Member member=members.get(idcount);
        System.out.println("Enter the vehicle number:");
        String number=sc.next();
        Vehicle v=vehicles.get(number);
        
        v.isAvailable=true;
        member.borrowList.remove(v);


    }

    
}
