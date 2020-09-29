package com.ecommerce.discount.client.http;

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
import java.util.Date;

@Service
@Command(scope = "discount", name = "add", description = "Add discount")
public class AddDiscountCommand implements Action {

    @Argument(index = 0, name = "id", description = "Discount ID", required = true, multiValued = false)
    long id;

    @Argument(index = 1, name = "startDate", description = "From date", required = true, multiValued = false)
    Date startDate;

    @Argument(index = 2, name = "endDate", description = "To date", required = true, multiValued = false)
    Date endDate;

    @Argument(index = 3, name = "amount", description = "To date", required = true, multiValued = false)
    Long amount;

    @Option(name = "--url", description = "Location of the REST service", required = false, multiValued = false)
    String restLocation = "http://localhost:8181/cxf/discount/";

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
                + "\"startDate\": \"" + startDate + "\","
                + "\"endDate\": \"" + endDate + "\","
                + "\"amount\": \"" + amount + "\""
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
