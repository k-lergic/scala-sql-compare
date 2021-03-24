package com.softwaremill.sql

import org.flywaydb.core.Flyway
import org.flywaydb.core.api.configuration.FluentConfiguration

trait DbSetup {
  val connectionString = "jdbc:postgresql:postgres"

  def dbSetup(): Unit = {
    val flyway = new Flyway(new FluentConfiguration().dataSource(connectionString, "postgres", "docker"))
    flyway.clean()
    flyway.migrate()
  }
}
