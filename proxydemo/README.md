### 两种常见的动态代理的例子
动态代理在互联网很多场景都有使用。
例如：spring aop、mybatis mapper....
所以觉得很有必要进行记忆一下
此处也做一下jdk和cglib两种动态代理的比较：

此处做一个比较显示的比较

jdk：需要首先实现一个接口IPerson类，然后通过一个代理类进行InvocationHandler进行实现，引入被代理类，并实现invoke()方法，可以很清楚的看到invoke里
就是代理类所实现的流程，最后通过Proxy的静态方法newProxyInstance()从而生成代理类，进行方法调用

cglib：不需要实现接口，直接通过代理类对MethodInterceptor进行实现，实现其中intercept，并通过封装Enhancer对象将被代理类引入并设置回调类，最后直接
通过getProxy()方法进行代理。
