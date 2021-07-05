ThisBuild / organization := "criterion"
ThisBuild / name         := "criterion-invoice-sync"
ThisBuild / version      := "0.1.0"
ThisBuild / scalaVersion := "2.13.6"
ThisBuild / description  := "Synchronization tool with Quick Books Online invoices information."

lazy val main = Some("com.criterion.quickbooksonline.invoice.Main")

lazy val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core"           % circeVersion,
  "io.circe" %% "circe-parser"         % circeVersion,
  "io.circe" %% "circe-generic"        % circeVersion,
  "io.circe" %% "circe-generic-extras" % circeVersion,
  "com.beachape" %% "enumeratum-circe" % "1.7.0",
  "com.typesafe"                       % "config" % "1.4.1",
  "com.lihaoyi" %% "pprint"            % "0.6.6",
  "org.scalatest" %% "scalatest"       % "3.2.9"  % Test
)

// Fat and executable jar
assembly / mainClass           := main
Compile / run / bloopMainClass := main
assembly / assemblyJarName     := s"${name.value}.jar"

scalacOptions ++= Seq(
  "-encoding",
  "utf-8", // Specify character encoding used by source files.
  "-Ybackend-parallelism",
  "8",
  "-explaintypes", // Explain type errors in more detail.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.

  // ********** -language: Enable or disable language features *****************
  "-language:dynamics", //Allow direct or indirect subclasses of scala.Dynamic
  "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
  "-language:higherKinds", // Allow higher-kinded types
  "-language:implicitConversions", // Allow definition of implicit functions called views
  "-language:postfixOps", // Allow postfix operator notation, such as 1 to 10 toList (not recommended)
  "-language:reflectiveCalls", // Allow reflective access to members of structural types
  "-language:experimental.macros", // Allow macro definition (besides implementation and application)

  // ********** Warning Settings ***********************************************
  "-Werror", // Fail the compilation if there are any warnings.
  "-Wdead-code", //  Warn when dead code is identified.
  "-Wextra-implicit", // Warn when more than one implicit parameter section is defined.
  "-Wmacros:none", // Do not inspect expansions or their original trees when generating unused symbol warnings.
  "-Wmacros:before", // Only inspect unexpanded user-written code for unused symbols. (Default)
  "-Wmacros:after", // Only inspect expanded trees when generating unused symbol warnings.
  "-Wmacros:both", // Inspect both user-written code and expanded trees when generating unused symbol warnings.
  "-Wnumeric-widen", // Warn when numerics are widened.
  "-Woctal-literal", // Warn on obsolete octal syntax.
  "-Wunused:imports", //Warn if an import selector is not referenced.
  "-Wunused:patvars", // Warn if a variable bound in a pattern is unused.
  "-Wunused:privates", // Warn if a private member is unused.
  "-Wunused:locals", // Warn if a local definition is unused.
  "-Wunused:explicits", // Warn if an explicit parameter is unused.
  "-Wunused:implicits", // Warn if an implicit parameter is unused.
  "-Wunused:params", // Enable -Wunused:explicits,implicits.
  "-Wvalue-discard", // Warn when non-Unit expression results are unused.

  // ********** -Xlint: Enable recommended warnings ****************************
  "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
  "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any", // Warn when a type argument is inferred to be Any.
  "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
  "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
  "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
  "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
  "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:option-implicit", // Option.apply used implicit view.
  "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
  "-Xlint:package-object-classes", // Class or object defined in package object.
  "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
  "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:nonlocal-return", // A return statement used an exception for flow control.
  "-Xlint:implicit-not-found", // Check @implicitNotFound and @implicitAmbiguous messages.
  "-Xlint:serial", // @SerialVersionUID on traits and non-serializable classes.
  "-Xlint:valpattern", // Enable pattern checks in val definitions.
  "-Xlint:eta-zero", // Warn on eta-expansion (rather than auto-application) of zero-ary method.
  "-Xlint:eta-sam", // Warn on eta-expansion to meet a Java-defined functional interface that is not explicitly annotated with @FunctionalInterface.
  "-Xlint:deprecation", // Enable linted deprecations.
  "-Xlint:implicit-recursion",
  "-Xfatal-warnings"
)

Compile / javacOptions ++= Seq(
  "-Xlint:unchecked",
  "-Xlint:deprecation"
)

run / javaOptions ++= Seq(
  "-Xms1G",
  "-Xmx4G",
  "-Xss4M",
  "-XX:MaxMetaspaceSize=1G",
  "-Djava.net.preferIPv4Stack=true"
)
