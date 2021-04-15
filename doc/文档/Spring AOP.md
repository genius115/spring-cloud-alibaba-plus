AspectJ的切入点表达式---execution表达式详解 或者 注解

@Pointcut("@annotation(com.xiaomai.cloud.api.comm.annotation.Limiter)")

@Pointcut("execution(* com.sample.service.impl..*.*(..))")

Aspectj切入点语法定义

在使用spring框架配置AOP的时候，不管是通过XML配置文件还是注解的方式都需要定义pointcut”切入点”

例如定义切入点表达式 execution (* com.sample.service.impl..*.*(..))

> execution (* com.sample.service.impl..*. *(..))

execution()是最常用的切点函数，其语法如下所示：

整个表达式可以分为五个部分：

1、execution(): 表达式主体。

2、第一个*号：表示返回类型， *号表示所有的类型。

3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。

4、第二个*号：表示类名，*号表示所有的类。

5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数




AspectJ的Execution表达式

execution()

execution()是最常用的切点函数，其语法如下所示：

execution(<修饰符模式>? <返回类型模式> <方法名模式>(<参数模式>) <异常模式>?) 除了返回类型模式、方法名模式和参数模式外，其它项都是可选的。与其直接讲解该方法的使用规则，还不如通过一个个具体的例子进行理解。下面，我们给出各种使用execution()函数实例。

1)通过方法签名定义切点

execution(public * *(..))l

匹配所有目标类的public方法，但不匹配SmartSeller和protected void showGoods()方法。第一个代表返回类型，第二个代表方法名，而..代表任意入参的方法；

execution(* *To(..))l

匹配目标类所有以To为后缀的方法。它匹配NaiveWaiter和NaughtyWaiter的greetTo()和serveTo()方法。第一个*代表返回类型，而*To代表任意以To为后缀的方法；

2)通过类定义切点

execution(* com.baobaotao.Waiter.*(..))l

匹配Waiter接口的所有方法，它匹配NaiveWaiter和NaughtyWaiter类的greetTo()和serveTo()方法。第一个代表返回任意类型，com.baobaotao.Waiter.代表Waiter接口中的所有方法；

execution(* com.baobaotao.Waiter+.*(..))l

匹 配Waiter接口及其所有实现类的方法，它不但匹配NaiveWaiter和NaughtyWaiter类的greetTo()和serveTo()这 两个Waiter接口定义的方法，同时还匹配NaiveWaiter#smile()和NaughtyWaiter#joke()这两个不在Waiter 接口中定义的方法。

3)通过类包定义切点

在类名模式串中，“.”表示包下的所有类，而“..”表示包、子孙包下的所有类。

execution(* com.baobaotao.*(..))l

匹配com.baobaotao包下所有类的所有方法；

execution(* com.baobaotao..*(..))l

匹 配com.baobaotao包、子孙包下所有类的所有方法，如com.baobaotao.dao，com.baobaotao.servier以及 com.baobaotao.dao.user包下的所有类的所有方法都匹配。“..”出现在类名中时，后面必须跟“*”，表示包、子孙包下的所有类；

execution(* com...*Dao.find(..))l

匹配包名前缀为com的任何包下类名后缀为Dao的方法，方法名必须以find为前缀。如com.baobaotao.UserDao#findByUserId()、com.baobaotao.dao.ForumDao#findById()的方法都匹配切点。

4)通过方法入参定义切点

切点表达式中方法入参部分比较复杂，可以使用“”和“ ..”通配符，其中“”表示任意类型的参数，而“..”表示任意类型参数且参数个数不限。

execution(* joke(String,int)))l

匹 配joke(String,int)方法，且joke()方法的第一个入参是String，第二个入参是int。它匹配 NaughtyWaiter#joke(String,int)方法。如果方法中的入参类型是java.lang包下的类，可以直接使用类名，否则必须使用全限定类名，如joke(java.util.List,int)；

execution(* joke(String,*)))l

匹 配目标类中的joke()方法，该方法第一个入参为String，第二个入参可以是任意类型，如joke(String s1,String s2)和joke(String s1,double d2)都匹配，但joke(String s1,double d2,String s3)则不匹配；

execution(* joke(String,..)))l

匹配目标类中的joke()方法，该方法第 一个入参为String，后面可以有任意个入参且入参类型不限，如joke(String s1)、joke(String s1,String s2)和joke(String s1,double d2,String s3)都匹配。

execution(* joke(Object+)))l

匹 配目标类中的joke()方法，方法拥有一个入参，且入参是Object类型或该类的子类。它匹配joke(String s1)和joke(Client c)。如果我们定义的切点是execution(* joke(Object))，则只匹配joke(Object object)而不匹配joke(String cc)或joke(Client c)。

args()和@args()

args()函数的入参是类名，@args()函数的入参必须是注解类的类名。虽然args()允许在类名后使用+通配符后缀，但该通配符在此处没有意义：添加和不添加效果都一样。

1)args()

该函数接受一个类名，表示目标类方法入参对象按类型匹配于指定类时，切点匹配，如下面的例子：

args(com.baobaotao.Waiter)

表 示运行时入参是Waiter类型的方法，它和execution(* (com.baobaotao.Waiter))区别在于后者是针对类方法的签名而言的，而前者则针对运行时的入参类型而言。如 args(com.baobaotao.Waiter)既匹配于addWaiter(Waiter waiter)，也匹配于addNaiveWaiter(NaiveWaiter naiveWaiter)，而execution( (com.baobaotao.Waiter))只匹配addWaiter(Waiter waiter)方法；实际上，args(com.baobaotao.Waiter)等价于execution( *(com.baobaotao.Waiter+))，当然也等价于args(com.baobaotao.Waiter+)。

2)@args()

该函数接受一个注解类的类名，当方法的运行时入参对象标注发指定的注解时，方法匹配切点。这个切点函数的匹配规则不太容易理解，我们通过以下示意图对此进行详细讲解：

            图 4 @arg(M)匹配示意图(1)
     
    T0、T1、T2、T3具有如图所示的继承关系，假设目标类方法的签名为fun(T1 t)，它的入参为T1，而切面的切点定义为@args(M)，T2类标注了@M。当fun(T1 t)传入对象是T2或T3时，则方法匹配@args(M)所声明定义的切点；

    1
    2
    3
    4

再看下面的情况，假设方法签名是fun(T1 t)，入参对于T1，而标注@M的类是T0，当funt(T1 t)传入T1、T2、T3的实例时，均不匹配切点@args(M)。

            图 5 @arg(M)匹配示意图(2)
     
    在类的继承树中，①点为方法签名中入参类型在类继承树中的位置，我们称之为入参类型点，而②为标注了@M注解的类在类继承树中位置，我们称之为注解点。判断方法在运行时是否匹配@agrs(M)切点，可以根据①点和②点在类继承树中的相对位置来判别：

    1
    2
    3
    4

1) 如果在类继承树中注解点②高于入参类型点①，则该目标方法不可能匹配切点@args(M)，如图 5所示；

2) 如果在类继承树中注解点②低于入参类型点①，则注解点所在类及其子孙类作为方法入参时，该方法匹配@args(M)切点，如图 4所示。

下 面举一个具体的例子，假设我们定义这样的切点：@args(com.baobaotao.Monitorable) ，如果NaiveWaiter标注了@Monitorable，则对于WaiterManager#addWaiter(Waiter w)方法来说，如果入参是NaiveWaiter或其子类对象，该方法匹配切点，如果入参是NaughtyWaiter对象，不匹配切点。如果 Waiter标注了@Monitorable，但NaiveWaiter未标注@Monitorable，则 WaiterManager#addNaiveWaiter(NaiveWaiter w)却不匹配切点，这是因为注解点（在Waiter）高于入参类型点（NaiveWaiter）。