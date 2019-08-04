package com.eumji.common.eventbus;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * event bus 简单使用案例 -> @Subscribe注解不能放在基类里面和泛型搭配会导致混乱
 * https://blog.csdn.net/huangyu1985/article/details/78735877
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2018/9/6
 * @time: 7:32
 */
public class EventBusDemo {

    class User {

    }

    class People {

    }

    interface EventListenerBase<T>{
        void listen(T event) throws InterruptedException;
    }

    class EvenrListenerA implements EventListenerBase<User>{

        @Override
        @Subscribe
        //@AllowConcurrentEvents
        public void listen(User event) throws InterruptedException {
            System.out.println("user event listener start current thread is: "+Thread.currentThread().getId());
            Thread.sleep(1000L);
            System.out.println("user event listener done");
        }
    }

    class EvenrListenerB implements EventListenerBase<People>{

        @Override
        @Subscribe
        //@AllowConcurrentEvents
        public void listen(People event) throws InterruptedException {
            System.out.println("EvenrListenerB people event listener start current thread is: "+Thread.currentThread().getId());
            Thread.sleep(1000L);
            System.out.println("EvenrListenerB people event listener done current thread is: "+Thread.currentThread().getId());
        }


    }
    class EvenrListenerC implements EventListenerBase<People>{

        @Override
        @Subscribe
        //@AllowConcurrentEvents
        public void listen(People event) throws InterruptedException {
            System.out.println("EvenrListenerC people event listener start current thread is: "+Thread.currentThread().getId());
            Thread.sleep(1000L);
            System.out.println("EvenrListenerC people event listener done current thread is: "+Thread.currentThread().getId());
        }


    }
    public void eventDemo() throws InterruptedException {
        EventBus eventBus = new EventBus();
        EvenrListenerA evenrListenerA = new EvenrListenerA();
        eventBus.register(evenrListenerA);
        EvenrListenerB evenrListenerB = new EvenrListenerB();
        eventBus.register(evenrListenerB);

        eventBus.post(new User());
        eventBus.post(new People());
        eventBus.post(new People());
        eventBus.post(new User());

        Thread.sleep(3000L);
        System.out.println("event push and listener end");


    }

    /**
     * 不开启AllowConcurrentEvents注解
     * 多线程设置post事件，多监听并行
     * user event listener start current thread is: 14
     * user event listener done
     * EvenrListenerB people event listener done current thread is: 13
     * event push and listener end
     *
     * 单线程post事件，串行
     *user event listener start current thread is: 1
     * user event listener done
     * EvenrListenerB people event listener start current thread is: 1
     * EvenrListenerB people event listener done current thread is: 1
     * @throws InterruptedException
     */
    public void diffrentSub() throws InterruptedException {
        EventBus eventBus = new EventBus();
        EvenrListenerA evenrListenerA = new EvenrListenerA();
        eventBus.register(evenrListenerA);
        EvenrListenerB evenrListenerB = new EvenrListenerB();
        eventBus.register(evenrListenerB);
        eventBus.post(new User());
        eventBus.post(new People());
//        new Thread(()-> eventbus.post(new People())).start();
//        new Thread(()-> eventbus.post(new User())).start();

        Thread.sleep(3000L);
        System.out.println("event push and listener end");


    }

    /**
     * AllowConcurrentEvents -》 多个线程post事件 ========针对的是统一事件的消费
     *
     * 开启注解结果
     * EvenrListenerB people event listener start current thread is: 13
     * EvenrListenerB people event listener start current thread is: 15
     * EvenrListenerB people event listener start current thread is: 14
     * EvenrListenerB people event listener done current thread is: 13
     * EvenrListenerB people event listener done current thread is: 14
     *
     * 不开启注解
     * EvenrListenerB people event listener start current thread is: 13
     * EvenrListenerB people event listener done current thread is: 13
     * EvenrListenerB people event listener start current thread is: 14
     * EvenrListenerB people event listener done current thread is: 14
     * EvenrListenerB people event listener start current thread is: 15
     * EvenrListenerB people event listener done current thread is: 15
     * @throws InterruptedException
     */
    public void threadEventDemo() throws InterruptedException{
        EventBus eventBus = new EventBus();
        //EvenrListenerC evenrListenerA = new EvenrListenerC();
        EvenrListenerB evenrListenerB = new EvenrListenerB();
        //eventbus.register(evenrListenerA);
        eventBus.register(evenrListenerB);

        for (int i = 0; i < 3; i++) {
            new Thread(()-> eventBus.post(new People())).start();
        }

        Thread.sleep(10000L);
        System.out.println("event push and listener end");
    }

        public static void main(String[] args) throws InterruptedException {
            EventBusDemo eventBusDemo = new EventBusDemo();
            //eventBusDemo.eventDemo();
            //eventBusDemo.threadEventDemo();
            eventBusDemo.diffrentSub();
        }
    }
