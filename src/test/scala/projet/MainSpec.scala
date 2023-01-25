package projet

import com.typesafe.config.{Config, ConfigFactory, ConfigValueFactory}
import org.scalatest.funsuite.AnyFunSuite

class MainSpec extends AnyFunSuite {

  test(
    "lawn in (1,2,N) and (GAGAGAGAAD) should return (1,3,E) and lawn in (2,4,N) and (DDAGAADADAAGDAA) should return (0,2,W)"
  ) {
    val conf: Config = ConfigFactory.load
      .withValue(
        "appplication.input-file",
        ConfigValueFactory.fromAnyRef("src/test/scala/projet/inputs/test.txt")
      )
      .resolve()
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      Main.main(Array())(Some(conf))
    }
    assert(
      stream.toString.replace("\n", "").replace("\r", "")
        ===
          """{"limite":{"x":5,"y":5},"tondeuses":[{"debut":{"point":{"x":1,"y":2},"direction":"N"},"instructions":["G","A","G","A","G","A","G","A","A","D"],"fin":{"point":{"x":1,"y":3},"direction":"E"}},{"debut":{"point":{"x":2,"y":4},"direction":"N"},"instructions":["D","D","A","G","A","A","D","A","D","A","A","G","D","A","A"],"fin":{"point":{"x":0,"y":2},"direction":"W"}}]}1;1;2;N;1;3;E;GAGAGAGAAD2;2;4;N;0;2;W;DDAGAADADAAGDAA"""
    )
  }

  test(
    "lawn in (1,2,N) and (GAGAGBGAAD) should return error"
  ) {
    val conf: Config = ConfigFactory.load
      .withValue(
        "appplication.input-file",
        ConfigValueFactory.fromAnyRef("src/test/scala/projet/inputs/test2.txt")
      )
      .resolve()
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      Main.main(Array())(Some(conf))
    }
    assert(
      stream.toString.replace("\n", "").replace("\r", "")
        ===
          "Une erreur à été trouvé, interrruption du programme: Action inconnue: B"
    )
  }

}
