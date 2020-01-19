package Task_2;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choose; // menu option
        String sBrand;
        int mYear;
        int mPrice;

        Car[] carList = initCarList();
        List<Car> resultList = new ArrayList<>();

        do {
            showMenu();
            choose = in.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("Enter the brand of car");
                    sBrand = in.next();
                    for (Car car : carList) {
                        if (sBrand.equals(car.getBrandOfCar())) {
                            resultList.add(car);
                        }
                    }
                    showResult(resultList);
                    break;
                case 2:
                    System.out.println("Enter the number of years that the car is already operated");
                    mYear = in.nextInt();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    for (Car value : carList) {
                        if (calendar.get(Calendar.YEAR) - mYear >= value.getYearOfCar()) {
                            resultList.add(value);
                        }
                    }
                    showResult(resultList);
                    break;
                case 3:
                    System.out.println("Enter the year of production");
                    mYear = in.nextInt();
                    System.out.println("Enter the price");
                    mPrice = in.nextInt();
                    for (Car car : carList) {
                        if (car.getYearOfCar() == mYear && car.getPriceOfCar() > mPrice)
                            resultList.add(car);
                    }
                    showResult(resultList);
                    break;
                default:
                    in.close();
                    break;
            }
        } while (choose != 4);
    }

    private static void showMenu() {
        System.out.printf("%30s\n", "*** Car List Menu Options ***");
        System.out.println("1. View all of your selected brand cars");
        System.out.println("2. View cars older than N years");
        System.out.println("3. View selected period cars and the price of which is more then N");
        System.out.println("4. Quit");
    }

    private static Car[] initCarList() {
        final Car[] cars = {
                new Car(1, "BMW", "X5", 2010, "black", 13000, 169735),  // 1st car
                new Car(2, "Volkswagen", "Jetta", 2014, "grey", 12700, 169736), // 2nd car
                new Car(3, "Mazda", "6", 2014, "red", 15000, 869737), // etc...
                new Car(4, "TOYOTA", "Camry", 2008, "purple", 5000, 869738),
                new Car(5, "Ferrari", "458", 2010, "red", 200000, 869739),
                new Car(6, "Ford", "Focus", 2016, "yellow", 8800, 869739),
                new Car(7, "Aston Martin", "Rapide", 2011, "black", 78000, 869740),
                new Car(8, "Renault", "Megane", 2016, "white", 10899, 869741),
                new Car(9, "Dodge", "Journey", 2015, "white", 13700, 869742),
                new Car(10, "Mitsubishi", "Outlander XL", 2008, "red", 11200, 869743),
                new Car(11, "Volkswagen", "CC", 2011, "grey", 11000, 869744),
                new Car(12, "Lexus", "ES 350", 2017, "blue", 32000, 869745),
                new Car(13, "Mercedes-Benz", "ML 320", 2009, "black", 16900, 869746),
                new Car(14, "Jaguar", "X-Type 4x4", 2007, "grey", 24000, 869747),
                new Car(15, "Lamborghini", "Urus", 2016, "silver", 330000, 869748)
        };
        return cars;
    }

    private static void showResult(List<Car> resultList) {
        if (resultList.isEmpty()) System.out.println("Nothing found\n");
        else {
            printCars(resultList);
            resultList.clear();
        }
    }

    private static void printCars(List<Car> carlist) {
        System.out.printf("%-5s%-20s%-20s%-10s%-10s%-17s%-20s\n", "Id", "Brand", "Model",
                "Year", "Color", "Price", "Registration Number");
        for (Car car : carlist) {
            System.out.printf("%-5s", car.getId());
            System.out.printf("%-20s", car.getBrandOfCar());
            System.out.printf("%-20s", car.getModelOfCar());
            System.out.printf("%-10s", car.getYearOfCar());
            System.out.printf("%-10s", car.getColorOfCar());
            System.out.printf("$ %-15s", car.getPriceOfCar());
            System.out.printf("%-20s", car.getRegistrationNumberOfCar());
            System.out.println();
        }
    }
}