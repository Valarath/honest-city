package cz.honestcity.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BeanIdRegisterBeanPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Map<String, Object> beansWithAnnotation = ((ConfigurableListableBeanFactory) registry).getBeansWithAnnotation(HonestCityService.class);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet())
            changeBeansIdsToAnnotationBeanId(registry, entry);
    }

    private void changeBeansIdsToAnnotationBeanId(BeanDefinitionRegistry registry, Map.Entry<String, Object> entry) {
        Class<?> beanClass = entry.getValue().getClass();
        HonestCityService annotation = beanClass.getAnnotation(HonestCityService.class);
        registry.removeBeanDefinition(entry.getKey());
        registry.registerBeanDefinition(annotation.beanId().getSimpleName(), BeanDefinitionBuilder.genericBeanDefinition(beanClass).getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
