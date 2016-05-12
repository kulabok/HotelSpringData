package hotel.config;

/**
 * Created by kulabok on 06.05.2016.
 */
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                DataConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses(){

        return new Class<?>[]{

        };
    }

    @Override
    protected String[] getServletMappings(){
        return new String[]{"/controller"};
    }
}
