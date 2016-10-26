lazy val root = (project in file(".")).settings(
 organization := "jp.gr.java_conf.harada",
 name := "sikulixscript",
 version := "0.1-SNAPSHOT",
 scalaVersion := "2.11.8",
 scalacOptions ++= Seq("-encoding", "MS932"),
 libraryDependencies ++= {
  val osname = System.getProperty("os.name").toLowerCase;
  val sikulixlib = if (osname.contains("win")) "sikulixlibswin" else if (osname.contains("mac")) "sikulixlibsmac" else "sikulixlibslux";
  val seleniumversion = "3.0.0";
  Seq("com.sikulix" % "sikulixapi" % "1.1.0" intransitive(),
  "com.sikulix" % sikulixlib % "1.1.0",
  "org.robotframework" % "robotframework" % "3.0",
  "org.seleniumhq.selenium" % "selenium-java" % seleniumversion,
  "org.seleniumhq.selenium" % "selenium-remote-driver" % seleniumversion,
  "org.seleniumhq.selenium" % "selenium-firefox-driver" % seleniumversion,
  "org.seleniumhq.selenium" % "selenium-ie-driver" % seleniumversion,
  "org.seleniumhq.selenium" % "selenium-chrome-driver" % seleniumversion,
  "org.seleniumhq.selenium" % "selenium-support" % seleniumversion,
  "commons-cli" % "commons-cli" % "1.3.1",
  "com.nativelibs4java" % "bridj" % "0.7.0",
  "jp.gr.java_conf.harada" %% "scriptshell" % "0.2-SNAPSHOT")},
 packAutoSettings
)
