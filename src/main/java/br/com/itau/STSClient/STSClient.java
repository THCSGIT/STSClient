package br.com.itau.STSClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class STSClient {

    @Value("${sts.uri}")
    private String uri;

    @Value("${sts.correlationID}")
    private String correlationID;

    @Value("${sts.flowID}")
    private String flowID;

    @Value("${sts.clientID}")
    private String clientID;

    @Value("${sts.clientSecret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchToken(){
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "client_credentials");
            map.add("client_id", clientID);
            map.add("client_secret", clientSecret);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, createHeaders());
            RestTemplate restTemplate = new RestTemplate();
            STSResponse response = restTemplate.exchange(uri().toUriString(), HttpMethod.POST, request, STSResponse.class).getBody();
            System.out.println("TOKEN OBTIDO COM SUCESSO!");
            assert response != null;
            System.out.println(response.getAccess_token());
            return response.getAccess_token();
        } catch (Exception e) {
            System.out.println("Exception on fetchToken");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

    private HttpHeaders createHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.add("x-itau-correlationID", correlationID);
        httpHeaders.add("x-itau-flowID", flowID);
        return httpHeaders;
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromHttpUrl(uri);
    }
}
