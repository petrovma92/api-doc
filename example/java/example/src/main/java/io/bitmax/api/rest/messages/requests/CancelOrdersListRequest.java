package io.bitmax.api.rest.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelOrdersListRequest {

    @JsonProperty("orders")
    private CancelOrderRequest[] orders;

    public CancelOrderRequest[] getOrders() {
        return orders;
    }

    public void setOrders(CancelOrderRequest[] orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "CancelOrdersList:\n\torders: " + Arrays.toString(orders);
    }
}
