package bobkubista.examples.services.rest.user.config;

//import java.sql.SQLException;
//import java.util.Properties;
//
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.ValidationMode;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Description;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import bobkubista.example.utils.property.ServerProperties;

// TODO use me instead of xml
//@Configuration
//@PropertySource("classpath:server.properties")
//@EnableTransactionManagement
//@ComponentScan(value = { "bobkubista.examples" })
public class DataConfig {

    // @Autowired
    // private DataSource dataSource;
    //
    // @Bean(name = "dataSource")
    // @Description("DataSource configuration for the tomcat jdbc connection
    // pool")
    // public DataSource dataSource() throws SQLException {
    // final com.mchange.v2.c3p0.ComboPooledDataSource source = new
    // com.mchange.v2.c3p0.ComboPooledDataSource();
    //
    // final Properties props = new Properties();
    // props.setProperty("driverClass",
    // ServerProperties.getString("database.driverClassName"));
    // props.setProperty("jdbcUrl", ServerProperties.getString("database.url"));
    // props.setProperty("user",
    // ServerProperties.getString("database.username"));
    // props.setProperty("password",
    // ServerProperties.getString("database.password"));
    // props.setProperty("minPoolSize",
    // ServerProperties.getString("database.minPoolSize"));
    // props.setProperty("maxPoolSize",
    // ServerProperties.getString("database.maxPoolSize"));
    // props.setProperty("idleConnectionTestPeriod",
    // ServerProperties.getString("database.idleConnectionTestPeriod"));
    //
    // source.setProperties(props);
    // return source;
    // }
    //
    // @Bean
    // public EntityManagerFactory entityManagerFactory() {
    // final LocalContainerEntityManagerFactoryBean em = new
    // LocalContainerEntityManagerFactoryBean();
    // em.setDataSource(this.dataSource);
    // em.setPersistenceUnitName("jpaData");
    // em.setPackagesToScan("bobkubista.examples.services.rest");
    // em.setJpaVendorAdapter(this.jpaVendorAdaper());
    // em.setValidationMode(ValidationMode.AUTO);
    //
    // final Properties jpaProperties = new Properties();
    // jpaProperties.put("javax.persistence.database-product-name",
    // "PostgreSQL");
    // jpaProperties.put("hibernate.dialect",
    // ServerProperties.getString("database.dialect"));
    // jpaProperties.put("hibernate.show_sql",
    // ServerProperties.getString("database.showSql"));
    // jpaProperties.put("hibernate.format_sql",
    // ServerProperties.getString("database.formatSql"));
    // jpaProperties.put("hibernate.hbm2ddl.auto",
    // ServerProperties.getString("database.ddlAuto"));
    // jpaProperties.put("hibernate.default_schema",
    // ServerProperties.getString("database.defaultSchema"));
    // jpaProperties.put("hibernatey.cache.provider_class",
    // "org.hibernate.cache.EhCacheProvider");
    // jpaProperties.put("hibernate.cache.use_query_cache", false);
    // jpaProperties.put("hibernate.cache.use_structured_entries", false);
    // jpaProperties.put("hibernate.cache.use_second_level_cache", false);
    // jpaProperties.put("hibernate.cache.region.factory_class",
    // "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
    // jpaProperties.put("hibernate.jdbc.batch_size", 50);
    // jpaProperties.put("hibernate.order_inserts", true);
    // jpaProperties.put("hibernate.order_updates", true);
    //
    // em.setJpaProperties(jpaProperties);
    //
    // em.afterPropertiesSet();
    // return em.getObject();
    // }
    //
    // @Bean(name = "transactionManager")
    // public JpaTransactionManager jpaTransactionManager() {
    // final JpaTransactionManager jpaTransactionManager = new
    // JpaTransactionManager();
    // jpaTransactionManager.setEntityManagerFactory(this.entityManagerFactory());
    // return jpaTransactionManager;
    // }
    //
    // @Bean
    // public JpaVendorAdapter jpaVendorAdaper() {
    // final HibernateJpaVendorAdapter vendorAdapter = new
    // HibernateJpaVendorAdapter();
    // vendorAdapter.setDatabase(Database.POSTGRESQL);
    // return vendorAdapter;
    // }
    //
    // @Bean
    // public PersistenceExceptionTranslationPostProcessor
    // persistenceExceptionTranslationPostProcessor() {
    // return new PersistenceExceptionTranslationPostProcessor();
    // }

}
