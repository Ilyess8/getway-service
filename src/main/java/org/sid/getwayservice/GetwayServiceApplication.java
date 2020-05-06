package org.sid.getwayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrix
public class GetwayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetwayServiceApplication.class, args);
	}

	@Bean
	RouteLocator staticRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r
				.path("/publicCountries/**")
						.filters(f -> f
								.addRequestHeader("x-rapidapi-host" , "restcountries-v1.p.rapidapi.com")
									.addRequestHeader("x-rapidapi-key" ,  "688014a7b9msh9665e25aa021de7p1c4542jsn8da8b52daa16")
										.rewritePath("/publicCountries/(?<segment>.*)" , "/${segment}")
											.hystrix(h -> h.setName("countries").setFallbackUri("forward:/defaultCountries"))

						)
						.uri("https://restcountries-v1.p.rapidapi.com").id("r1"))

				.route(r -> r
						.path("/muslim/**")
						.filters(f -> f
								.addRequestHeader("x-rapidapi-host" , "muslimsalat.p.rapidapi.com")
								.addRequestHeader("x-rapidapi-key" ,  "688014a7b9msh9665e25aa021de7p1c4542jsn8da8b52daa16")
								.rewritePath("/muslim/(?<segment>.*)" , "/${segment}")
								.hystrix(h -> h.setName("salat").setFallbackUri("forward:/defaultsalat"))

						)
						.uri("https://muslimsalat.p.rapidapi.com").id("r2"))

				.build() ;


	}

	@Bean
	DiscoveryClientRouteDefinitionLocator DynamicRoutes(ReactiveDiscoveryClient rdc,
														DiscoveryLocatorProperties dlp) {
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp) ;
	}


}


