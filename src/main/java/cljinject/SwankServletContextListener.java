package cljinject;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import clojure.lang.Compiler; 
import java.io.StringReader; 

public class SwankServletContextListener implements ServletContextListener{

    // keep a hand on the context so we can get it from within clojure
    public static ServletContext context;

    public void contextInitialized(ServletContextEvent contextEvent) {
        System.out.println(">>>>>>>>>>>>>>> Context Created");
        context = contextEvent.getServletContext();
        // set variable to servlet context
        context.setAttribute("TEST", "TEST_VALUE");
        
        System.out.println(">>>>>>>>>>>>>>> now waiting ... ;)");

        new Thread() {
            public void run() {
                try {
                    final String startSwankScript = 
                        "(ns my-app\n" + 
                        "  (:use [swank.swank :as swank]))\n" + 
                        "(swank/start-repl) "; 
                    Compiler.load(new StringReader(startSwankScript)); 
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
