import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;



public class WeatherData{
    private static final String API_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
	
public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nOptions:");
                System.out.println("1. Get weather");
                System.out.println("2. Get Wind Speed");
                System.out.println("3. Get Pressure");
                System.out.println("0. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        getWeather();
                        break;
                    case 2:
                        getWindSpeed();
                        break;
                    case 3:
                        getPressure();
                        break;
                    case 0:
                        System.out.println("Exiting the program.");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getWeather() throws IOException {
        String date = getDateFromUser();
        String response = makeAPIRequest();
        
        double temperature = 0;
        if (temperature != Double.MIN_VALUE) {
            System.out.println("Temperature on " + date + ": " + temperature + " Kelvin");
        }
        else {
        
            System.out.println("Weather data not found for the given date.");
        }
    }
     
     private static void getWindSpeed() throws IOException {
        String date = getDateFromUser();
        String response = makeAPIRequest();

       
        double windspeed = 0;
        if (windspeed != Double.MIN_VALUE) {
            System.out.println("Speed on " + date + ": " + windspeed + " knots ");
        }
        else {
        
            System.out.println("Speed data not found for the given date.");
        }
    }

    private static void getPressure() throws IOException {
        String date = getDateFromUser();
        String response = makeAPIRequest();

       
        double pressure = 0;
        if (pressure != Double.MIN_VALUE) {
            System.out.println("Pressure on " + date + ": " + pressure + " pascal  ");
        }
        else {
        
            System.out.println("Pressure data not found for the given date.");
        }
    }
    private static String getDateFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
        String date = scanner.nextLine();
        return date;
    }

    private static String makeAPIRequest() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        return response.toString();
    }
}
