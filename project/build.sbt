lazy val root = (project in file(".")).
  settings(
    libraryDependencies ++= Seq(
      "org.codehaus.groovy" % "groovy-all" % "2.4.3",
      "info.cukes" % "gherkin" % "2.12.2"
    )
  )
