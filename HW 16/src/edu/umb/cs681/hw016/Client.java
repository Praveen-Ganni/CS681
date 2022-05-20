package edu.umb.cs681.hw016;

import java.util.ArrayList;

public class Client {
	public static void main(String args[])
	{
		ArrayList<Car> cars = new ArrayList<Car>();
		Car a = new Car("Prius");
		Car b = new Car("Escape");
		Car c = new Car("ModelS");
		Car d = new Car("CX-5");
		
		cars.add(a);
		cars.add(b);
		cars.add(c);
		cars.add(d);
		
		int carMakerCount = cars.stream()
				.parallel()
				.map((Car car)-> car.getMaker())
				.reduce(0,
				(count,carMaker)-> ++count,
				(final_result,int_result)->final_result + int_result);
		System.out.println("Total number of makers are : " + carMakerCount);

		
	}
}