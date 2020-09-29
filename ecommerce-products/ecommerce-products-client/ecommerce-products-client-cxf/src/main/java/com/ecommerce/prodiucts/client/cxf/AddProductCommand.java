package com.ecommerce.prodiucts.client.cxf;

import com.commerce.discount.api.api.Discount;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.WebClient;
import com.ecommerce.products.api.Products;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Service
@Command(scope = "product", name = "add", description = "Add product")
public class AddProductCommand implements Action {

    private List<Discount> discountsList;

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
        Products products = new Products();
        products.setId(id);
        products.setCategory(category);
        products.setName(name);
        products.setDelivery(delivery);
        products.setDescription(description);
        products.setInventory(inventory);
        products.setPaymentOptions(paymentOptions);

        List providers = new ArrayList();
        providers.add(new JacksonJsonProvider());
        WebClient webClient = WebClient.create(restLocation, providers);
        webClient.header("Content-Type", MediaType.APPLICATION_JSON).post(products);

        return null;
    }

}
