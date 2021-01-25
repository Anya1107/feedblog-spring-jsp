package by.feedblog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@EnableWebMvc
@ComponentScan(basePackages = "by.feedblog")
public class WebConfiguration {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("root");
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/pages/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
}
