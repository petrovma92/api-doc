package io.bitmax.api.rest.client;

import io.bitmax.api.Authorization;
import io.bitmax.api.Mapper;
import io.bitmax.api.rest.messages.requests.CancelOrderRequest;
import io.bitmax.api.rest.messages.responses.CancelOrderResponse;
import io.bitmax.api.rest.messages.responses.OpenOrdersList;
import io.bitmax.api.rest.messages.responses.OrderDetails;
import io.bitmax.api.rest.messages.responses.UserInfo;
import okhttp3.*;

import java.util.Map;

public class BitMaxRestApiClientAccount extends BitMaxRestApiClient {

    private Authorization authClient;
    private int accountGroup;

    public BitMaxRestApiClientAccount(String apiKey, String secret, String baseUrl) {
        authClient = new Authorization(baseUrl, apiKey, secret);
        client = new OkHttpClient();
        accountGroup = getUserInfo().getAccountGroup();
    }

    public UserInfo getUserInfo() {
        Map<String, String> headers = authClient.getHeaderMap(PATH_INFO, System.currentTimeMillis());

        Request.Builder builder = new Request.Builder()
                .url(URL + API + PATH_INFO)
                .get();

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }

        try {
            Response response = client.newCall(builder.build()).execute();

            return Mapper.asObject(response.body().string(), UserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public OpenOrdersList getOpenOrders() {
        Map<String, String> headers = authClient.getHeaderMap(PATH_ORDERS, System.currentTimeMillis());

        Request.Builder builder = new Request.Builder()
                .url(URL + accountGroup + '/' + API + PATH_ORDERS)
                .get();

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }

        try {
            Response response = client.newCall(builder.build()).execute();

            return Mapper.asObject(response.body().string(), OpenOrdersList.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public OrderDetails getOrder(String coid) {
        Map<String, String> headers = authClient.getHeaderMap(PATH_ORDER, System.currentTimeMillis());

        Request.Builder builder = new Request.Builder()
                .url(URL + accountGroup + '/' + API + PATH_ORDER + '/' + coid)
                .get();

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }

        try {
            Response response = client.newCall(builder.build()).execute();

            return Mapper.asObject(response.body().string(), OrderDetails.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public CancelOrderResponse cancelOrder(CancelOrderRequest order) {
        Map<String, String> headers = authClient.getHeaderMap(PATH_ORDER, System.currentTimeMillis());

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                Mapper.asString(order)
        );

        Request.Builder builder = new Request.Builder()
                .url(URL + accountGroup + '/' + API + PATH_ORDER)
                .delete(body);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }

        try {
            Response response = client.newCall(builder.build()).execute();

            return Mapper.asObject(response.body().string(), CancelOrderResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
