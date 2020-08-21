package idv.module.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * * HibernateConfig. 2020/3/22 6:03 下午
 * *
 * * @author sero
 * * @version 1.0.0
 *
 **/
// Hibernate 建立資料庫連線
public class HibernateConfig {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // 從hibernate.cfg.xml建立SessionFactory
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // 獲取異常紀錄
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // 關閉cache和連線池
        getSessionFactory().close();
    }

}
