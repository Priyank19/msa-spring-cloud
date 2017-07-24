package com.example.itemrec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class RecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendationServiceApplication.class, args);
	}
}
// @Bean
// CommandLineRunner lookup(CountryClient countryClient) {
// return args -> {
// String name = "Spain";
//
// if (args.length > 0) {
// name = args[0];
// }
// GetCountryResponse response = countryClient.getCountry(name);
// System.err.println(response.getCountry().getCapital());
// };
// }

// CommandLineRunner lookup(QuoteClient quoteClient) {
// return args -> {
// String ticker = "MSFT";
//
// if (args.length > 0) {
// ticker = args[0];
// }
// GetQuoteResponse response = quoteClient.getQuote(ticker);
// System.err.println(response.getGetQuoteResult());
// };
