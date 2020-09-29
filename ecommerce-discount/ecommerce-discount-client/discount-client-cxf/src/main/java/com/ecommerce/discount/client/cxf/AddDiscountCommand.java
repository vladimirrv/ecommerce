package com.ecommerce.discount.client.cxf;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.WebClient;
import com.commerce.discount.api.api.Discount;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Command(scope = "discount", name = "add", description = "Add discount")
public class AddDiscountCommand implements Action {

    @Argument(index = 0, name = "id", description = "Discount ID", required = true, multiValued = false)
    long id;

    @Argument(index = 1, name = "startDate", description = "From date", required = true, multiValued = false)
    Date startDate;

    @Argument(index = 2, name = "endDate", description = "To date", required = true, multiValued = false)
    Date endDate;

    @Argument(index = 3, name = "amount", description = "Amount", required = true, multiValued = false)
    Long amount;

    @Option(name = "--url", description = "Location of the REST service", required = false, multiValued = false)
    String restLocation = "http://localhost:8181/cxf/discount/";

    @Override
    public Object execute() throws Exception {
        Discount discount = new Discount();
        discount.setId(id);
        discount.setEndDate(endDate);
        discount.setStartDate(startDate);
        discount.setAmount(amount);

        List providers = new ArrayList();
        providers.add(new JacksonJsonProvider());
        WebClient webClient = WebClient.create(restLocation, providers);
        webClient.header("Content-Type", MediaType.APPLICATION_JSON).post(discount);

        return null;
    }

}
