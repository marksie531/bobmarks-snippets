package org.bobmarks.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductTest {

    public static void main(String[] args) {
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = (Product) session.load(Product.class, new Long(1));
        System.out.println(product.getName());

        Product newProduct = new Product("Pen", "Nice little Bic pen", 0.50);
        session.save(newProduct);
       
        session.delete(newProduct);

        tx.commit();
        session.close();
    }
}