package pro.crzang.web.client.model;

import com.google.inject.Inject;
import com.google.inject.Provider;
import pro.crzang.web.client.model.bean.ErrorInfoBean;

/**
 * Регистрация фабрик бинов.
 *
 * @author Alexey
 */
final class BeanFactoryRegistrationImpl
    implements BeanFactoryRegistration {

    @Inject
    private Provider<BeanFactoryMapping> beanFactoryMappingProvider;
    @Inject
    private Provider<ErrorInfoBean> errorInfoProvider;

    //@Inject Provider

    /**
     * Конструтор.
     */
    @Inject
    private BeanFactoryRegistrationImpl() {
    }

    /**
     * Регистрация.
     */
    @Override
    public void registrationFactories() {
        BeanFactoryMapping beanFactoryMapping = beanFactoryMappingProvider.get();
        //register interface to provider
        beanFactoryMapping.register(ErrorInfoBean.class, errorInfoProvider);
    }
}
