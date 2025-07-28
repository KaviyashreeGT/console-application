

package LiftSystem;
import java.util.*;
class Lift{
    Scanner sc=new Scanner(System.in);
    int currentState=0;
    boolean movingup=true;
    int queuelist=0;
    Queue <Integer>nextStops=new LinkedList<>();
    public void currentState(){
        System.out.println("Current state:"+currentState);
        System.out.println("Moving Up:"+movingup);
    }
    public void giveRequest(){
        try{
        System.out.println("Enter the requesting floor(0-10):");
        int floor=sc.nextInt();
        nextStops.offer(floor);
        }catch(InputMismatchException e){
            System.out.println("Invlid type of input");
            sc.nextLine();
        }
        

    }
    public void giveDestination(){
        try{
        System.out.println("Enter the destination request:");
        int floor=sc.nextInt();
        if(floor<0||floor>10){
            System.out.println("Enter the valid input type which is number");
            return;
        }
        nextStops.offer(floor);
        while(currentState!=floor){
          if(floor>currentState){
            movingup=true;
            currentState++;
           

          }
          else if(floor<currentState){
            movingup=false;
            currentState--;
           
          }
        }
          nextStops.poll();
    }catch(InputMismatchException e){
        System.out.println("Enter the valid input type which is number");
        sc.nextLine();
    }

        

    }
    public void upcomingStops(){
        System.out.println("Upcoming stops:");
        for(int i:nextStops){
            System.out.print(i+" ");
        }
        System.out.println();
    }


}

public class LiftSystem {
    
   
    public static void main(String[]args){
         Scanner sc=new Scanner(System.in);
         Lift lift=new Lift();
        
      while(true){
        try{ 
        System.out.println("Welcome!!!");
        System.out.println("1.CurrentStatus\n2.Give request\n3.Give Destination\n4.upcoming Stops\n5.Exit");
        int choice=sc.nextInt();
        switch(choice){
            case 1:
                lift.currentState();
                break;
            case 2:
                lift.giveRequest();
                break;
            case 3:
                lift.giveDestination();
                break;
            case 4:
                lift.upcomingStops();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice");                    

        }
    }
catch(InputMismatchException e){
    System.out.println("Enter the valid input type which is number");
    sc.nextLine();
}
catch(Exception e){
    System.out.println("UncaughtException:"+e.getMessage());
    sc.nextLine();
}

}
}


    
}
