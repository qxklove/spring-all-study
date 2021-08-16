# Spring Context 层次结构

委托机制：在自己的 context 中找不到 bean，会委托父 context 查找该 bean。

各个 context 相互独立，每个 context 的 aop 增强只对本 context 的 bean 生效。  
如果想将切面配置成通用的，对父和子上下文的 bean 均支持增强，则：
1. 切面 Aspect 定义在父上下文。
2. 父上下文和子上下文，均要开启 aop 的增加，即 @EnableAspectJAutoProxy 或 <aop: aspectj-autoproxy /> 的支持。

参考：https://time.geekbang.org/course/detail/100023501-85418