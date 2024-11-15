package com.shubhada.productservice;

//@SpringBootTest
public class ProductTests {
   /* @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    void savingProductAndCategory(){*/
      /*  Category category=new Category();
        category.setName("phones");
        Category savedCategory=categoryRepository.save(category);

        Product product=new Product();
        product.setPrice(100);
        product.setTitle("iPhone17");
        product.setDescription("iPhone17 launching soon");
        product.setImageUrl("hello");
        product.setCategory(category);
        productRepository.save(product);*/

        /*Category category=new Category();
        category.setName("electronics");
        //Category savedCategory=categoryRepository.save(category);

        Product product=new Product();
        product.setPrice(120);
        product.setTitle("fridge");
        product.setDescription("fridge");
        product.setImageUrl("fridge");
        product.setCategory(category);
        productRepository.save(product);


    }*/
   /* @Test
    @Transactional
    void fetchTypesTests(){
     Product product=productRepository.findProductById(1L);
        System.out.println("fetched product");
        Category category=product.getCategory();
        String name=category.getName();
    }
    @Test
    void deleteProduct(){
     productRepository.deleteById(1L);
    }


    @Test
    @Transactional
    //@Rollback(value = false)
    @Commit
    void saveProductsCategory(){
        //rolled back changes
        //get() for Optional
     Category category=categoryRepository.findById(2L).get();

        Product product=new Product();
        product.setPrice(102);
        product.setTitle("TV");
        product.setDescription("Refridgerator");
        product.setImageUrl("fridge");
        product.setCategory(category);
        productRepository.save(product);

        product=new Product();
        product.setPrice(103);
        product.setTitle("TV");
        product.setDescription("LED TV");
        product.setImageUrl("ledtv");
        product.setCategory(category);
        productRepository.save(product);

        Category category1=new Category();
        category1.setName("iPhone");
        categoryRepository.save(category1);
    }
    @Test
    @Transactional
    void getProductsForCategory() throws InterruptedException {
   // Category category=categoryRepository.findById(2L).get();
        List<Category> categories=categoryRepository.findAllByIdIn(List.of(2L,52L));
        //select * from category where id In(2,52)
        //select * from products where category_id=2;
        //select * from products where category_id=52;
        Thread.sleep(100L);
        System.out.println("Doing something");
        Thread.sleep(100L);
    for(Category category:categories){
        for(Product product:category.getProducts()){
            System.out.println(product.getPrice());
        }
    }

    }

    @Test
    @Transactional
    void getProductsForOneCategory() throws InterruptedException {
        // Category category=categoryRepository.findById(2L).get();
        Category category=categoryRepository.findById(2L).get();
        Thread.sleep(100L);
        System.out.println("Doing something");
        Thread.sleep(100L);

            for(Product product:category.getProducts()){
                System.out.println(product.getPrice());
            }


    }

    @Test
    public void demonstrateCustomQueries(){
        //List<ProductDBDto> productDBDtos = productRepository.laaoproductsWithId(52L);
       // List<Product> products=productRepository.getByIdAndTitle(2L,"iphone");

    }*/
}
