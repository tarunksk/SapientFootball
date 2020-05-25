package com.sapient.football.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Component
public class HttpRestClient {
    private String target = "https://apiv2.apifootball.com/";
    private WebTarget webTarget;

    @Autowired
    public HttpRestClient() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target(target).queryParam("APIkey", "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");
    }

    public String call(Map<String, String> queryParams) {
        WebTarget localWebTarget = this.webTarget;

        for(Map.Entry<String, String> entry : queryParams.entrySet()){
            localWebTarget = localWebTarget.queryParam(entry.getKey(), entry.getValue());
        }

        try{
            return localWebTarget.request().get(String.class);
        }catch (Exception e) {
            throw new IllegalStateException("Http call failed", e);
        }
    }
}
