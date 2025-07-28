package ecommerceSystem;

import java.util.*;

class Product {
    int product_id;
    String product_name;
    double cost;
    int quantity;
    public Product (int product_id,String product_name,double cost,int quantity){
        this.product_id=product_id;
        this.product_name=product_name;
        this.cost=cost;
        this.quantity=quantity;
    }
    public String toString(){
        return "ID:"+product_id+" Name:"+product_name+"Cost:"+cost+"quantity:"+quantity;
    }
 }
 class Member{
    int member_id;
    String member_name;
    double amount;
    public Member(int member_id,String member_name,double amount){
        this.member_id=member_id;
        this.member_name=member_name;
        this.amount=amount;
    }
 }
 class Cart{
    Product product;
    int quantity;
    public Cart(Product product,int quantity){
        this.product=product;
        this.quantity=quantity;
    }
    public String toString(){
        return product.toString()+"cart_Quantity:"+quantity;
    }
 }

public class EcommerceSystem {
    static Scanner sc=new Scanner(System.in);
    static Map<Integer,Product>products=new HashMap<>();
    static Map<Integer,Member>members=new HashMap<>();
    static List<Cart>cart=new ArrayList<>();
    static Queue<Integer>productQueue=new LinkedList<>();
    static int memberId=0;
    static int productId=0;

    public static void main(String[]args){
        while(true){
            System.out.println("Welcome to Shopify");
            System.out.println("Enter user/admin");
            String role=sc.next();
            if(role.equalsIgnoreCase("admin")){
                System.out.println("Enter the username:");
                String username=sc.next();
                System.out.println("Enter the password");
                String password=sc.next();
                if(username.equalsIgnoreCase("admin")&&password.equalsIgnoreCase(password)){
                    System.out.println("WELCOME ADMIN");
                    adminMenu();
                }
                else{
                    System.out.println("Invalid credentials");
                }
            }
            else if(role.equalsIgnoreCase("user")){
                System.out.println("Enter existing or newuser:");
                String type=sc.next();
                System.out.println("Enter the username:");
                String username=sc.next();
                memberId++;
                if(type.equalsIgnoreCase("newuser")){
                    System.out.println("Enter the amount:");
                    int amount=sc.nextInt();
                    members.put(memberId,new Member( memberId,username,amount));
                }
                userMenu(memberId);
                
            }
            else{
                System.out.println("Invalid input");
            }

            
        }
    }

    public static void adminMenu(){
        while(true){
        System.out.println("Admin Menu");
        System.out.println("1.AddProduct\n2.RemoveProduct\n3.Remove Early Products\n4.Remove Less Quantity Product\n5.view Product\n6.ViewQueue\n7.logout");
        System.out.println("Enter your choice");
        int choice =sc.nextInt();
        switch(choice){
            case 1:
                addProduct();
                break;
            case 2:
                removeProduct();
                break;
            case 3:
                removeEarlyProduct();
                break;
            case 4:
                removeLessQuantityProduct();
                break;
            case 5:
                viewProducts();
                break;
            case 6:
                viewQueue();  
                break;
            case 7:
                return;      
            default:
                System.out.println("Invalid choice");         
        }


        }

    }

    public static void addProduct(){
        System.out.println("Enter the product_name:");
        String product_name=sc.next();
        System.out.println("Enter the quantity");
        int quantity=sc.nextInt();
        System.out.println("Enter the cost");
        double cost=sc.nextDouble();
        productId++;
        Product p=new Product(productId, product_name, cost, quantity);
        products.put(productId,p);
        productQueue.offer(productId);
    }

    public static void removeProduct(){
        System.out.println("enter the product_id:");
        int productId=sc.nextInt();
        products.remove(productId);
        productQueue.remove(productId);

    }

    public static void removeEarlyProduct(){
        System.out.println("Enter the number of products to remove:");
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            productQueue.poll();
        }
    }
    public static void viewQueue(){
        System.out.println("Queue");
       for(int id:productQueue){
        Product p=products.get(id);
        if(p!=null){
            System.out.println(p);

        }
        else{
            System.out.println("Empty Queue");
        }
       }
    }

    public static  void removeLessQuantityProduct(){
        System.out.println("Enter the quantity by which you have to remove the products:");
        int n=sc.nextInt();
        List<Integer> toRemove = new ArrayList<>();
        for(Map.Entry<Integer,Product>entry:products.entrySet()){
            if(entry.getValue().quantity<n){
                toRemove.add(entry.getKey());

            }
            

        }
        for(Integer key:toRemove){
            products.remove(key);

        }
    }
    public static void userMenu(int id){
        while(true){
            System.out.println("UserMenu");
            System.out.println("1.Search product\n2.view products\n3.Add to cart\n4.Remove from cart\n5.checkout\n6.viewcart\n7.logout");
            System.out.println("Enter your choice:");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    searchProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    addToCart();
                    break;
                case 4:
                    removeFromCart();
                    break;
                case 5:
                    checkOut(id);
                    break;
                case 6:
                    viewCart();
                    break;
                case 7:
                    return;    
                default:
                    System.out.println("Invalid input");                        
            }
        }

    }

    public static void searchProduct(){
        System.out.println("Enter the product id:");
        int id=sc.nextInt();
        for(Product p:products.values()){
            if(p.product_id==id){
                System.out.println(p.product_id+"-"+p.product_name+"|"+p.cost+"Q"+p.quantity);
            }

        }
    }

    
    public static void viewProducts(){
        System.out.println("Product List");
        for(Product p:products.values()){
             System.out.println(p.product_id+"-"+p.product_name+"|"+p.cost+"Q"+p.quantity);
        }
    }
    public static void addToCart(){
        viewProducts();
        System.out.println("Enter the product_id:");
        int id=sc.nextInt();
         System.out.println("Enter the quantity:");
        int quantity=sc.nextInt();
        Product prod=null;
        for(Product p:products.values()){
            if(p.product_id==id){
                prod=p;
                if(p.quantity>=quantity){
                cart.add(new Cart(prod, quantity));
                }
                else{
                    System.out.println("Insufficient quantity");
                }
            }
        }
        if(prod==null){
            System.out.println("There is no such product");
        }
       
     }
     public static void removeFromCart(){
        System.out.println("Enter the Product id:");
        int id=sc.nextInt();
        for(Cart c:cart){
            if(c.product.product_id==id){
                cart.remove(c);
                return;
            }
        }
        System.out.println("ID doesn't exists");

     }
     public static void viewCart(){
        System.out.println("Cart Item");
        for(Cart c:cart){
            System.out.println(c);
        }
        System.out.println("");

     }

     public static void checkOut(int id){
        System.out.println("CheckOut Details");
        int total=0;
        for(Cart c:cart){
            total+=c.product.cost*c.quantity;
        }
        Member m=members.get(id);
        if(m.amount<total){
            System.out.println("Insufficient Balance");
            System.err.println("Enter the amount:");
            int amount=sc.nextInt();
            m.amount+=amount;
        }
        else{
            m.amount-=total;
            System.out.println("CheckOut successfull");
        }
        
     }




    
}
