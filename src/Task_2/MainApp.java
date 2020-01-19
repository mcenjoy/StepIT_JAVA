package Task_2;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        int choose = 0;   // variable for menu
        int mYear;   // model year
        int mPrice;  // model price of car we are looking for
        Scanner in = new Scanner(System.in);    // our Scanner for switch
        // array of objects (creating and initializing)
        Car[] carlist = new Car[]{
                new Car(1, "BMW", "", 1994, "black", 2300, 869735),  // 1st car
                new Car(2, "AUDI", "", 2002, "yellow", 4000, 869736), // 2nd car
                new Car(3, "Volvo","", 1999, "green", 3300, 869737), // etc...
                new Car(4, "TOYOTA","", 2002, "purple", 5000, 869738),
                new Car(5, "Ferrari","", 2005, "red", 10000, 869739),
                new Car(6, "Ford","", 1967, "yellow", 8800, 869739),
                new Car(7, "Aston Martin","", 2009, "black", 18000, 869740),
                new Car(8, "Lada", "",2013, "white", 1800, 869741),
                new Car(9, "Dodge","", 2009, "white", 6700, 869742),
                new Car(10, "Mitsubishi","", 2008, "red", 3200, 869743),
                new Car(11, "Volkswagen","", 2016, "grey", 9000, 869744),
                new Car(12, "Lexus", "",2014, "blue", 11000, 869745),
                new Car(13, "Mercedes-Benz", "",2016, "black", 22000, 869746),
                new Car(14, "Jaguar", "",2015, "grey", 24000, 869747),
                new Car(15, "Lamborghini","", 2016, "silver", 30000, 869748)
        };
        List<Car> resultList = new ArrayList<>();

        do {
            showMenu();

            choose = in.nextInt();
            switch (choose) {
                // show all the cars
                case 1:
                    printCars(Arrays.asList(carlist));
                    break;
                // list of all cars that in use more then n years
                case 2:
                    System.out.println("Enter the number of years that the car is already operated");
                    mYear = in.nextInt();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    for (int i = 0; i < carlist.length; i++) {
                        if (calendar.get(Calendar.YEAR) - mYear >= carlist[i].getYearOfCar()) {
                            resultList.add(carlist[i]);
                        }
                    }
                    showResult(resultList);
                    break;
                //  list of cars of some model year with price more then
                case 3:
                    System.out.println("Enter the model year");
                    mYear = in.nextInt();
                    System.out.println("Enter the price");
                    mPrice = in.nextInt();
                    for (int i = 0; i < carlist.length; i++) {
                        if (carlist[i].getYearOfCar() == mYear && carlist[i].getPriceOfCar() > mPrice)
                            resultList.add(carlist[i]);
                    }
                    showResult(resultList);
                    break;
                default:
                    break;
            }
        } while (choose != 4);
    }

    private static void showMenu() {
        System.out.printf("%10s \n", "Menu");
        System.out.println("1. All cars");
        System.out.println("2. Cars that in use more then n years");
        System.out.println("3. Cars of some period price of which is more then");
        System.out.println("4. Exit");
    }

    private static void showResult(List<Car> resultList) {
        if (resultList.isEmpty()) {
            System.out.println("Nothing found");
        } else {
            printCars(resultList);
            resultList.clear();
        }
    }

    private static void printCars(List<Car> carlist) {
        System.out.printf("%-5s%-20s%-10s%-10s%-10s%-20s\n", "id", "brand", "year", "color", "price", "register number");
        for (int i = 0; i < carlist.size(); i++) {
            System.out.printf("%-5s", String.valueOf(carlist.get(i).getId()));
            System.out.printf("%-20s", String.valueOf(carlist.get(i).getBrandOfCar()));
            System.out.printf("%-10s", String.valueOf(carlist.get(i).getYearOfCar()));
            System.out.printf("%-10s", String.valueOf(carlist.get(i).getColorOfCar()));
            System.out.printf("%-10s", String.valueOf(carlist.get(i).getPriceOfCar()));
            System.out.printf("%-20s", String.valueOf(carlist.get(i).getRegistrationNumberOfCar()));
            System.out.println();
        }
    }
}