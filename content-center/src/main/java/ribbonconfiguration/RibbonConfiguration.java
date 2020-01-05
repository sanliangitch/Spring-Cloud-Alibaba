package ribbonconfiguration;

import com.netflix.loadbalancer.IRule;
import com.wulang.contentcenter.configuration.NacosSameClusterWeightedRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  面试题：           Spring父子上下文重叠？
 * 父子上下文扫描重叠：会导致事物不生效
 *
 * 一般我们在Spring的配置文件application.xml中对Service的方法进行AOP增强或事物处理如事物回滚，
 * 但是遇到一个问题，在Controller类中调用Service层方法，配置的事物管理会失效。
 * 查询相关资料发现，
 * Spring和SpringMVC两个容器为父子关系，Spring为父容器，而SpringMVC为子容器。
 * 也就是说application.xml中应该负责扫描除了@Controller的注解如@Service，而SpringMVC的配置文件应该只负责扫描@Controller
 * 否则会产生重复扫描导致Spring容器中配置的事物失效。
 *
 * @author wulang
 * @create 2020/1/4/9:13
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
//        return new NacosWeightedRule();
        return new NacosSameClusterWeightedRule();
//        return new RandomRule();
    }

//    @Bean
//    public IPing ping(){
//        return new PingUrl();
//    }
}
