package com.app.studio.service;

import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Product;
import java.util.List;

/**
 * Interface which is going to provide the product service operations
 *
 * @author malalanayake
 */
public interface ProductService {

    /**
     * Create New product
     *
     * @param product
     * @return
     * @throws RequiredDataNotPresent
     * @throws RecordAlreadyExistException
     */
    public Product createNewProduct(Product product) throws RequiredDataNotPresent,
            RecordAlreadyExistException;

    /**
     * Update product
     *
     * @return
     * @throws RequiredDataNotPresent
     */
    public Product updateProduct() throws RequiredDataNotPresent;

    /**
     * Delete product
     *
     * @return
     * @throws RequiredDataNotPresent
     */
    public Product deleteProduct() throws RequiredDataNotPresent;

    /**
     * List of all products
     *
     * @return
     */
    public List<Product> listOfAllProducts();
}
