package cljinject;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 
public class SwankServletContextListener implements ServletContextListener{
    ServletContext context;
    public void contextInitialized(ServletContextEvent contextEvent) {
        System.out.println(">>>>>>>>>>>>>>> Context Created");
        context = contextEvent.getServletContext();
        // set variable to servlet context
        context.setAttribute("TEST", "TEST_VALUE");
        
        System.out.println(">>>>>>>>>>>>>>> now waiting ... ;)");

        new Thread() {
            public void run() {
                try {
                    while (true) {
                        System.out.print(".");
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
    public void contextDestroyed(ServletContextEvent contextEvent) {
        context = contextEvent.getServletContext();
        System.out.println(">>>>>>>>>>>>>>>> Context Destroyed");
    }
}
