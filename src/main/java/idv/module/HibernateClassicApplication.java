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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * * Application. 2020/3/22 6:07 下午
 * *
 * * @author sero
 * * @version 1.0.0
 *
 **/
public class HibernateClassicApplication {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public static void main(String[] args) throws InterruptedException {

        // 建立sessionFactory
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // 開啟一個事物
            Transaction transaction = session.beginTransaction();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            /* get all Product */
            LOGGER.info("==============get all Product==============");
            Query<Product> selectAllQuery = session.createNativeQuery("select * from product", Product.class);
            List<Product> products = selectAllQuery.list();
            for (Product p : products) {
                LOGGER.log(Level.INFO, "(SelectProduct) Id: {0}, EnName: {1}, ZhName: {2}, Price: {3}, ReleaseDate: {4}", new Object[]{p.getId(), p.getEnName(), p.getZhName(), p.getPrice(), sdf.format(p.getReleaseDate())});
            }
            Thread.sleep(50);

            /* select random Product */
            int productId = ThreadLocalRandom.current().nextInt(1, products.size() + 1);
            LOGGER.log(Level.INFO, "==============select {0} Product==============", productId);
            Product selectProduct = session.get(Product.class, productId);
            LOGGER.log(Level.INFO, "(SelectProduct) Id: {0}, EnName: {1}, ZhName: {2}, Price: {3}, ReleaseDate: {4}", new Object[]{selectProduct.getId(), selectProduct.getEnName(), selectProduct.getZhName(), selectProduct.getPrice(), sdf.format(selectProduct.getReleaseDate())});
            Thread.sleep(50);

            /* insert */
            LOGGER.info("==============insert Test Product==============");
            Product insertProduct = new Product();
            insertProduct.setEnName("Test Product");
            insertProduct.setZhName("測試商品");
            insertProduct.setPrice(9999.55);
            insertProduct.setReleaseDate(Timestamp.from(Instant.now()));
            session.persist(insertProduct);
            Query<Product> selectQuery = session.createNativeQuery("select * from product where en_name = ?1", Product.class);
            selectQuery.setParameter(1, "Test Product");
            insertProduct = selectQuery.list().get(0);
            LOGGER.log(Level.INFO, "(InsertProduct) Id: {0}, EnName: {1}, ZhName: {2}, Price: {3}, ReleaseDate: {4}", new Object[]{insertProduct.getId(), insertProduct.getEnName(), insertProduct.getZhName(), insertProduct.getPrice(), sdf.format(insertProduct.getReleaseDate())});
            Thread.sleep(50);

            /* update */
            LOGGER.info("==============update Test Product==============");
            LOGGER.log(Level.INFO, "(Before insertProduct) ZhName: {0}, Price: {1}, EditDate: {2}", new Object[]{insertProduct.getZhName(), insertProduct.getPrice(), insertProduct.getEditDate() == null ? "" : sdf.format(insertProduct.getEditDate())});
            insertProduct.setPrice(4990.72);
            insertProduct.setZhName("測試商品更新");
            insertProduct.setEditDate(Timestamp.valueOf(LocalDateTime.now()));
            session.merge(insertProduct);
            Product updateProduct = session.get(Product.class, insertProduct.getId());
            LOGGER.log(Level.INFO, "(After insertProduct) ZhName: {0}, Price: {1}, EditDate: {2}", new Object[]{updateProduct.getZhName(), updateProduct.getPrice(), updateProduct.getEditDate() == null ? "" : sdf.format(insertProduct.getEditDate())});
            Thread.sleep(50);

            /*
             * delete
             */
            LOGGER.info("==============delete Test Product==============");
            Query<Product> deleteQuery = session.createNativeQuery("select * from product where id = ?1", Product.class);
            deleteQuery.setParameter(1, insertProduct.getId());
            List<Product> deleteProducts = deleteQuery.list();
            for (Product p : deleteProducts) {
                LOGGER.log(Level.INFO, "(DeleteProduct) Id: {0}, EnName: {1}, ZhName: {2}, Price: {3}, ReleaseDate: {4}", new Object[]{p.getId(), p.getEnName(), p.getZhName(), p.getPrice(), sdf.format(p.getReleaseDate())});
                session.remove(p);
            }
            Thread.sleep(50);

            LOGGER.info("==============commit and close session==============");

            // 最後不執行commit則會rollback
            transaction.commit();

            // commit不執行就關閉會發生connection leak detected
            HibernateConfig.shutdown();
        }

    }

}
