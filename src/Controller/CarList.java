package Controller;

import view.Menu;
import Model.Brand;
import Model.Car;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class CarList extends ArrayList<Car> {

    private ArrayList<Car> listCar;
    private String carID, color, frameID, engineID;
    Brand brand;
    BrandList listBrand;
    private Validation vl;
    private Menu menu;
    private final Scanner sc = new Scanner(System.in);

    public CarList(BrandList bList) {
        listCar = new ArrayList<>();
        listBrand = bList;
        vl = new Validation();
        menu = new Menu();
    }

    public ArrayList<Car> getListCar() {
        return listCar;
    }

    public boolean loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                this.carID = data[0].trim();
                brand = listBrand.getListBrand().get(listBrand.searchID(data[1].trim()));
                this.color = data[2].trim();
                this.frameID = data[3].trim();
                this.engineID = data[4].trim();

                Car car = new Car(carID, brand, color, frameID, engineID);
                listCar.add(car);
            }
            return true;
        } catch (IOException e) {
            System.out.println("File not found " + e.getMessage());
        }
        return false;
    }

    public boolean saveToFile(String filename) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filename));
            for (Car b : listCar) {
                pw.println(b);
            }
            pw.close();
            System.out.println("Save successfully!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int searchFrame(String fID) {
        for (int i = 0; i < listCar.size(); i++) {
            if (fID.equals(listCar.get(i).getFrameID())) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        for (int i = 0; i < listCar.size(); i++) {
            if (eID.equals(listCar.get(i).getEngineID())) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        this.carID = vl.inputCarID("Enter Car's ID to add: ", listCar);
        this.brand = (Brand)menu.ref_getChoice(listBrand.getListBrand());
        this.color = vl.inputString("Enter Car's color: ");
        this.frameID = vl.inputFrameId("Enter Frame's ID: ", listCar);
        this.engineID = vl.inputEngineId("Enter Engine's ID: ", listCar);
        Car c = new Car(carID, brand, color, frameID, engineID);
        listCar.add(c);
        System.out.println("Add successfully");
    }

    public boolean updateCar() {
        String updateID;
        System.out.print("Enter Car's ID to update: ");
        updateID = sc.nextLine();
        int pos = searchID(updateID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            this.brand = (Brand) menu.ref_getChoice(listBrand.getListBrand());
            this.color = vl.inputString("Enter new color: ");
            this.frameID = vl.inputFrameId("Enter new Frame ID: ", listCar);
            this.engineID = vl.inputEngineId("Enter new Engine ID: ", listCar);
        }
        listCar.get(pos).setUpdateCar(brand, color, frameID, engineID);
        return true;
    }

    public int searchID(String carID) {
        for (int i = 0; i < listCar.size(); i++) {
            if (carID.equals(listCar.get(i).getCarID())) {
                return i;
            }
        }
        return -1;
    }

    public void printBasedBrandName() {
        String aPartOfBrandName;
        System.out.print("Enter Brand's name: ");
        aPartOfBrandName = sc.nextLine();
        int count = 0;
        for (int i = 0; i < listCar.size(); i++) {
            if (listCar.get(i).brand.getBrandName().contains(aPartOfBrandName)) {
                System.out.println(listCar.get(i).screenString());
                count++;
            }

        }
        if (count == 0) {
            System.out.println("No car detected!");
        }
    }

    public boolean removeCar() {
        String removeID;
        System.out.print("Enter Car ID to remove: ");
        removeID = sc.nextLine();
        int pos = searchID(removeID);
        if (pos < 0) {
            return false;
        }
        listCar.remove(pos);
        return true;
    }

    public void listCars() {
        List<Car> sortList = (ArrayList<Car>) listCar;
        Collections.sort(sortList, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.brand.getBrandName().compareTo(o2.brand.getBrandName());
            }
        });
        for (int i = 0; i < sortList.size(); i++) {
            System.out.println(sortList.get(i));
        }
    }
}
