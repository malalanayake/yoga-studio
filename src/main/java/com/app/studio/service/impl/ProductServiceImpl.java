/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.studio.service.impl;

import com.app.studio.dao.ProductDAO;
import com.app.studio.exception.RecordAlreadyExistException;
import com.app.studio.exception.RequiredDataNotPresent;
import com.app.studio.model.Product;
import com.app.studio.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ahmadreza
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Override
    @Transactional
    public Product createNewProduct(Product product) throws RequiredDataNotPresent, RecordAlreadyExistException {
        Product pro = null;
        if (!product.getName().equals("") && !product.getType().equals("")
                && !product.getDescription().equals("")) {
            pro = productDAO.create(product);
        } else {
            throw new RequiredDataNotPresent("Required data not presenet to create product");
        }
        return pro;
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        Product pro = null;
        if (!product.getName().equals("") && !product.getType().equals("")
                && !product.getDescription().equals("")) {
           // if (product.getId() > 0) {
                pro=this.productDAO.update(product);
//            } else {
//                throw new RequiredDataNotPresent("Primery key not present");
//            }
        } else {
//            throw new RequiredDataNotPresent("Required data not presenet to create product");
        }
        return pro;
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        this.productDAO.remove(id);
    }

    @Override
    @Transactional
    public List<Product> listOfAllProducts() {
        return productDAO.list();
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public Product getProductByID(int id) {
        return this.productDAO.getById(id);
    }

}
