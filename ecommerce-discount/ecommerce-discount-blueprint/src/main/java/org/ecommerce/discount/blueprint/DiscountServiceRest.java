package org.ecommerce.discount.blueprint;

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

import com.commerce.discount.api.api.Discount;
import com.commerce.discount.api.api.DiscountService;

@Path("/")
public class DiscountServiceRest implements DiscountService {
    
    private final Map<Long, Discount> bookings = new HashMap<>();

    @Override
    @Path("/")
    @Produces("application/json")
    @GET
    public Collection<Discount> list() {
        return bookings.values();
    }

    @Override
    @Path("/{id}")
    @Produces("application/json")
    @GET
    public Discount get(@PathParam("id") Long id) {
        return bookings.get(id);
    }
    
    @Override
    @Path("/")
    @Consumes("application/json")
    @POST
    public void add(Discount discount) {
        bookings.put(discount.getId(), discount);
    }

    @Override
    @Path("/")
    @Consumes("application/json")
    @PUT
    public void update(Discount discount) {
        bookings.remove(discount.getId());
        bookings.put(discount.getId(), discount);
    }

    @Override
    @Path("/{id}")
    @DELETE
    public void remove(@PathParam("id") Long id) {
        bookings.remove(id);
    }
}
