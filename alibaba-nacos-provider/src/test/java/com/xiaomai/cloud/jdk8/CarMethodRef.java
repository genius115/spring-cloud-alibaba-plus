package com.xiaomai.cloud.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 * Java 8 方法引用  ::
 * @author Madison
 * @date 2021/1/17
 */
class CarMethodRef {

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static CarMethodRef create(final Supplier<CarMethodRef> supplier) {
        return supplier.get();
    }

    public static void collide(final CarMethodRef car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final CarMethodRef another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {

        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        final CarMethodRef car = CarMethodRef.create( CarMethodRef::new );
        final List<CarMethodRef> cars = Arrays.asList( car );

        //静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach( CarMethodRef::collide );

        //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach( CarMethodRef::repair );

        //特定对象的方法引用：它的语法是instance::method实例如下：
        final CarMethodRef police = CarMethodRef.create( CarMethodRef::new );
        cars.forEach( police::follow );


        List<String> names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        //System.out::println 方法作为静态方法来引用。
        names.forEach(System.out::println);

    }
}
