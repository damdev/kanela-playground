import sbt.Tests.{Group, SubProcess}

name := "kanela-playground"

organization := "com.github.damdev.kanela"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "io.kamon"  %%  "kanela-scala-extension"  % "0.0.14",
  "io.kamon" % "kanela-agent" % "0.0.15",
  "com.ea.agentloader" % "ea-agent-loader" % "1.0.3"

)

fork in Test := true

enablePlugins(JavaAgent)

// javaAgents += "io.kamon" % "kanela-agent" % "0.0.15" % "compile;test"

libraryDependencies += "com.lihaoyi" %% "utest" % "0.6.5" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")

// Define a method to group tests, in my case a single test per group
def singleTests(tests: Seq[TestDefinition]) =
  tests map { test =>
    new Group(
      name = test.name,
      tests = Seq(test),
      runPolicy = SubProcess(config = ForkOptions()))
  }

// Add the following to the `Project` settings
testGrouping in Test := singleTests((definedTests in Test).value)