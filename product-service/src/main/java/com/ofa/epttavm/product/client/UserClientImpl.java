package com.ofa.epttavm.product.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ofa.epttavm.product.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClientImpl implements UserClient {

    private final RestTemplate restTemplate;
    private final String appHost;

    @Autowired
    public UserClientImpl(RestTemplate restTemplate, @Value("${app.host}") String appHost) {
        this.restTemplate = restTemplate;
        this.appHost = appHost;
    }

    @HystrixCommand(fallbackMethod = "defaultUserDetail")
    @Override
    public User retrieveUserDetailsByUsername(String username) {
        return restTemplate.getForObject(appHost + "user/" + username, User.class);
    }

    private User defaultUserDetail(final String username){
        return new User("fallback", null, null, false, null);
    }
}
