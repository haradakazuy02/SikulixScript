package jp.gr.java_conf.harada;
import scala.collection.convert.WrapAsScala._
import scala.collection._
import jp.gr.java_conf.harada._
import java.io._
import org.sikuli.script._

object SikulixScript {
  def main(args:Array[String]) {
      var scriptfile : File = null;
      var interpreter = false;
      var inits : List[String] = Nil;
      var n = args.size;
      var skip = 0;
      for (i<-0 until args.size if n == args.size) {
        if (skip > 0) skip -= 1 else if (args(i).charAt(0) != '-') n = i else args(i) match {
          case "-i" => interpreter = true;
          case "-init" => skip = 1; inits :+= args(i+1);
          case "-script" => skip = 1; scriptfile = new File(args(i+1));
          case _ => println("unknown option : " + args(i));
        }
      }
      if (n >= args.size) {
        println("SikulixScript (options) [scala/javascript]");
        println("(options)");
        println(" -init [script] : run the script string before running a script file.");
        println(" -script [file] : run the script in [file].");
        println(" -i : interpret after script.");
        System.exit(1);
      }
      // ‚È‚º‚©scala script‚Å‚¢‚«‚È‚ènew Screen‚·‚é‚Æ
      // java.lang.UnsatisfiedLinkError: org.sikuli.util.SysJNA$WinKernel32.
      // GetEnvironmentVariableW(Lorg/bridj/Pointer;Lorg/bridj/Pointer;I)I
      val screen = new Screen;
      val _this = new ScriptShell(args(n));
      args(n) match {
      case "scala" =>
        _this.putWithType("_screen", screen, "org.sikuli.script.Screen");
        _this.eval("import jp.gr.java_conf.harada.SikulixScript._");
        _this.eval("implicit val screen = _screen");
      case "javascript" =>
        _this.put("_screen", screen);
        _this.eval("function click(path,modifier) { if (path == undefined) return _screen.click(); else if (modifier == undefined) return _screen.click(path); else return _screen.click(path,modifier);}");
        _this.eval("function doubleClick(path) { return _screen.click(path);}");
        _this.eval("function rightClick(path) { return _screen.click(path);}");
        _this.eval("function find(path) { return _screen.find(path);}");
        _this.eval("function drag(target) { return _screen.drag(target);}");
        _this.eval("function dragDrop(from, to) { return _screen.dragDrop(from, to);}");
        _this.eval("function dropAt(target) { return _screen.dragDrop(target);}");
        _this.eval("function exists(target,timeout) { if (timeout == undefined) return _screen,exists(target); else return _screen.exists(target, timeout);}");
        _this.eval("function find(path) { return _screen.find(path);}");
        _this.eval("function findBest(path) { return _screen.findBest(path);}");
        _this.eval("function findAll(path) { return _screen.findAll(path);}");
        _this.eval("function hightlight(secs,color) { _screen.hightlight(secs,color);}");
        _this.eval("function hover(target) { return _screen.hover(target);}");
        _this.eval("function paste(target,text) { if (text == undefined) return _screen.paste(target); else return _screen.paste(new org.sikuli.script.Pattern(target),text);}");
        _this.eval("function type(target,text,modifier) { if (text == undefined) return type(target); else if (modifier == undefined) return _screen.type(target, text); else return _screen.type(target, text, modifier);}");
        _this.eval("function saveScreenCapture(path) { return _screen.saveScreenCapture(path);}");
        _this.eval("function wait(target,timeout) { if (timeout == undefined) return _screen.wait(target); else return _screen.wait(new org.sikuli.script.Pattern(target),timeout);}");
        _this.eval("function wheel(target,direction,steps,stepDelay) { if (direction == undefined) return _screen.wheel(target); else if (steps == undefined) return _screen.wheel(target,direction); else if (stepDelay == undefined) return _screen.wheel(target,direction,steps); else return _screen.wheel(target,direction,steps,stepDelay);}");
        _this.eval("function userCapture(msg) { if (msg == undefined) return _screen.userCapture(); else return _screen.userCapture(msg);}");
      }
      if (scriptfile != null) _this.run(new BufferedReader(new FileReader(scriptfile))) match {
        case ScriptOK => //
        case ScriptExit(code) => System.exit(code);
        case ScriptRet(ret) => // ignore
      }
      if (interpreter) _this.shell() match {
        case ScriptExit(code) => System.exit(code);
      }
      System.exit(0);
  }
  implicit def matchToBoolean(m:Match) = (m != null);
  def setAutoWaitTimeout(sec:Double)(implicit s : Screen) { s.setAutoWaitTimeout(sec);}
  def click()(implicit s : Screen) = (s.click() == 1);
  def click(path:String)(implicit s : Screen) = (s.click(path) == 1);
  def click(path:String, modifier:Int)(implicit s : Screen) = (s.click(path,modifier) == 1);
  def rightClick()(implicit s : Screen) = (s.rightClick() == 1);
  def rightClick(path:String)(implicit s : Screen) = (s.rightClick(path) == 1);
  def rightClick(path:String, modifier:Int)(implicit s : Screen) = (s.rightClick(path,modifier) == 1);
  def doubleClick()(implicit s : Screen) = (s.doubleClick() == 1);
  def doubleClick(path:String)(implicit s : Screen) = (s.doubleClick(path) == 1);
  def doubleClick(path:String, modifier:Int)(implicit s : Screen) = (s.doubleClick(path, modifier) == 1);
  def drag(target:String)(implicit s : Screen) = (s.drag(target) == 1);
  def dragDrop(target:String)(implicit s : Screen) = (s.dragDrop(target) == 1);
  def dragDrop(from:String, to:String)(implicit s : Screen) = (s.dragDrop(from,to) == 1);
  def dropAt(target:String)(implicit s : Screen) = (s.dropAt(target) == 1);
  def exists(target:String)(implicit s : Screen) = s.exists(target);
  def exists(target:String, timeout:Double)(implicit s : Screen) = s.exists(target, timeout);
  def find(path:String)(implicit s : Screen) = s.find(path);
  def findBest(path:String)(implicit s : Screen) = s.findBest(path);
  def findAll(path:String)(implicit s : Screen) : Iterator[Match] = s.findAll(path);
  def hightlight()(implicit s : Screen) = s.highlight();
  def hightlight(secs:Float)(implicit s : Screen) = s.highlight(secs);
  def hightlight(secs:Int, color:String)(implicit s : Screen) = s.highlight(secs, color);
  def hightlight(color:String)(implicit s : Screen) = s.highlight(color);
  def hover()(implicit s : Screen) = s.hover();
  def hover(target:String)(implicit s : Screen) = s.hover(target);
  def paste(target:String, text:String)(implicit s : Screen) = s.paste(target, text);
  def paste(text:String)(implicit s : Screen) = s.paste(text);
  def `type`(text:String)(implicit s : Screen) = s.`type`(text);
  def `type`(target:String, text:String)(implicit s : Screen) = s.`type`(target, text);
  def `type`(target:String, text:String, modifier:Int)(implicit s : Screen) = s.`type`(target, text, modifier);
  def `type`(text:String, modifier:Int)(implicit s : Screen) = s.`type`(text, modifier);
  def saveScreenCapture()(implicit s : Screen) = s.saveScreenCapture();
  def saveScreenCapture(path:String)(implicit s : Screen) = s.saveScreenCapture(path);
  def wait(timeout:Double)(implicit s : Screen) = s.wait(timeout);
  def wait(target:String)(implicit s : Screen) = s.wait(target);
  def wait(target:String, timeout:Double)(implicit s : Screen) = s.wait(target, timeout);
  def waitVanish(target:String)(implicit s : Screen) = s.waitVanish(target);
  def waitVanish(target:String, timeout:Double)(implicit s : Screen) = s.waitVanish(target, timeout);
  def wheel(direction:Int, steps:Int)(implicit s : Screen) = s.wheel(direction, steps);
  def wheel(target:String, direction:Int, steps:Int)(implicit s : Screen) = s.wheel(target, direction, steps);
  def wheel(target:String, direction:Int, steps:Int, stepDelay:Int)(implicit s : Screen) = s.wheel(target, direction, steps, stepDelay);
  def userCapture()(implicit s : Screen) = s.userCapture();
  def userCapture(msg:String)(implicit s : Screen) = s.userCapture(msg);
  def capture()(implicit s : Screen) = s.capture();
  def capture(region:Region)(implicit s : Screen) = s.capture(region);
}