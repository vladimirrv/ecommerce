package com.ecommerce.prodiucts.client.cxf;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.WebClient;
import com.ecommerce.products.api.Products;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Service
@Command(scope = "product", name = "list", description = "List products")
public class ListProductCommand implements Action {

    @Option(name = "--url", description = "Location of the REST service", required = false, multiValued = false)
    String restLocation = "http://localhost:8181/cxf/product/";

    @Override
    public Object execute() throws Exception {
        List providers = new ArrayList();
        providers.add(new JacksonJsonProvider());
        WebClient webClient = WebClient.create(restLocation, providers);

        List<Products> products = (List<Products>) webClient.accept(MediaType.APPLICATION_JSON).getCollection(Products.class);
        for (Products product : products) {
            System.out.println(product.getId() + " " + product.getName() + " " + product.getCategory());
        }

        return null;
    }

}
