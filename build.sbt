import AssemblyKeys._

assemblySettings

name := "ToreeExt"

version := "0.1"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.2" % "provided"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.2" % "provided"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

resolvers += "Cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/"

resolvers += "Maven" at "http://repo1.maven.org/maven2/"

testOptions in Test += Tests.Argument("-oF")

fork in Test := true

parallelExecution in Test := false

assembleArtifact in packageScala := false

val meta = """META.INF(.)*""".r

val lucene = """META.INF/services/org.apache.lucene.codecs.Cod(.)*""".r

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
    case PathList("javax", "xml", xs @ _*) => MergeStrategy.last
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("org", "objectweb", xs @ _*) => MergeStrategy.last
    case PathList("org", "w3c", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
    case PathList("plugin.properties") => MergeStrategy.last
    case lucene(_) => MergeStrategy.last
    case meta(_) => MergeStrategy.discard
    case x => MergeStrategy.last
  }
}
