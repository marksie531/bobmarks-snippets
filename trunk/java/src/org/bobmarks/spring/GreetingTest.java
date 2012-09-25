package org.bobmarks.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class GreetingTest {

    public static void main(String[] args) throws Exception {
        BeanFactory factory = new XmlBeanFactory (new FileSystemResource ("greeting.xml"));
        GreetingService greetingService = (GreetingService)factory.getBean("greetingService");
        greetingService.displayGreeting();
    }
   
}