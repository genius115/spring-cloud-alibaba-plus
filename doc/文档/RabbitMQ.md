一、简介:
队列持久化  消息持久化


生产者---（server (host Exchange交换机---queue)）---消费者


二、消息类型：
1/点对点 hello-world
The simplest thing that does something
点对点消费
场景：注册增加积分

2/工作队列 work-queue   消费负载有限时
Distributing tasks among workers 
平均消费（ack消息确认机制 自动） 或者 能者多劳（ack消息确认机制 手动）
场景：注册增加积分


3、Publish/Subscribe 广播模型 fanout  交换机决定发送给那个队列
Sending messages to many consumers at once
场景：注册后，积分、短信、短信等消费


4、Routing 直联 (direct) 类似静态路由
Receiving messages selectively


5、Routing (topic)  动态路由
Receiving messages based on a pattern (topics)
* (star) can substitute for exactly one word.
【#】 (hash) can substitute for zero or more words.
