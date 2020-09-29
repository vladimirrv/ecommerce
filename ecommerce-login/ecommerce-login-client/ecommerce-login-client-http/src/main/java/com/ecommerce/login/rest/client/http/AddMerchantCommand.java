package com.ecommerce.login.rest.client.http;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

@Service
@Command(scope = "merchant", name = "add", description = "Add merchant")
public class AddMerchantCommand implements Action {

    @Argument(index = 0, name = "id", description = "Merchant ID", required = true, multiValued = false)
    long id;

    @Argument(index = 1, name = "merchantType", description = "Merchant Type", required = true, multiValued = false)
    String merchantType;

    @Argument(index = 2, name = "merchantName", description = "Merchant name", required = true, multiValued = false)
    String merchantName;

    @Argument(index = 3, name = "ownerName", description = "Merchant owner name", required = true, multiValued = false)
    String ownerName;

    @Argument(index = 4, name = "address", description = "Merchant address", required = true, multiValued = false)
    String address;

    @Argument(index = 5, name = "phoneNumber", description = "Merchant phone number", required = true, multiValued = false)
    String phoneNumber;

    @Argument(index = 6, name = "emailAddress", description = "Merchant email address", required = true, multiValued = false)
    String emailAddress;

    @Argument(index = 7, name = "password", description = "Merchant password", required = true, multiValued = false)
    String password;

    @Option(name = "--url", description = "Location of the REST service", required = false, multiValued = false)
    String restLocation = "http://localhost:8181/cxf/login/";

    @Override
    public Object execute() throws Exception {
        URL url = new URL(restLocation);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        String json = "{"
                + "\"id\": " + id + ","
                + "\"merchantType\": \"" + merchantType + "\","
                + "\"merchantName\": \"" + merchantName + "\","
                + "\"ownerName\": \"" + ownerName + "\","
                + "\"address\": \"" + address + "\","
                + "\"phoneNumber\": \"" + phoneNumber + "\","
                + "\"emailAddress\": \"" + emailAddress + "\","
                + "\"password\": \"" + password + "\""
                + "}";

        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(json);
        writer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();

        return null;
    }

}
