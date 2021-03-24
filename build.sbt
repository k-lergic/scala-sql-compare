import sbt._
import Keys._

name := "scala-sql-compare"

lazy val commonSettings = Seq(
  organization := "com.softwaremill",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.13.5"
)

lazy val scalaSqlCompare = (project in file("."))
  .settings(commonSettings)
  .aggregate(slick, doobie, quill, scalikejdbc)

lazy val common = (project in file("common"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.postgresql" % "postgresql" % "42.2.9",
      "org.flywaydb" % "flyway-core" % "7.7.1")
  )

lazy val slick = (project in file("slick"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.3.3"
    )
  )
  .dependsOn(common)

lazy val doobie = (project in file("doobie"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.tpolecat" %% "doobie-postgres" % "0.12.1"
    )
  )
  .dependsOn(common)

lazy val quill = (project in file("quill"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "io.getquill" %% "quill-async-postgres" % "3.7.0"
    )
  )
  .dependsOn(common)

lazy val scalikejdbc = (project in file("scalikejdbc"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalikejdbc" %% "scalikejdbc" % "3.5.0",
      "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % "3.5.0",
      "ch.qos.logback" % "logback-classic" % "1.2.3"
    )
  )
  .dependsOn(common)
