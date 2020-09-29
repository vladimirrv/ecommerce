package com.ecommerce.products.client.http;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Command(scope = "product", name = "add", description = "Add product")
public class AddProductCommand implements Action {

    @Argument(index = 0, name = "id", description = "Product ID", required = true, multiValued = false)
    long id;

    @Argument(index = 1, name = "name", description = "Product name", required = true, multiValued = false)
    String name;

    @Argument(index = 2, name = "description", description = "Product description", required = true, multiValued = false)
    String description;

    @Argument(index = 3, name = "inventory", description = "Product description", required = true, multiValued = false)
    Long inventory;

    @Argument(index = 4, name = "delivery", description = "Product description", required = true, multiValued = false)
    String delivery;

    @Argument(index = 5, name = "category", description = "Product description", required = true, multiValued = false)
    String category;

    @Argument(index = 6, name = "paymentOptions", description = "Product description", required = true, multiValued = false)
    String paymentOptions;

    @Option(name = "--url", description = "Location of the REST service", required = false, multiValued = false)
    String restLocation = "http://localhost:8181/cxf/product/";

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
                + "\"name\": \"" + name + "\","
                + "\"description\": \"" + description + "\","
                + "\"inventory\": \"" + inventory + "\","
                + "\"delivery\": \"" + delivery + "\","
                + "\"category\": \"" + category + "\","
                + "\"paymentOptions\": \"" + paymentOptions + "\""
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
