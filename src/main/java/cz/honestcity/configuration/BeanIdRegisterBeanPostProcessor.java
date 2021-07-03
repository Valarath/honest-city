package cz.honestcity.configuration;

import cz.honestcity.service.configuration.HonestCityService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BeanIdRegisterBeanPostProcessor implements BeanDefinitionRegistryPostProcessor {
    /*
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
   */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }


    public static String deCapitalize(String string) {
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }
/*
    private void changeBeansIds(BeanDefinitionRegistry registry, Map.Entry<String, Object> entry) {
        Class<?> beanClass = entry.getValue().getClass();
        MyServiceAnnotation annotation = beanClass.getAnnotation(MyServiceAnnotation.class);
        System.out.println(annotation.value());
        registry.removeBeanDefinition(entry.getKey());
        registry.registerBeanDefinition(annotation.value().name(), BeanDefinitionBuilder.genericBeanDefinition(beanClass).getBeanDefinition());

    }*/

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String[] beanNamesForAnnotation = ((ConfigurableListableBeanFactory) registry).getBeanNamesForAnnotation(HonestCityService.class);
        for (String beanNames : beanNamesForAnnotation)
            changeBeansIds(registry, (ScannedGenericBeanDefinition) registry.getBeanDefinition(beanNames));
    }


    private void changeBeansIds(BeanDefinitionRegistry registry, ScannedGenericBeanDefinition beanDefinition) {
        Map<String, Object> annotationAttributes = beanDefinition.getMetadata().getAnnotationAttributes(HonestCityService.class.getName());
        String[] split = beanDefinition.getMetadata().getClassName().split("\\.");
        String oldId = deCapitalize(split[split.length - 1]);
        registry.removeBeanDefinition(oldId);
        registry.registerBeanDefinition(getNewId(annotationAttributes), beanDefinition);

    }

    private String getNewId(Map<String, Object> annotationAttributes) {
        Class newId = (Class) annotationAttributes.get("beanId");
        Class newIdSpecification = (Class) annotationAttributes.get("beanIdSpecification");
        return newIdSpecification.equals(Object.class)? newId.getSimpleName(): newId.getSimpleName()+newIdSpecification.getSimpleName();
    }
}
