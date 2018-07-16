package com.yly.testboot2.config;

import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("ALL")
@Configuration
@EnableScheduling
public class QuartzConfiguration
{

    /**
     * 继承org.springframework.scheduling.quartz.SpringBeanJobFactory
     * 实现任务实例化方式
     */
    public static class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements
            ApplicationContextAware {

        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext context) {
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        /**
         * 将job实例交给spring ioc托管
         * 我们在job实例实现类内可以直接使用spring注入的调用被spring ioc管理的实例
         * @param bundle
         * @return
         * @throws Exception
         */
        @Override
        protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            /**
             * 将job实例交付给spring ioc
             */
            beanFactory.autowireBean(job);
            return job;
        }
    }

    /**
     * 配置任务工厂实例
     * @param applicationContext spring上下文实例
     * @return
     */
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext)
    {
        /**
         * 采用自定义任务工厂 整合spring实例来完成构建任务
         * see {@link AutowiringSpringBeanJobFactory}
         */
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    /**
     * 由于在quartz.properties 中直接配置数据源的方式会报错，
     * 所以 直接在application.properties中配置了一个数据源，此处生成一个quartz专用的datasource
     * @return
     */
    @Bean(name = "quartztest1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.quartztest1")
//    @Primary
    public DataSource quartztest1DataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置任务调度器
     * 使用项目数据源作为quartz数据源
     * @param jobFactory 自定义配置任务工厂
     * @param dataSource 数据源实例
     * @return
     * @throws Exception
     */
    @Bean(destroyMethod = "destroy",autowire = Autowire.NO)
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, DataSource dataSource) throws Exception
    {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        //将spring管理job自定义工厂交由调度器维护
//        schedulerFactoryBean.setJobFactory(jobFactory);
//        //设置覆盖已存在的任务
//        schedulerFactoryBean.setOverwriteExistingJobs(true);
//        //项目启动完成后，等待2秒后开始执行调度器初始化
//        schedulerFactoryBean.setStartupDelay(2);
//        //设置调度器自动运行
//        schedulerFactoryBean.setAutoStartup(true);
//        //设置数据源，使用与项目统一数据源
//        schedulerFactoryBean.setDataSource(dataSource);
//        //设置上下文spring bean name
//        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
//        //设置配置文件位置
//        schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
//        schedulerFactoryBean.setDataSource(quartztest1DataSource());
//        return schedulerFactoryBean;
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setQuartzProperties(properties());
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactoryBean.setDataSource(quartztest1DataSource());
        return schedulerFactoryBean;

    }
    @Bean
    public Properties properties() throws IOException {
        Properties prop = new Properties();
        prop.load(new ClassPathResource("/quartz.properties").getInputStream());
        return prop;
    }





}