package com.ecommerce.login.rest.client.http;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Command(scope = "merchant", name = "list", description = "List merchant")
public class ListMerchantCommand implements Action {

    @Option(name = "--url", description = "Location of the REST service", required = false, multiValued = false)
    String restLocation = "http://localhost:8181/cxf/login/";

    @Override
    public Object execute() throws Exception {
        URL url = new URL(restLocation);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = buffer.readLine())!= null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
        } else {
            System.err.println("Error when sending GET method : HTTP_CODE = " + connection.getResponseCode());
        }
        return null;
    }

}
