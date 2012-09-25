package org.bobmarks.spring;

public class GreetingServiceImpl implements GreetingService {

    private String greeting;
   
    public GreetingServiceImpl () {}
   
    public GreetingServiceImpl (String greeting) {
        this.greeting = greeting;
    }
   
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
   
    public String getGreeting () {
        return this.greeting;
    }
   
    @Override
    public void displayGreeting() {
        System.out.println (greeting);
    }
}