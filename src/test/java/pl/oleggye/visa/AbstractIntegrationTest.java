package pl.oleggye.visa;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        classes = Application.class,
        webEnvironment = RANDOM_PORT
)
@Import(ContainersConfig.class)
public abstract class AbstractIntegrationTest {
    @LocalServerPort
    int port;

    // Define a RequestSpecification instance to be used by test methods
    private RequestSpecification spec;

    @BeforeEach
    void setUpBase() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        spec = given()
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .log().ifValidationFails(); // This enables logging if validation fails
    }

    protected RequestSpecification givenSpec() {
        return spec;
    }
}
