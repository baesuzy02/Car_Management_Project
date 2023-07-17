package Controller;

import Model.Brand;
import Model.Car;
import java.util.List;
import java.util.Scanner;

public class Validation {

    private static final Scanner sc = new Scanner(System.in);

    public String inputString(String msg) {
        System.out.print(msg);
        while (true) {
            String result = sc.nextLine().trim();
            if (result.length() == 0 || result == null) {
                System.out.println("Can not be blank!");
                System.out.print(msg);
                continue;
            }
            return result;
        }
    }

    public String inputBrandID(String msg, List<Brand> listBrand) {
        boolean exist;
        String id;
        while (true) {
            id = inputString(msg);
            exist = false;
            for (Brand b : listBrand) {
                if (b.getBrandID().equals(id)) {
                    System.out.println("Brand ID is existed. Try another one!");
                    exist = true;
                    break;
                }
                
            }
            if(!exist) break;
        }
        return id;
    }

    public String inputCarID(String msg, List<Car> listCar) {
        boolean exist;
        String id;
        while (true) {
            id = inputString(msg);
            exist = false;
            for (Car c : listCar) {
                if (c.getCarID().equals(id)) {
                    System.out.println("Car ID is existed. Try another one!");
                    exist = true;
                    break;
                }
            }
            if(!exist) break;
        }
        return id;
    }

    public double inputDouble(String msg) throws Exception {
        double input;
        boolean cont = false;
        while (true) {
            System.out.print(msg);
            String input2 = sc.nextLine().trim();
            if (input2.isEmpty()) {
                throw new Exception();
            } else {
                input = Double.parseDouble(input2);
                if (input <= 0) {
                    throw new Exception();
                }
                break;
            }
        }
        return input;
    }

    public String inputFrameId(String msg, List<Car> listCar) {
        String result;
        boolean exist;
        while (true) {
            result = inputString(msg);
            exist = false;
            for (Car c : listCar) {
                if (c.getFrameID().equals(result)) {
                    System.out.println("Frame ID is existed. Try another one!");
                    exist = true;
                    break;
                }
                if (!result.matches("[F]\\d{5}")) {
                    System.out.println("Frame ID  must be in the “F00000” format!");
                    exist = true;
                    break;
                }
            }
            if(!exist) break;
        }
        return result;
    }

    public String inputEngineId(String msg, List<Car> listCar) {
        String result;
        boolean exist;
        while (true) {
            result = inputString(msg);
            exist = false;
            for (Car c : listCar) {
                if (c.getEngineID().equals(result)) {
                    System.out.println("Engine ID is existed. Try another one!");
                    exist = true;
                    break;
                }
                if (!result.matches("[E]\\d{5}")) {
                    System.out.println("Frame ID  must be in the “E00000” format!");
                    exist = true;
                    break;
                }
            }
            if(!exist) break;
        }
        return result;
    }
}
