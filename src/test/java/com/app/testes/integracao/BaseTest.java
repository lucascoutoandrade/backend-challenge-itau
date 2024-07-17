package com.app.testes.integracao;

import java.util.Properties;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.Config;

public class BaseTest {
	private static Properties prop = Config.getProp();

	@BeforeAll
	public static void setUp() {

		RestAssured.baseURI = prop.getProperty("api.baseUri");
		

	}

	@BeforeEach
	public void beforeEachTest() {

		RestAssured.requestSpecification = defaultRequestSpecification();

	}

	@AfterAll
	public static void tearDown() {

		RestAssured.responseSpecification = defaultResponseSpecification();

	}

	protected static RequestSpecification defaultRequestSpecification() {

		return new RequestSpecBuilder()
				.log(LogDetail.ALL)
				.setContentType(ContentType.JSON)
				.build();

	}

	protected static ResponseSpecification defaultResponseSpecification() {

		return new ResponseSpecBuilder()
				.log(LogDetail.ALL)
				.expectResponseTime(Matchers.lessThan(1000l))
				.build();

	}

}
