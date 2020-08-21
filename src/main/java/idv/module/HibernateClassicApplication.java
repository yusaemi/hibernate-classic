package idv.module;

import idv.module.config.HibernateConfig;
import idv.module.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * * Application. 2020/3/22 6:07 下午
 * *
 * * @author sero
 * * @version 1.0.0
 *
 **/
public class HibernateClassicApplication {

    public static void main(String[] args) throws InterruptedException {

        // 建立sessionFactory
        Session session = HibernateConfig.getSessionFactory().openSession();
        // 開啟一個事物
        Transaction transaction = session.beginTransaction();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        /* get all Product */
        System.err.println("==============get all Product==============");
        Query selectAllQuery = session.createQuery("from Product");
        List<Product> products = selectAllQuery.list();
        for (Product p : products) {
            System.out.printf("(SelectProduct) Id: %d, EnName: %s, ZhName: %s, Price: %f, ReleaseDate: %s\n", p.getId(), p.getEnName(), p.getZhName(), p.getPrice(), sdf.format(p.getReleaseDate()));
        }
        Thread.sleep(50);

        /* select random Product */
        int productId = ThreadLocalRandom.current().nextInt(1, products.size() + 1);
        System.err.printf("==============select %d Product==============\n", productId);
        Product selectProduct = session.get(Product.class, productId);
        System.out.printf("(SelectProduct) Id: %d, EnName: %s, ZhName: %s, Price: %f, ReleaseDate: %s\n", selectProduct.getId(), selectProduct.getEnName(), selectProduct.getZhName(), selectProduct.getPrice(), sdf.format(selectProduct.getReleaseDate()));
        Thread.sleep(50);

        /* insert */
        System.err.println("==============insert Test Product==============");
        Product insertProduct = new Product();
        insertProduct.setEnName("Test Product");
        insertProduct.setZhName("測試商品");
        insertProduct.setPrice(9999.55);
        insertProduct.setReleaseDate(Timestamp.from(Instant.now()));
        session.save(insertProduct);
        Query selectQuery = session.createQuery("from Product where enName = ?1");
        selectQuery.setParameter(1, "Test Product");
        insertProduct = (Product) selectQuery.list().get(0);
        System.out.printf("(InsertProduct) Id: %d, EnName: %s, ZhName: %s, Price: %f, ReleaseDate: %s\n", insertProduct.getId(), insertProduct.getEnName(), insertProduct.getZhName(), insertProduct.getPrice(), sdf.format(insertProduct.getReleaseDate()));
        Thread.sleep(50);

        /* update */
        System.err.println("==============update Test Product==============");
        System.out.printf("(Before insertProduct) ZhName: %s, Price: %f, EditDate: %s\n", insertProduct.getZhName(), insertProduct.getPrice(), (insertProduct.getEditDate() == null ? "" : sdf.format(insertProduct.getEditDate())));
        insertProduct.setPrice(4990.72);
        insertProduct.setZhName("測試商品更新");
        insertProduct.setEditDate(Timestamp.valueOf(LocalDateTime.now()));
        session.update(insertProduct);
        Product updateProduct = session.get(Product.class, insertProduct.getId());
        System.out.printf("(After insertProduct) ZhName: %s, Price: %f, EditDate: %s\n", updateProduct.getZhName(), updateProduct.getPrice(), (updateProduct.getEditDate() == null ? "" : sdf.format(insertProduct.getEditDate())));
        Thread.sleep(50);

        /*
         * delete
         */
        System.err.println("==============delete Test Product==============");
        Query deleteQuery = session.createQuery("from Product where id = ?1");
        deleteQuery.setParameter(1, insertProduct.getId());
        List<Product> deleteProducts = (List<Product>) deleteQuery.list();
        for (Product p : deleteProducts) {
            System.out.printf("(DeleteProduct) Id: %d, EnName: %s, ZhName: %s, Price: %f, ReleaseDate: %s\n", p.getId(), p.getEnName(), p.getZhName(), p.getPrice(), sdf.format(p.getReleaseDate()));
            session.delete(p);
        }
        Thread.sleep(50);

        System.err.println("==============commit and close session==============");

        // 最後不執行commit則會rollback
        transaction.commit();

        // commit不執行就關閉會發生connection leak detected
        HibernateConfig.shutdown();

    }

}
