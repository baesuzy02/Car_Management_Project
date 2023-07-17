
package view;

import Controller.BrandList;
import Controller.CarList;
import Controller.Validation;
import java.util.ArrayList;
import java.util.Scanner;


public class CarManager {

    public static void main(String[] args) {
        int choice;
        String fileCar = "D:\\Code\\Java\\CarPrj\\cars.txt";
        String fileBrand = "D:\\Code\\Java\\CarPrj\\brands.txt";
        BrandList brandList = new BrandList();
        CarList carList = new CarList(brandList);
        Menu menu = new Menu();
        Validation vl = new Validation();
        brandList.loadFromFile(fileBrand);
        carList.loadFromFile(fileCar);
        ArrayList<String> options = new ArrayList<>(11);
        options.add(" List all brands");
        options.add(" Add a new brand");
        options.add(" Search a brand based on its ID");
        options.add(" Update a brand");
        options.add(" Save brands to the file, named brands.txt");
        options.add(" List all cars in ascending order of brand names");
        options.add(" List cars based on a part of an input brand name");
        options.add(" Add a car");
        options.add(" Remove a car based on its ID");
        options.add(" Update a car based on its ID");
        options.add(" Save cars to file, named cars.txt");

        do {
            System.out.println("-------------------------Menu-------------------------");
            choice = menu.int_getchoice(options);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    System.out.println("\n");
                    break;
                case 2:
                    brandList.addBrand();
                    System.out.println("\n");
                    break;
                case 3:
                    String bID = vl.inputBrandID("Input Brand ID: ", brandList);
                    if (brandList.searchID(bID) == -1) {
                        System.out.print("Brand ID not found !");
                    } else {
                        System.out.println(brandList.getListBrand().get(brandList.searchID(bID)));
                    }
                    System.out.println("\n");
                    break;
                case 4:
                    brandList.updateBrand();
                    System.out.println("\n");
                    break;
                case 5:
                    brandList.saveToFile(fileBrand);
                    System.out.println("\n");
                    break;
                case 6:
                    carList.listCars();
                    System.out.println("\n");
                    break;
                case 7:
                    carList.printBasedBrandName();
                    System.out.println("\n");
                    break;
                case 8:
                    carList.addCar();
                    System.out.println("\n");
                    break;
                case 9:
                    if (carList.removeCar()) {
                        System.out.println("Remove successfully");
                    }
                    System.out.println("\n");
                    break;
                case 10:
                    carList.updateCar();
                    System.out.println("\n");
                    break;
                case 11:
                    carList.saveToFile(fileCar);
                    System.out.println("\n");
                    break;
            }
        } while (choice > 0 && choice < 12);

    }
}
