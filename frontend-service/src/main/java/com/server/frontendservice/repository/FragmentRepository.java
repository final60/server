package com.server.frontendservice.repository;

import com.server.common.model.Fragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
public class FragmentRepository extends BaseRepository
{
    private static final String GET_ALL = "fragments/get-all";
    private static final String GET_ALL_BY_URI = "fragments/get-all-by-uri/";
    private static final String GET_BY_ID = "fragments/get-by-id/";
    private static final String UPDATE = "fragments/update";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    @Async("asyncExecutor")
    public CompletableFuture<List<Fragment>> getAll(final String uri) {

        ResponseEntity<List<Fragment>> res = template.exchange(baseUri + GET_ALL_BY_URI + uri, GET, getEntity(),
                new ParameterizedTypeReference<List<Fragment>>() {});

        return CompletableFuture.completedFuture(res.getBody());
    }

    public CompletableFuture<List<Fragment>> getAll() {

        ResponseEntity<List<Fragment>> res = template.exchange(baseUri + GET_ALL, GET, getEntity(),
                new ParameterizedTypeReference<List<Fragment>>() {});

        return CompletableFuture.completedFuture(res.getBody());
    }

    public Fragment get(final Long id) {

        ResponseEntity<Fragment> res = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), Fragment.class);
        return res.getBody();
    }

    public void update(Fragment fragment) {

        template.exchange(baseUri + UPDATE, POST, postJson(fragment), Fragment.class);
    }
}
