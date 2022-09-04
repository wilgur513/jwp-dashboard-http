package nextstep.jwp.controller;

import org.apache.coyote.http11.HttpRequest;
import org.apache.coyote.http11.HttpResponse;

public class HomeController {

    public HttpResponse handle(final HttpRequest request) {
        return HttpResponse.ok()
                .textBody("Hello world!")
                .build();
    }
}
