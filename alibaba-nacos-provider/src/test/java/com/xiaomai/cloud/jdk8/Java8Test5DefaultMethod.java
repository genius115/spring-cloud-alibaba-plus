package com.xiaomai.cloud.jdk8;

/**
 * @author Madison
 * @date 2021/1/17
 */
public class Java8Test5DefaultMethod{
    public static void main(String args[]) {
        Vehicle vehicle = new Car2();
        vehicle.print();
    }
}

interface Vehicle {
    default void print() {
        System.out.println("我是一辆车!");
    }

    static void blowHorn() {
        System.out.println("按喇叭!!!");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车!");
    }
}

class Car2 implements Vehicle, FourWheeler {
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }
}

