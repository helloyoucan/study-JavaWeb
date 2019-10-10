package humen;

import car.Car;

public class ZhangSan extends HumenWithCar{
    public ZhangSan(Car car) {
        super(car);
    }

    public void goHome() {
        car.start();
        car.turnLeft();
        car.turnRight();
        car.stop();
    }
}
