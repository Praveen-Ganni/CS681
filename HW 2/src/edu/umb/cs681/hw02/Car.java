package edu.umb.cs681.hw02;
import java.util.Comparator;
import java.util.List;

enum CarType {
    CNG,
    DIESEL,
    ELECTRIC,
    HYBRID,
    PETROL
}

public class Car {
    private String model;
    private String brand;
    private String color;
    private int maxSpeed;
    private int price;
    private CarType type;

    public Car(String model, String brand, String color, int maxSpeed, int price, CarType type) {
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.price = price;
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getPrice() {
        return price;
    }

    public CarType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{ "+model+" " + brand + " }";
    }

    public static void main(String[] args) {
        List<Car> cars = List.of(
                new Car("BMW", "X5", "Black", 220, 200000, CarType.PETROL),
                new Car("Mercedes", "C-Class", "White", 210, 150000, CarType.DIESEL),
                new Car("Audi", "A4", "Blue", 220, 350000, CarType.PETROL),
                new Car("Honda", "Civic", "Gray", 190, 120000, CarType.CNG),
                new Car("Tesla", "Model 3", "Red", 250, 1000000, CarType.ELECTRIC)
                );

        // find the highest price car
        Car highestPriceCar = cars.stream()
                .max(Comparator.comparing(Car::getPrice))
                .orElseThrow(() -> new RuntimeException("No cars found"));

        System.out.println("Highest price car " + highestPriceCar + " price is " + highestPriceCar.getPrice());

        // find the lowest price car
        Car lowestPriceCar = cars.stream()
                .min(Comparator.comparing(Car::getPrice))
                .orElseThrow(() -> new RuntimeException("No cars found"));

        System.out.println("Lowest price car " + lowestPriceCar + " price is " + lowestPriceCar.getPrice());

        // average price of all cars using reduce
        int avg_cost = cars.stream().map(Car::getPrice)
                .reduce(0, Integer::sum, (finalResult,
                                          intermediateResult) -> finalResult)/cars.size();

        System.out.println("Average price of all cars: " + avg_cost);
    }
}