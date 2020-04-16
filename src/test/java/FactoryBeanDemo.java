import com.study.sys.utils.BaseController;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author wxl
 * @date 2020/4/15 08:39:29
 */
@Component
public class FactoryBeanDemo implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new BaseController();
    }

    @Override
    public Class<?> getObjectType() {
        return BaseController.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
