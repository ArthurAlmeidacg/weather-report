package ArthurAlmeidaAlves;

import java.util.Scanner;

public class Forecast{
    int[][] rainfall;
    String[] descriptors;

    public Forecast(int[][] rainfall, String[] descriptors) {
        this.rainfall = rainfall;
        this.descriptors = descriptors;
    }

    // TODO
    public int[][] dataPreparation() {

        int value;

        for (int i = 0; i < rainfall.length; i++) {
            for (int j = 0; j < rainfall[0].length; j++) {
                value = rainfall[i][j]; // save the number from the array to cheack if is negativ or not

                //here checks if it is sunny rainy or thunderstorm and corrects the numbers that are negative in the array
                if (value < 0) {
                    if (descriptors[j].equals("sunny")) { // 
                        value = 0;
                    }

                    else if (descriptors[j].equals("rainy")) {
                        int sum = 0;
                        int count = 0;

                        for (int k = 0; k < rainfall.length; k++) {
                            if (rainfall[k][j] >= 0) {
                                sum = sum + rainfall[k][j];
                                count++;
                            }
                        }
                        if (count > 0) {
                            value = sum / count;
                        } else {
                            value = 0;
                        }

                    }

                    else if (descriptors[j].equals("thunderstorm")) { 
                        value = value * (-1);
                    }
                }

                rainfall[i][j] = value;
            }
        }
        return rainfall;
    }

    // TODO
    public int totalRainfall() {
        int sum = 0;
        for (int i = 0; i < rainfall.length; i++) {
            for (int j = 0; j < rainfall[0].length; j++) {
                sum = sum + rainfall[i][j];
            }
        }
        return sum;
    }

    // TODO
    public String trend(int n) {
        int sum = 0;
        int count = 0;
        int median = 0;

        // add the last n numbers
        for (int i = 0; i < rainfall.length; i++) {
            for (int j = n; j < rainfall[0].length; j++) {
                sum += rainfall[i][j];
                count++;
            }
        }
        median = sum / count;
        if (median < 50) {
            return "sunny";
        } else if (median >= 50) {
            return "rainy";
        } else if (median == 75) {
            return "thunderstorm";
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        try (Scanner myObj = new Scanner(System.in)) {
            System.out.println("how many station?");

            int station = myObj.nextInt();
            System.out.println("how many days?");
            int days = myObj.nextInt();
            
            int rainfall[][] = new int[station][days];
            String[] descriptors = {"sunny", "rainy", "thunderstorm", "sunny", "sunny", "thunderstorm", "sunny"};

            //int[][] rainfall = {{-1, 30, -1, 40}, {50, -1, -1, -1}, {60, 70, 80, 90}};
            

            for (int i = 0; i < station; i++) {
                for (int j = 0; j < days; j++) {
                    System.out.println("number from station" + " " + i + " " + "day" + " " + j);
                    rainfall[i][j] = myObj.nextInt();
                }
            }

            Forecast rain = new Forecast(rainfall, descriptors);

            int[][] preparedData = rain.dataPreparation();

            for (int i = 0; i < preparedData.length; i++) {
                for (int j = 0; j < preparedData[0].length; j++) {
                    System.out.print(preparedData[i][j] + "\t");
                }
                System.out.println();
            }

            int totalRainfall = rain.totalRainfall();
            System.out.println("total rain fall " + totalRainfall);

            System.out.println("how many last days do you want to see the average?");
            int n = myObj.nextInt();
            String trend = rain.trend(n);
            System.out.println("Average " + trend);

        }
    }
}

