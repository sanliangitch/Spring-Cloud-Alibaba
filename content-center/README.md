## Sentinel api
* Sphu : 定义一个sentinel保护的资源，并让资源受到监控。SphU.entry(resourceName);
* Tracer : 对想要的异常进行统计。Tracer.trace(e2);
* ContextUtil : 实现调用来源，还可以标记调用。ContextUtil.enter(resourceName, "test-wfw");

## @SentinelResource()
* [流控规则](https://github.com/alibaba/Sentinel/wiki/如何使用#流量控制规则-flowrule)

SentinelBeanPostProcessor 非常重要的一个类