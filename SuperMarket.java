package SuperMarket;
import java.util.*;

public class SuperMarket {
 static class Product{
    int id;
    String name;
    double price;
    int stock;
    public Product(int id,String name,double price,int stock ){
        this.id=id;
        this.name=name;
        this.price=price;
        this.stock=stock;
    }
    public String toString(){
        return id+"."+name+"- Rs."+price+"|"+stock;
    }
}
static class CartItem{
    Product product;
    int quantity;

    public CartItem(Product product,int quantity){
        this.product=product;
        this.quantity=quantity;
    }
    public String toString(){
        return product.name+"-"+quantity+"="+product.price*quantity;
    }
}

   static Scanner sc=new Scanner(System.in);
    static Map<Integer,Product> products=new HashMap<>();
    static List<CartItem>cart=new ArrayList<>();
    static int productIdCounter=1;

    public static void main(String[]args){
        System.out.println("Welcome to mart");
        while(true){
        System.out.println("1.Customer");
        System.out.println("2.Administrator");
        System.out.println("3.Exit");
        System.out.println("Enter your choice");
        int choice =sc.nextInt();
            switch(choice){
                case 1:
                    customerMenu();
                    break;
                case 2:
                    adminLogin();
                    break;
                case 3:
                    System.out.println("Thank you !Visit again");
                    break;
                default:
                    System.out.println("Invalid choice");        
                
            }
         }

    }
     public static void customerMenu(){
        while(true){
                System.out.println("Customer Menu");
                System.out.println("1.View product");
                System.out.println("2.Add to cart");
                System.out.println("3.view cart");
                System.out.println("4.check out");
                System.out.println("5.Back");
                System.out.println("Enter your choice:");
                int choice=sc.nextInt();
               
                switch(choice){
                    case 1:
                      displayProducts();
                      break;
                    case 2:
                      addToCart();
                      break;
                    case 3:
                      viewCart();
                      break;
                    case 4:
                       checkOut();
                       break;
                    case 5:
                       return;
                    default:
                      System.out.println("Invalid choice")  ; 
                }
          }
        }

        static void adminLogin(){
            System.out.println("enter the username:");
            String username=sc.next();
            System.out.println("enter the password:");
            String password=sc.next();

            if(username.equals("admin")&&password.equals("1234")){
                System.out.println("Welcome Admin");
                adminMenu();
            }
            else{
                System.out.println("Invalid credentials");
            }
            
        }

        static void adminMenu(){
            while(true){
                System.out.println("1.add product");
                System.out.println("2.View products");
                System.out.println("3.Back");
                System.out.println("enter the choice:");
                int choice =sc.nextInt();
                sc.nextLine();
                switch(choice){
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        displayProducts();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("invalid choice");          
                }


            }
        }

        static void addProduct(){
            
            System.out.println("Enter the product name:");
            String name=sc.next();
            sc.nextLine();
            System.out.println("Enter the product price:");
            double price=sc.nextDouble();
            sc.nextLine();
            System.out.println("Enter the product quantity:");
            int stock=sc.nextInt();
            sc.nextLine();
            Product p=new Product(productIdCounter++, name, price, stock);
            products.put(p.id,p);
            System.out.println("product added successfully");

        }

        static void displayProducts(){
            System.out.println("Product list");
            if(products.isEmpty()){
                System.out.println("list is empty");
            }
            for(Product p:products.values()){
                System.out.println(p);

            }
        }

        static void addToCart(){
            displayProducts();
            System.out.println("Enter the product id:");
            int id=sc.nextInt();
            Product p=products.get(id);
            if(p==null){
                System.out.println("product not found");
            }
            System.out.println("Enter the quantity:");
            int quantity=sc.nextInt();
            if(quantity>p.stock){
                System.out.println("product shortage");
            }
            cart.add(new CartItem(p, quantity));
            p.stock-=quantity;
            System.out.println("Added to cart");

        }

        public static  void viewCart(){
            System.out.println("Your cart");
            if(cart.isEmpty()){
                System.out.println("Empty cart");
                return;
            }
            double total=0;
            for(CartItem item:cart){
                System.out.println(item);
                total+=item.product.price*item.quantity;
            }
            System.out.println("total="+total);
        }
        public static void checkOut(){
            if(cart.isEmpty()){
                System.out.println("Your cart is empty");
                return;
            }
            viewCart();
            System.out.println("Thank you for visiting");
            cart.clear();

        }

    
}
