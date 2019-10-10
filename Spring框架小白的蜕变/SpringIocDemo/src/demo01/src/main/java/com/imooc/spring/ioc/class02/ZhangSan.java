package com.imooc.spring.ioc.class02;

public class ZhangSan {
    //2
//    private Audi audi = new Audi();
    //换车
//    private Buick audi = new Buick();
    //3
    private Car car;

    public ZhangSan(Car car){
        this.car = car;
    }
    public void goHome(){
        //1
//        Audi audi = new Audi();
        //2
//        audi.start();
//        audi.turnLeft();
//        audi.turnRight();
//        audi.stop();
        //3
        car.start();
        car.turnLeft();
        car.turnRight();
        car.stop();
    }

    public void goShop(){
        //1
//        Audi audi = new Audi();
//        audi.start();
//        audi.turnLeft();
//        audi.turnRight();
//        audi.stop();
        //3
        car.start();
        car.turnLeft();
        car.turnRight();
        car.stop();
    }
}

