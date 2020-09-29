package com.ecommerce.login.client.cxf;

import com.ecommerce.login.api.Merchant;
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
@Command(scope = "merchant", name = "list", description = "List merchant")
public class ListMerchantCommand implements Action {

    @Option(name = "--url", description = "Location of the REST service", required = false, multiValued = false)
    String restLocation = "http://localhost:8181/cxf/login/";

    @Override
    public Object execute() throws Exception {
        List providers = new ArrayList();
        providers.add(new JacksonJsonProvider());
        WebClient webClient = WebClient.create(restLocation, providers);

        List<Merchant> merchants = (List<Merchant>) webClient.accept(MediaType.APPLICATION_JSON).getCollection(Merchant.class);
        for (Merchant merchant : merchants) {
            System.out.println(merchant.getMerchantType() + " " + merchant.getMerchantName() + " " + merchant.getOwnerName());
        }

        return null;
    }

}
