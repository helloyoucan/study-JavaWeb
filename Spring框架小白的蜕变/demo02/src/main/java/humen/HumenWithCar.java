package humen;


import car.Car;

public abstract class HumenWithCar implements Hunem {
    protected Car car;

    public HumenWithCar(Car car) {
        this.car = car;
    }

    public abstract void goHome();
}
