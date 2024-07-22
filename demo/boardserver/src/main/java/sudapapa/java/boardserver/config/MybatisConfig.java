package sudapapa.java.boardserver.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "sudapapa.java.boardserver")
public class MybatisConfig {

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Bean(name = "mybatisDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public  DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mybatisDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        // application.yml 에서 설정이 완료됨
        //sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mapper/mybatis-config.xml"));

        return  sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return  new SqlSessionTemplate(sqlSessionFactory);
    }
}
