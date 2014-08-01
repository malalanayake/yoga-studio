package com.app.studio.dao;

import com.app.studio.model.ShoppingCart;
import java.util.List;

/**
 * Interface which is going to provide the ShoppingCart
 *
 * @author malalanayake
 */
public interface ShoppingCartDAO {

    /**
     * Create a new ShoppingCart
     *
     * @param shoppingCart
     * @return
     */
    public ShoppingCart create(ShoppingCart shoppingCart);

    /**
     * Update existing ShoppingCart
     *
     * @param shoppingCart
     * @return
     */
    public ShoppingCart update(ShoppingCart shoppingCart);

    /**
     * List all ShoppingCart
     *
     * @return
     */
    public List<ShoppingCart> list();

    /**
     * Get ShoppingCart by ID
     *
     * @param id
     * @return
     */
    public ShoppingCart getById(int id);

    /**
     * Remove ShoppingCart
     *
     * @param id
     * @return
     */
    public ShoppingCart remove(int id);
}
