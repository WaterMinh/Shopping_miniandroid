package com.example.bkshopping;

import java.text.ParseException;
import java.util.List;

public interface IProductDAO {
    public List<Product> selectAll() throws ParseException;
    public boolean insert(Product product);
    public  boolean update (Product product);
    public  boolean delete (int id);
    public  Product selectById(int id) throws ParseException;
}
