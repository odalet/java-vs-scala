package com.chariotsolutions.jshepard

import java.time.LocalDateTime
import javax.annotation.{ PostConstruct, Resource }

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.{ RequestMapping, RestController }

@RestController
@EnableAutoConfiguration
@ComponentScan
class Example {
  private var exampleRepository: ExampleRepository = _
  private var exampleSetup: ExampleSetup = _

  @PostConstruct
  def initialize() {
    exampleSetup.initializeData
  }

  @RequestMapping(Array("/"))
  def home(): String = {
    "<h3>Names</h3>" + exampleRepository
      .getAllNames
      .sortBy { n => (n.lastName, n.firstName) }
      .map { n => n.lastName + " " + n.firstName }
      .reduce { (n, acc) => n + "<br />" + acc } +
      "<h3>First Names</h3>" + exampleRepository
      .getAllFirstNames
      .sortBy { s => s }
      .reduce { (n, acc) => n + ", " + acc }
  }

  @Resource(name = "exampleRepository")
  def setExampleRepository(repository: ExampleRepository) {
    exampleRepository = repository
  }

  @Resource(name = "exampleSetup")
  def setExampleSetup(setup: ExampleSetup) {
    exampleSetup = setup
  }
}

object Example {
  def main(args: Array[String]) {
    val configuration: Array[Object] = Array(classOf[Example])
    SpringApplication.run(configuration, args)
  }
}

