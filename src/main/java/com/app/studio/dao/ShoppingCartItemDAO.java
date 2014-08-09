package com.app.studio.dao;

import com.app.studio.model.ShoppingCartItem;
import java.util.List;

/**
 *
 * @author Yen
 */
public interface ShoppingCartItemDAO {

    /**
     *
     * @param s
     * @return
     */
    ShoppingCartItem create(ShoppingCartItem s);

    /**
     *
     * @param s
     * @return
     */
    ShoppingCartItem update(ShoppingCartItem s);

    /**
     *
     * @return
     */
    List<ShoppingCartItem> list();

    /**
     *
     * @param id
     * @return
     */
    ShoppingCartItem getById(int id);

    /**
     *
     * @param id
     * @return
     */
    ShoppingCartItem remove(int id);
}
