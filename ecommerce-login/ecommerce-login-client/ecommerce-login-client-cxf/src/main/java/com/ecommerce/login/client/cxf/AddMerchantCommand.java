package com.ecommerce.login.client.cxf;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.WebClient;
import com.ecommerce.login.api.Merchant;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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
        Merchant merchant = new Merchant();
        merchant.setMerchantType(merchantType);
        merchant.setMerchantName(merchantName);
        merchant.setOwnerName(ownerName);
        merchant.setAddress(address);
        merchant.setPhoneNumber(phoneNumber);
        merchant.setEmailAddress(emailAddress);
        merchant.setPassword(password);


        List providers = new ArrayList();
        providers.add(new JacksonJsonProvider());
        WebClient webClient = WebClient.create(restLocation, providers);
        webClient.header("Content-Type", MediaType.APPLICATION_JSON).post(merchant);

        return null;
    }

}
