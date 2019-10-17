package humen;

import car.Car;

public class LiSi extends HumenWithCar{
    public LiSi(Car car) {
        super(car);
    }

    public void goHome() {
        car.start();
        car.stop();
    }
}
