lazy val cucumberVersion = "1.2.3-SNAPSHOT"

def generateI18nSources(base: File): Seq[File] = {
  import groovy.text.SimpleTemplateEngine
  val engine = new SimpleTemplateEngine()
  val templateSource = root.base / "src" / "main" / "code_generator" / "I18n.scala.txt"

  val template = engine.createTemplate(templateSource).make(null)
  val output = base / "i18n" / "cucumber" / "api" / "scala" / "I18n.scala"
  output.getParentFile.mkdirs()
  IO.write(output, template.toString())

  Seq(output)
}

lazy val root = (project in file(".")).
  settings(
    resolvers += Resolver.mavenLocal,
    name := "cucumber-scala",
    version := cucumberVersion,
    scalaVersion := "2.11.4",
    crossScalaVersions := Seq("2.10.4", "2.11.6", "2.12.0-M1"),
    sourceGenerators in Compile += Def.task {
      generateI18nSources((sourceManaged in Compile).value)
    }.taskValue,
    scalacOptions ++= Seq("-feature", "-deprecation"),
    libraryDependencies ++= Seq(
      "info.cukes" % "cucumber-core" % cucumberVersion,
      "info.cukes" % "cucumber-junit" % cucumberVersion % "test",
      "junit" % "junit" % "4.11",
      "com.novocode" % "junit-interface" % "0.11" % Test
    )
  )
