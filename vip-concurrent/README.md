1、【课后作业】初步认识多线程的发展及使用
	利用多线程解决一个实际问题。具体的问题可以结合大家以往项目中遇到过的问题进行优化。
	要求：(1):通过图形方式表达使用前后的差异； (2):代码实现
	https://gper.gupaoedu.com/homework/subjects/5265
2、【课后作业】多线程的原理分析(1)
	1.请分析以下程序的执行结果,并详细说明原因。
	2.下面这个程序的最终结果是多少？为什么？	（代码截图看下方的链接）
	https://gper.gupaoedu.com/homework/subjects/5422
3、【课后作业】多线程的原理分析(2)
	1.请列出Happens-before的几种规则
		1）程序顺序规则：一个线程中的每个操作，happens-before于该线程中的任意后续操作。
		2）监视器锁规则：对一个锁的解锁，happens-before于随后对这个锁的加锁。
		3）volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读。
		4）传递性：如果A happens-before B，且B happens-before C，那么A happens-before C。
		5）start()规则：如果线程A执行操作ThreadB.start()（启动线程B），那么A线程的ThreadB.start()操作happens-before于线程B中的任意操作。
		6）join()规则：如果线程A执行操作ThreadB.join()并成功返回，那么线程B中的任意操作happens-before于线程A从ThreadB.join()操作成功返回。
	2.volatile 能使得一个非原子操作变成原子操作吗？为什么？
		不能，volatile只是保证写操作happens-before 读可见，如果在多线程的情况下，特别是在有阻塞的情况下，cpu会抢夺资源的情况下无法保证数据的原子性；
		volatile 并没有原子性：比如： i++ 就不是原子操作 分成三步 第一步 获取i的值 第二步 i+1 第三步 设置值 i=新值	所以并不是原子操作.
	3.哪些场景适合使用Volatile
		多线程下 让每个线程都可见的情况下。
		Volatile特性有：可见性，禁止重排序。可以保证程序中可以查看到变量最新的值，被其修饰的变量会被用汇编指令lock来做内存屏障，也就是说操作该字段都是强制同步到主内存中，同时获取也是在主内存中。
	4.如果对一个数组修饰volatile，是否能够保证数组元素的修改对其他线程的可见？为什么？
		volatile修饰变量或数组之类的，其含义是对象或数组的地址具有可见性，但是对象或对象内部的成员改变不具备可见性。
	https://gper.gupaoedu.com/homework/subjects/5493
4、【课后作业】AQS的底层原理分析
	按照上课的分析思路，用逻辑图画出ReentrantReadWriteLock的实现原理。
	https://gper.gupaoedu.com/homework/subjects/5624
5、【课后作业】常见并发工具的基本原理分析
	请结合ReentrantLock、Condition实现一个简单的阻塞队列，阻塞队列提供两个方法，一个是put、一个是take
	1.当队列为空时，请求take会被阻塞，直到队列不为空
	2.当队列满了以后，存储元素的线程需要备阻塞直到队列可以添加数据
	https://gper.gupaoedu.com/homework/subjects/5704
6、【课后作业】ConcurrentHashMap的原理分析
	1.ConcurrentHashMap1.8中是基于什么机制来保证线程安全性的
		ConcurrentHashMap1.8存储是采用数数组+链表+红黑树方式来存储；
		put方法保证安全性若加入的数据没在数组下标为空时采用CAS保证安全性，若有则使用synchronized关键字对头结点加锁保证安全性；
		addCount是采用CAS+CounterCell数组保证并发安全性
	2.ConcurrentHashMap通过get方法获取数据的时候，是否需要通过加锁来保证数据的可见性？为什么？
		不需要，因为Node结点中的val和next都是用volatile关键字修饰，，说明每次添加都对读可见
	3.ConcurrentHashMap1.7和ConcurrentHashMap1.8有哪些区别？
		1.取消了 segment 分段设计，直接使用 Node 数组来保存数据，并且采用 Node 数组元素作为锁来实现每一行数据进行加锁来进一步减少并发冲突的概率。
		2.将原本数组+单向链表的数据结构变更为了数组+单向链表+红黑树的结构，优化了计算方式，数据量大时使查询效率更快。
	4.ConcurrentHashMap1.8为什么要引入红黑树？
		防止链表的长度过长，减少查询时间。队列长度超过 8 的列表，数组大小扩容超过64位时，JDK1.8 采用了红黑树的结构。
		链表线性查找的复杂度在O(n),当n值增加到一定大小时，会严重影响到查询效率。
		红黑树来说，时间复杂度为O(log(n)),数据量越大查找的速度相对于线性链表越快,可以提升查找的性能。
	https://gper.gupaoedu.com/homework/subjects/5743
7、【课后作业】阻塞队列、原子操作的原理分析
	1.请使用阻塞队列来解决一个实际问题
	https://gper.gupaoedu.com/homework/subjects/5826
8、【课后作业】线程池、forkjoin的原理分析
	1.为什么要使用线程池？
		如果每个请求到达就创建一个新线程，创建和销毁线程花费的时间和消耗的系统资源都相当大，甚至可能要比在处理实际的用户请求的时间和资源要多的多。如果在一个 Jvm 里创建太多的线程，可能会使系统由于过度消耗内存或“切换过度”而导致系统资源不足。
	2.Executors提供的四种线程: 请说出他们的区别以及应用场景
		newSingleThreadExecutor：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先 级)执行。若空闲则执行，若没有空闲线程则暂缓在任务队列中。不会有多个线程是活动的应用场景。
		newFixedThreadPool：该方法返回一个固定数量的线程池，线程数不变，当有一个任务提交时，若线程池中空闲，则立即执行，若没有，则会被暂缓在一个任务队列中，等待有空闲的线程去执行。 它适用于负载比较重的服务器。
		newCachedThreadPool：返回一个可根据实际情况调整线程个数的线程池，不限制最大线程数量，若用空闲的线程则执行任务，若无任务则不创建线程。并且每一个空闲线程会在60秒后自动回收。是大小无界的线程池，适用于执行很多的短期异步任务的小程序，或者是负载较轻的服务器。
		newScheduledThreadPool：创建一个可以指定线程的数量的线程池，但是这个线程池还带有延迟和周期性执行任务的功能，类似定时器。适用于需要多个后台线程执行周期任务，同时为了满足资源管理的需求而需要限制后台线程的数量的应用场景。
	3. 线程池有哪几种工作队列？
		ArrayBlockingQueue 数组实现的有界阻塞队列, 此队列按照先进先出（FIFO）的原则对元素进行排序。
		LinkedBlockingQueue 链表实现的有界阻塞队列, 此队列的默认和最大长度为Integer.MAX_VALUE。此队列按照先进先出的原则对元素进行排序
		PriorityBlockingQueue 支持优先级排序的无界阻塞队列, 默认情况下元素采取自然顺序升序排列。也可以自定义类实现 compareTo()方法来指定元素排序规则，或者初始化 PriorityBlockingQueue 时，指定构造参数 Comparator 来对元素进行排序。
		DelayQueue 优先级队列实现的无界阻塞队列
		SynchronousQueue 不存储元素的阻塞队列, 每一个 put 操作必须等待一个 take 操作，否则不能继续添加元素。
		LinkedTransferQueue 链表实现的无界阻塞队列
		LinkedBlockingDeque 链表实现的双向阻塞队列
	4. 线程池默认的拒绝策略有哪些
		1、AbortPolicy：直接抛出异常，默认策略；
		2、CallerRunsPolicy：用调用者所在的线程来执行任务；
		3、DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
		4、DiscardPolicy：直接丢弃任务；
	5. 如何理解有界队列和无解队列
		有界队列就是规定了大小的队列，无界队列就是没有设置固定大小的队列。但是一般情况都会设置大小，用有界队列，避免内存溢出。
	6. 线程池是如何实现线程回收的？ 以及核心线程能不能被回收？
		当参数allowCoreThreadTimeOut为true，当前线程池的大小大于核心线程池大小（corePoolSize）,满足这两个条件后，一个线程在keepAliveTime时间内未获取的到任务时即空闲了这么久，该线程就会被回收。回收方法shutdown()和shutdownnow()
		核心线程也能被回收（allowCoreThreadTimeOut为true时）。
	7. FutureTask是什么
		Future接口和实现Future接口的FutureTask类，代表异步计算的结果。
	8. Thread.sleep(0)的作用是什么
		在抢占式CPU调度中，通知CPU释放本线程的执行权，触发操作系统立刻重新进行一次CPU竞争
	9. 如果提交任务时，线程池队列已满，这时会发生什么
		执行拒绝策略。
	10.如果一个线程池中还有任务没有执行完成，这个时候是否允许被外部中断？
		可以通过interrupt方式被终止。
		shutdownNow立即终止线程池:线程池的状态设置成STOP，然后尝试停止所有的正在执行或暂停任务的线程，并且清空任务缓存队列，并返回等待执行任务的列表。
	shutdown不会立即清空线程池：将线程池的状态设置成SHUTDOWN状态，然后中断所有没有正在执行任务的线程。
	https://gper.gupaoedu.com/homework/subjects/5915


我的作业地址：
GP12741 
https://github.com/xianlonglu/vip/tree/master/vip-concurrent/src/main/java/com/lxl/thread/work
