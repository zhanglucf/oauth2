package com.example.oauth.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.*;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
public class MyClientDetailsService implements ClientDetailsService {

    private static final Map<String, ClientDetails> ClientDetailsMap = new HashMap<>();

    static {

        ClientDetails clientDetails = new BaseClientDetails();
        List<String> scope = new ArrayList<>();
        scope.add("SCOPE_ALL");
        ((BaseClientDetails) clientDetails).setClientId("admin");
        ((BaseClientDetails) clientDetails).setClientSecret("admin");
        ((BaseClientDetails) clientDetails).setScope(scope);

        ClientDetails clientDetails2 = new BaseClientDetails();
        ((BaseClientDetails) clientDetails2).setClientId("app_id");
        ((BaseClientDetails) clientDetails2).setClientSecret("app_id");
        ((BaseClientDetails) clientDetails2).setScope(scope);

        ClientDetails clientDetails3 = new BaseClientDetails();
        ((BaseClientDetails) clientDetails3).setClientId("zzh");
        ((BaseClientDetails) clientDetails3).setClientSecret("zzh");
        ((BaseClientDetails) clientDetails3).setScope(scope);

        ClientDetailsMap.put("admin", clientDetails);
        ClientDetailsMap.put("app_id", clientDetails2);
        ClientDetailsMap.put("zzh", clientDetails3);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return ClientDetailsMap.get(clientId);
    }
}
