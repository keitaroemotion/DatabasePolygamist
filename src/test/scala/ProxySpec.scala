
import org.specs2.mutable.Specification
import scala.collection.immutable.Map
import scala.collection.immutable.List
import java.io.FileOutputStream
import java.sql.{Connection, DriverManager, SQLException};

class MainSpec extends Specification {
  val dsl = mapRawConfig(scala.io.Source.fromFile(System.getProperty("config.resource", "src/test/resource/test.conf")).getLines)
  def mapRawConfig(dsl:Iterator[String]):Map[String,String] = {
    dsl.foldLeft(Map[String,String]()){ (map, line) =>
      map + (line -> line)
    }
  }

  "MySQL" should {
    def extractConfig():Map[String,String] = {
      dsl.foreach{ x =>
        println(x)
      }
      Map()
    }
    def initializeJDBCDriver() = {
      Class.forName("com.mysql.jdbc.Driver").newInstance()
    }
    "#INSERT" in {
      initializeJDBCDriver
      extractConfig
      var conn =  DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                                   "user=root");
      val stmt = conn.createStatement();
      val sql = "SELECT *  FROM ips";
      val  rs = stmt.executeQuery(sql);
      println(rs)
      "" == ""
    }
  }
}

