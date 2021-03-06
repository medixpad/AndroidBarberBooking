package com.example.androidbarberbooking.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CartDAO {

    @Query("SELECT SUM(productPrice*productQuantity) FROM Cart WHERE userEmail=:userEmail")
    Single<Double> sumPrice(String userEmail);

    @Query("SELECT * FROM Cart WHERE userEmail=:userEmail")
    Flowable<List<CartItem>> getAllItemsFromCart (String userEmail);

    @Query("SELECT COUNT(*) FROM Cart WHERE userEmail=:userEmail")
    Single<Integer> countItemsInCart (String userEmail);

    @Query("SELECT * FROM Cart WHERE productId=:productId AND userEmail=:userEmail")
    Flowable<CartItem> getProductInCart(String productId, String userEmail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(CartItem...carts);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Single<Integer> update(CartItem cart);

    @Delete
    Single<Integer> delete(CartItem cartItem);

    @Query("DELETE FROM Cart WHERE userEmail=:userEmail")
    Single<Integer> clearCart(String userEmail);

}
