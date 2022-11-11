public interface IStore {
    void getProducts(String type);

    void addProduct(String type);

    void deleteProduct(Long id);

    void getProductById(Long id);

    void getProductByBrandId(Long brandId);
}
