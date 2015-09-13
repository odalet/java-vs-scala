package com.chariotsolutions.jshepard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;

import javax.annotation.PostConstruct;

@RestController
@EnableAutoConfiguration
@ComponentScan
public class Example {
	private ExampleRepository exampleRepository;
	private ExampleSetup exampleSetup;

	@PostConstruct
	public void initialize() {
		exampleSetup.initializeData();
	}

	@RequestMapping("/")
	String home() {

		Comparator<Name> ncomp = ((Comparator<Name>) (n1, n2) -> n1.getLastName().compareTo(n2.getLastName()))
				.thenComparing((n1, n2) -> n1.getFirstName().compareTo(n2.getFirstName()));

		return "<h3>Names</h3>"
				+ exampleRepository.getAllNames().stream().sorted(ncomp)
						.map(n -> n.getLastName() + " " + n.getFirstName()).reduce((s, acc) -> s + "<br />" + acc).get()
				+ "<h3>First Names</h3>"
				+ exampleRepository.getAllFirstNames().stream().sorted().reduce((n, acc) -> n + ", " + acc).get();
	}

	@Autowired
	public void setExampleRepository(ExampleRepository exampleRepository) {
		this.exampleRepository = exampleRepository;
	}

	@Autowired
	public void setExampleSetup(ExampleSetup exampleSetup) {
		this.exampleSetup = exampleSetup;
	}

	public static void main(String... args) throws Exception {
		SpringApplication.run(Example.class, args);
	}
}
