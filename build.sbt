sbtPlugin := true

name := "sbt-man"

organization := "com.eed3si9n"

version := "0.1.1"

sbtVersion in Global := "0.13.0-RC3" 

scalaVersion in Global := "2.10.2" 

description := "sbt plugin to look up scaladoc"

libraryDependencies ++= Seq("net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
  "net.databinder.dispatch" %% "dispatch-json4s-native" % "0.11.0",
  "org.slf4j" % "slf4j-nop" % "1.6.2")

scalacOptions := Seq("-deprecation", "-unchecked")

publishArtifact in (Compile, packageBin) := true

publishArtifact in (Test, packageBin) := false

publishArtifact in (Compile, packageDoc) := false

publishArtifact in (Compile, packageSrc) := false

publishMavenStyle := false

publishTo <<= (version) { version: String =>
   val scalasbt = "http://scalasbt.artifactoryonline.com/scalasbt/"
   val (name, u) = if (version.contains("-SNAPSHOT")) ("scalasbt-plugin-snapshots", scalasbt+"sbt-plugin-snapshots")
                   else ("scalasbt-plugin-releases", scalasbt+"sbt-plugin-releases")
   Some(Resolver.url(name, url(u))(Resolver.ivyStylePatterns))
}

credentials += Credentials(Path.userHome / ".ivy2" / ".sbtcredentials")

// lsSettings

// LsKeys.tags in LsKeys.lsync := Seq("sbt", "manual")

// externalResolvers in LsKeys.lsync := Seq(
//   "sbt-plugin-releases" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases")
