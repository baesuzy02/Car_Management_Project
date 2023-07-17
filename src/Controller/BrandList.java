
package Controller;

import view.Menu;
import Model.Brand;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class BrandList extends ArrayList<Brand> {

    private ArrayList<Brand> listBrand;
    private String brandID, brandName, soundBrand;
    private double price;
    private Validation vl;

    public BrandList() {
        listBrand = new ArrayList<>();
        vl = new Validation();
    }

    public ArrayList<Brand> getListBrand() {
        return listBrand;
    }

    public boolean loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                this.brandID = data[0].trim();
                this.brandName = data[1].trim();
                this.soundBrand = data[2].split(":")[0].trim();
                this.price = Double.parseDouble(data[2].split(":")[1].trim());

                Brand newBrand = new Brand(brandID, brandName, soundBrand, price);
                listBrand.add(newBrand);
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
            for (Brand b : listBrand) {
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

    public Brand getUserChoice() {
        Menu mnu = new Menu();
        return (Brand) mnu.ref_getChoice(listBrand);
    }

    public int searchID(String bID) {
        for (int i = 0; i < listBrand.size(); i++) {
            if (bID.equals(listBrand.get(i).getBrandID())) {
                return i;
            }
        }
        return -1;
    }

    public void addBrand() {
        brandID = vl.inputBrandID("Enter Brand's ID: ", listBrand);
        brandName = vl.inputString("Enter Brand's name: ");
        soundBrand = vl.inputString("Enter the sound manufacturer: ");
        boolean cont = false;
        do {
            try {
                price = vl.inputDouble("Enter the price: ");
                cont = false;
            } catch (InputMismatchException e) {
                System.out.println("The price must be number!");
                cont = true;
            } catch (Exception ex) {
                System.out.println("The price is invalid!");
                cont = true;
            }
        } while (cont);
        Brand newBrand = new Brand(brandID, brandName, soundBrand, price);
        listBrand.add(newBrand);
        System.out.println("Brand has added successfully");

    }

    public void updateBrand() {
        Scanner sc = new Scanner(System.in);
        String updateID;
        System.out.print("Enter Brand's ID to update: ");
        updateID = sc.nextLine();
        int pos = searchID(brandID);
        if (pos < 0) {
            System.out.println("Not found!");

        } else {
            this.brandName = vl.inputString("Enter new Brand's name: ");
            this.soundBrand = vl.inputString("Enter the new sound manufacturer: ");
            boolean cont = false;
            do {
                try {
                    price = vl.inputDouble("Enter the new price: ");
                    cont = false;
                } catch (InputMismatchException e) {
                    System.out.println("The price must be number!");
                    cont = true;
                } catch (Exception ex) {
                    System.out.println("The price is invalid!");
                    cont = true;
                }
            } while (cont);
        }
        listBrand.get(searchID(brandID)).setUpdateBrand(brandName, soundBrand, price);
        System.out.println("Update success!");
    }

    public void listBrands() {
        for (int i = 0; i < listBrand.size(); i++) {
            System.out.println(listBrand.get(i));
        }
    }

}
