import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks._

@SuppressWarnings(
  Array(
    "org.wartremover.warts.Any",
    "org.wartremover.warts.AsInstanceOf",
    "org.wartremover.warts.NonUnitStatements"
  )
)
class NonRegressionSpec extends AnyFlatSpec with Matchers {
  val m = Table(
    ("day", "part", "result"),
    (1, 1, 1158),
    (1, 2, 1132),
    (2, 1, 30994),
    (2, 2, 233),
//    (3, 1, 369601),
//    (3, 2, 368078),
    (4, 1, 455),
    (4, 2,186 ),
    (5, 1, 372671),
    (5, 2, 25608480),
    (6, 1, 5042),
//    (6, 2, 1046),
    (7, 1, "qibuqqg"),
//    (7, 2, 1079),
    (8, 1, 5946),
    (8, 2, 6026),
    (9, 1, 14204),
    (9, 2, 6622),
    (10, 1, 1980),
    (10, 2, "899124dac21012ebc32e2f4d11eaec55"),
//    (11, 1, 818),
//    (11, 2, 1596),
    (13, 1, 1728),
    (13, 2, 3946838)
  )

  forAll(m) { (d: Int, p: Int, res: Any) =>
    Puzzles.findPuzzles().find(_.day() == s"$d") match {
      case Some(puzzle) =>
        p match {
          case 1 => puzzle.part1(puzzle.input) shouldBe res
          case 2 => puzzle.part2(puzzle.input) shouldBe res
        }
      case None => sys.error(s"Unknown day '$d'")
    }
  }
}