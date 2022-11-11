import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PatikaStore implements IStore{
    private static List<Brand> brands = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static Scanner cevap = new Scanner(System.in);
    static {
        brands.add(new Brand(1L,"Samsung"));
        brands.add(new Brand(2L,"Lenovo"));
        brands.add(new Brand(3L,"Apple"));
        brands.add(new Brand(4L,"Huawei"));
        brands.add(new Brand(5L,"Casper"));
        brands.add(new Brand(6L,"Asus"));
        brands.add(new Brand(7L,"HP"));
        brands.add(new Brand(8L,"Xiaomi"));
        brands.add(new Brand(9L,"Monster"));
        products.add(new Phone(1L,new BigDecimal(13999),8,20,"Galaxy Note 9",brands.get(0),6,6.1,256,5000,Color.Black));
        products.add(new Phone(2L,new BigDecimal(42000),5,5,"Iphone 14",brands.get(2),8,7.2,512,4000,Color.Blue));
        products.add(new Phone(3L,new BigDecimal(8799),0,15,"A1",brands.get(7),4,5.3,64,3500,Color.Black));
        products.add(new Phone(4L,new BigDecimal(21999),7,20,"P50 Pro",brands.get(5),6,5.7,128,4000,Color.Red));
        products.add(new Notebook(5L,new BigDecimal(27999),3,50,"Thinkpad E14",brands.get(1),16,17.1,512));
        products.add(new Notebook(6L,new BigDecimal(21999),3,50,"Thinkpad E13",brands.get(1),16,15.2,256));
    }

    public Product productById(Long id){
        return products.stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElse(null);
    }

    public List<Product> productByBrandId(Long brandId){
        return products.stream()
                .filter(product -> product.getBrand().getId() == brandId)
                .collect(Collectors.toList());
    }

    public List<Product> filterProducts(String type){
        List filtered = null;
        try {
            Class t = Class.forName(type);
            filtered = products.stream()
                    .filter(product -> product.getClass().equals(t))
                    .collect(Collectors.toList());
        }catch (ClassNotFoundException e){
            System.out.println("Category not found!");
        }
        return filtered;
    }

    @Override
    public void getProducts(String type) {
        List<Product> filteredProducts = filterProducts(type);
        if(filteredProducts != null) {
            System.out.println("| Brand |      Name     |   Price   |Stock|\n===========================================");
            for (Product product : filteredProducts) {
                System.out.format("|%7s|%15s|%11.2f|%5d|\n", product.getBrand().getName(), product.getName(), product.getPrice(), product.getStock());
            }
        }else{
            System.out.println("Category type is empty.");
        }
    }

    @Override
    public void addProduct(String type){
        System.out.println("Enter your unique number:");
        Long id = Long.valueOf(cevap.nextLine());
        System.out.println("Enter your product name:");
        String name = cevap.nextLine();
        System.out.println("Enter your product price:");
        BigDecimal price = cevap.nextBigDecimal();
        System.out.println("Enter your product stock:");
        int stock = cevap.nextInt();
        System.out.println("Enter your product discount percent:");
        double discount = cevap.nextDouble();
        System.out.println("Enter your product RAM:");
        int ram = cevap.nextInt();
        System.out.println("Enter your product screen size:");
        double screen = cevap.nextDouble();
        System.out.println("Enter your product brand name:");
        String brandName = cevap.next();
        Brand brand = brands.stream()
                .filter(brand1 -> brand1.getName().equals(brandName))
                .findAny()
                .orElse(null);
        switch (type){
            case "Phone":
                System.out.println("Enter your product memory:");
                int memory = cevap.nextInt();
                System.out.println("Enter your product battery:");
                int battery = cevap.nextInt();
                System.out.println("Enter your product color(0-Black,1-Red,2-Blue):");
                int color = cevap.nextInt();
                products.add(new Phone(id,price,discount,stock,name,brand,ram,screen,memory,battery,Color.values()[color]));
                System.out.println("Phone successfully created.");
                break;
            case "Notebook":
                System.out.println("Enter your product storage:");
                int storage = cevap.nextInt();
                products.add(new Notebook(id,price,discount,stock,name,brand,ram,screen,storage));
                System.out.println("Notebook successfully created.");
                break;
            default:
                System.out.println("Product type not valid!");
                break;
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productById(id);
        if(product != null) {
            products.remove(productById(id));
            System.out.println("Product successfully deleted.");
        }else{
            System.out.println("Product not found.");
        }
    }

    @Override
    public void getProductById(Long id) {
        Product product = productById(id);
        if(product != null) {
            System.out.println("| Brand |      Name     |   Price   |Stock|\n===========================================");
            System.out.format("|%7s|%15s|%11.2f|%5d|\n", product.getBrand().getName(), product.getName(), product.getPrice(), product.getStock());
        }else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void getProductByBrandId(Long brandId) {
        List<Product> productList = productByBrandId(brandId);
        if(!productList.isEmpty()) {
            System.out.println("| Brand |      Name     |   Price   |Stock|\n===========================================");
            for (Product product : productList) {
                System.out.format("|%7s|%15s|%11.2f|%5d|\n", product.getBrand().getName(), product.getName(), product.getPrice(), product.getStock());
            }
        }else{
            System.out.println("Brand not found or out of stock.");
        }
    }
}
