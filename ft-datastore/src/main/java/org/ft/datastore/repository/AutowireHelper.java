package org.ft.datastore.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AutowireHelper implements ApplicationContextAware
{
    /*
        It is a bit weird that it is static. But Guy, the original
        author recommends this
        https://guylabs.ch/2014/02/22/autowiring-pring-beans-in-hibernate-jpa-entity-listeners/
    */
    private static ApplicationContext applicationContext;
    private static AutowireHelper INSTANCE = new AutowireHelper();

    private AutowireHelper() {
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        AutowireHelper.applicationContext = applicationContext;
    }

    public static void autowire(Object classToAutowire, Object... beansToAutowireInClass) {
        for (Object bean : beansToAutowireInClass) {
            if (bean == null) {
                getInstance().applicationContext.getAutowireCapableBeanFactory().autowireBean(classToAutowire);
                return;
            }
        }
    }

    public static AutowireHelper getInstance() {
        return INSTANCE;
    }
}

