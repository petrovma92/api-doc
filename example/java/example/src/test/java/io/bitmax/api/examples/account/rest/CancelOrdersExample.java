package io.bitmax.api.examples.account.rest;

import io.bitmax.api.rest.client.BitMaxRestApiClientAccount;
import io.bitmax.api.rest.messages.requests.CancelOrderRequest;

public class CancelOrdersExample {
    public static void main(String[] args) {
        String apiKey = "<apikey>";
        String secret = "<secret>";
        String baseUrl = "https://bitmax.io";

        BitMaxRestApiClientAccount restClient = new BitMaxRestApiClientAccount(apiKey, secret, baseUrl);

        CancelOrderRequest orderRequest = new CancelOrderRequest();

        long time = System.currentTimeMillis();

        orderRequest.setTime(time);
        orderRequest.setCoid("coid_" + time);
        orderRequest.setOrigCoid("<cancel_order_coid>");
        orderRequest.setSymbol("EOS/ETH");

        System.out.println(restClient.cancelOrder(orderRequest));
    }
}
