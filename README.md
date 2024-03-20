这是个人练习demo，一个简易版的RPC框架

#### 什么是RPC？
> 专业定义：RPC（Remote Procedure Call）即远程过程调用，是一种计算机通信协议，它允许程序在不同的计算机之间进行通信和交互，就像本地调用一样。
> 简单理解就是消费者，外卖，商家之间的关系。如果没有外卖，你就需要自己亲自下楼买，但是有了外卖，在家就能买到，不需要关系外卖平台是怎么运作的，小哥哥们是怎么配送的，只需要收到货后开炫即可

#### 为什么需要RPC？
> RPC 允许一个程序（称为服务消费者）像调用自己程序的方法一样，调用另一个程序（称为服务提供者）的接口，而不需要了解数据的传输处理过程、底层网络通信的细节等。这些都会由 RPC 框架帮你完成，使得开发者可以轻松调用远程服务，快速开发分布式系统。