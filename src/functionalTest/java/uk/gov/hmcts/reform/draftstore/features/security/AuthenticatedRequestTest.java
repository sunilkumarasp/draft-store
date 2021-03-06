package uk.gov.hmcts.reform.draftstore.features.security;

import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource("classpath:application.properties")
@RunWith(SpringRunner.class)
public class AuthenticatedRequestTest {

    @Value("${draft-store-url}")
    private String draftStoreUrl;

    @Test
    public void should_reject_request_when_missing_required_headers() {
        RestAssured
            .given()
            .log().all()
            .relaxedHTTPSValidation()
            .baseUri(draftStoreUrl)
            .basePath("/drafts")
            .queryParam("type", "default")
            .when()
            .get()
            .then()
            .assertThat()
            .statusCode(400);
    }
}
