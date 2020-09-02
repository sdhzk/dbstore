package io.starskyoio.dbstore.server.ds;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private Map<String, DataSourceConfigBean> configMap = new HashMap<>();
    private Map<String, DataSource> targetDataSources = new HashMap<>();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        log.info("注册动态数据源");
        if (configMap.isEmpty()) {
            log.error("动态数据源配置错误");
            return;
        }

        DynamicDataSourceContextHolder.addDataSourceKeys(configMap.keySet());

        // 初始化DataSource
        initTargetDataSources();

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        beanDefinition.getPropertyValues().addPropertyValue("targetDataSources", targetDataSources);
        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);

        log.info("注册动态数据源成功");
    }

    /**
     * Init datasources by config
     */
    private void initTargetDataSources() {
        for (Map.Entry<String, DataSourceConfigBean> entry : configMap.entrySet()) {
            DataSourceConfigBean configBean = entry.getValue();
            DataSource ds = DataSourceBuilder.create()
                    .driverClassName(configBean.getDriverClassName())
                    .url(configBean.getUrl())
                    .username(configBean.getUsername())
                    .password(configBean.getPassword())
                    .type(HikariDataSource.class)
                    .build();
            targetDataSources.put(entry.getKey(), ds);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        // load spring.datasource.* config
        configMap = Binder.get(environment).bind("spring.datasource",
                Bindable.mapOf(String.class, DataSourceConfigBean.class)).get();
    }
}
