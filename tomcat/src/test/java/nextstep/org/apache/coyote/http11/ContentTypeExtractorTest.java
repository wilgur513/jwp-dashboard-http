package nextstep.org.apache.coyote.http11;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.coyote.http11.ContentTypeExtractor;
import org.apache.coyote.http11.HttpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ContentTypeExtractorTest {

    @DisplayName("Accept 헤더를 통해 Content Type 유추")
    @Test
    void extractFromAcceptHeader() {
        // arrange
        final String request= String.join("\r\n",
                "GET /css/styles.css HTTP/1.1 ",
                "Host: localhost:8080 ",
                "Accept: text/css,*/*;q=0.1 ",
                "Connection: keep-alive ",
                "",
                "");
        ContentTypeExtractor extractor = new ContentTypeExtractor();

        // act
        String actual = extractor.extract(HttpRequest.from(request));

        // assert
        assertThat(actual).isEqualTo("text/css");
    }

    @ParameterizedTest
    @CsvSource({"/index.html, text/html", "/css/styles.css, text/css", "/main.js, text/javascript"})
    void extractFromExtension(String uri, String expectContentType) {
        // arrange
        final String request= String.join("\r\n",
                "GET " + uri + " HTTP/1.1 ",
                "Host: localhost:8080 ",
                "Connection: keep-alive ",
                "",
                "");
        ContentTypeExtractor extractor = new ContentTypeExtractor();

        // act
        String actual = extractor.extract(HttpRequest.from(request));

        // assert
        assertThat(actual).isEqualTo(expectContentType);
    }
}
