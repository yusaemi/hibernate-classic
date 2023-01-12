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
public final class HibernateConfig {

    private HibernateConfig() {}

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // 從hibernate.cfg.xml建立SessionFactory
        return new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        // 關閉cache和連線池
        getSessionFactory().close();
    }

}
