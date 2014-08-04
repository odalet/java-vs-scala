package com.chariotsolutions.jshepard

import java.time.LocalDateTime
import javax.annotation.{PostConstruct, Resource}

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
@EnableAutoConfiguration
@ComponentScan
class Example {
  private var exampleRepository : ExampleRepository = _
  private var exampleSetup : ExampleSetup = _

  @PostConstruct
  def initialize() {
    exampleSetup.initializeData
  }

  @RequestMapping(Array("/"))
  def home() : String = {
    "<h3>Names</h3>" +
    exampleRepository.getAllNames.toString +
    "<h3>First Names</h3>" +
    exampleRepository.getAllFirstNames.toString
  }

  @Resource(name = "exampleRepository")
  def setExampleRepository(exampleRepository : ExampleRepository) {
    this.exampleRepository = exampleRepository
  }

  @Resource(name = "exampleSetup")
  def setExampleSetup(exampleSetup : ExampleSetup) {
    this.exampleSetup = exampleSetup
  }
}

object Example {
  def main(args: Array[String]) {
    val configuration : Array[Object] = Array(classOf[Example])
    SpringApplication.run(configuration, args)
  }
}

