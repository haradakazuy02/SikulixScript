lazy val root = (project in file(".")).settings(
 organization := "jp.gr.java_conf.harada",
 name := "sikulixscript",
 version := "0.1-SNAPSHOT",
 scalaVersion := "2.11.8",
 scalacOptions ++= Seq("-encoding", "MS932"),
 libraryDependencies ++= {
  val osname = System.getProperty("os.name").toLowerCase;
  val sikulixlib = if (osname.contains("win")) "sikulixlibswin" else if (osname.contains("mac")) "sikulixlibsmac" else "sikulixlibslux";
  Seq("com.sikulix" % "sikulixapi" % "1.1.0" intransitive(),
  "com.sikulix" % sikulixlib % "1.1.0",
  "commons-cli" % "commons-cli" % "1.3.1",
  "com.nativelibs4java" % "bridj" % "0.7.0",
  "jp.gr.java_conf.harada" %% "scriptshell" % "0.2-SNAPSHOT")},
 packAutoSettings
)
