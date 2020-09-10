/*
 *    Copyright 2019-2030 codecuriosity
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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

