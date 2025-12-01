package com.assignment.hrx;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@SpringBootApplication
public class HrxApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrxApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(WebClient webClient) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {

                String name = "Lakshaya Jain";
                String regNo = "22BCE11253";
                String email = "lakshayajain93@gmail.com";

                String generateUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

                System.out.println("Calling generateWebhook...");

                Map<String, String> body = Map.of(
                        "name", name,
                        "regNo", regNo,
                        "email", email
                );

                // Parse REAL response
                GenerateResponse response = webClient.post()
                        .uri(generateUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(GenerateResponse.class)
                        .block();

                if (response == null) {
                    System.err.println("generateWebhook returned NULL.");
                    return;
                }

                String webhook = response.getWebhook();
                String token = response.getAccessToken();

                System.out.println("Webhook = " + webhook);
                System.out.println("Access Token = " + token);

                // Read SQL file
                String finalQuery = Files.readString(Path.of("finalQuery.sql"));

                Map<String, String> submissionBody = Map.of(
                        "regNo", regNo,
                        "finalQuery", finalQuery
                );

                System.out.println("Submitting finalQuery...");

                // Capture status + body
                String submitResult = webClient.post()
                        .uri(webhook)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(submissionBody)
                        .exchangeToMono(resp ->
                                resp.bodyToMono(String.class).map(
                                        b -> "STATUS=" + resp.statusCode().value() + " BODY=" + b
                                )
                        )
                        .block();

                System.out.println("Submission Response: " + submitResult);
            }
        };
    }
}
