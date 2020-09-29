package org.ecommerce.login.blueprint;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ecommerce.login.api.Merchant;
import com.ecommerce.login.api.MerchantService;
import com.ecommerce.login.api.SignIn;

@Path("/")
public class MerchantServiceRest implements MerchantService {
    
    private final Map<Long, Merchant> merchants = new HashMap<>();

    @Override
    @Path("/")
    @Produces("application/json")
    @GET
    public Collection<Merchant> list() {
        return merchants.values();
    }

    @Override
    @Path("/{id}")
    @Produces("application/json")
    @GET
    public Merchant get(@PathParam("id") Long id) {
        return merchants.get(id);
    }
    
    @Override
    @Path("/")
    @Consumes("application/json")
    @POST
    public void add(Merchant merchant) {
        merchants.put(merchant.getMerchantType(), merchant);
    }

    @Override
    @Path("/")
    @Consumes("application/json")
    @PUT
    public void update(Merchant merchant) {
        merchants.remove(merchant.getMerchantType());
        merchants.put(merchant.getMerchantType(), merchant);
    }

    @Override
    @Path("/{id}")
    @DELETE
    public void remove(@PathParam("id") Long id) {
        merchants.remove(id);
    }

    @Override
    @Path("/")
    @Consumes("application/json")
    @POST
    public void signIn(SignIn signIn) {

    }
}
