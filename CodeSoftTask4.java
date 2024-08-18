/*                          CODSOFT TASK 4 ***CURRENCY CONVERTER***
_______________________________________________________________________________________
-Currency Selection: Allow the user to choose the base currency and the target currency.
-Currency Rates: Fetch real-time exchange rates from a reliable API.
-Amount Input: Take input from the user for the amount they want to convert.
-Currency Conversion: Convert the input amount from the base currency to the
-target currency using the fetched exchange rate.
-Display Result: Show the converted amount and the target currency symbol to the user. */

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CodeSoftTask4 {
    private static final String API_KEY = "YOUR_API_KEY"; // Replace with your API key
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Available currencies
        System.out.println("Available currencies: USD, EUR, GBP, JPY, AUD, CAD, CHF, CNY, SEK, NZD");

        // Get base currency from user
        System.out.print("Enter the base currency: ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        // Get target currency from user
        System.out.print("Enter the target currency: ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Get amount from user
        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            // Fetch exchange rates
            JSONObject exchangeRates = fetchExchangeRates(baseCurrency);

            if (exchangeRates != null && exchangeRates.has(targetCurrency)) {
                // Get the exchange rate for the target currency
                double exchangeRate = exchangeRates.getDouble(targetCurrency);

                // Convert the amount
                double convertedAmount = amount * exchangeRate;

                // Display the result
                System.out.printf("Converted amount: %.2f %s\n", convertedAmount, targetCurrency);
            } else {
                System.out.println("Invalid currency or exchange rate not available.");
            }
        } catch (IOException e) {
            System.out.println("Error fetching exchange rates: " + e.getMessage());
        }

        scanner.close();
    }

    private static JSONObject fetchExchangeRates(String baseCurrency) throws IOException {
        String urlStr = API_URL + baseCurrency + "?access_key=" + API_KEY;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream responseStream = connection.getInputStream();
        Scanner scanner = new Scanner(responseStream);
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        JSONObject jsonResponse = new JSONObject(response);
        return jsonResponse.getJSONObject("rates");
    }
}
