package com.ecommerce.discount.client.cxf;

import com.commerce.discount.api.api.Discount;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Service
@Command(scope = "discount", name = "list", description = "List discount")
public class ListDiscountCommand implements Action {

    @Option(name = "--url", description = "Location of the REST service", required = false, multiValued = false)
    String restLocation = "http://localhost:8181/cxf/discount/";

    @Override
    public Object execute() throws Exception {
        List providers = new ArrayList();
        providers.add(new JacksonJsonProvider());
        WebClient webClient = WebClient.create(restLocation, providers);

        List<Discount> discounts = (List<Discount>) webClient.accept(MediaType.APPLICATION_JSON).getCollection(Discount.class);
        for (Discount discount : discounts) {
            System.out.println(discount.getId() + " " + discount.getStartDate() + " " + discount.getEndDate());
        }

        return null;
    }

}
