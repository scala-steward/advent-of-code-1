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
    (1, 1, 280),
    (1, 2, 1797),
    (2, 1, 1588178),
    (2, 2, 3783758),
    (3, 1, 2081),
    (3, 2, 2341),
// Very slow
// (4, 1, 117946),
// (4, 2, 3938038),
    (5, 1, 255),
    (5, 2, 55),
    (6, 1, 377891),
    (6, 2, 14110788),
    (7, 1, 16076),
    (7, 2, 2797),
    (8, 1, 1333),
    (8, 2, 2046),
// TODO
//    (9, 1, 207),
//    (9, 2, 804),
//    (10, 1, 492982),
//    (10, 2, 1321131112),
//    (11, 1, "vzcaabcc"),
//    (11, 2, "vzbxkghb"),
    (12, 1, 191164),
//    (12, 2, ),
    (13, 1, 709),
    (13, 2, 668),
    (14, 1, 2660),
    (14, 2, 1256),
    (15, 1, 21367368),
    (15, 2, 1766400),
//    (16, 1, 40),
//    (16, 2, 241),
    (17, 1, 1638),
    (17, 2, 17),
//    (18, 1, 814),
//    (18, 2, 924),
    (19, 1, 535),
//    (19, 2, ),
    (20, 1, 786240),
    (20, 2, 831600),
//    (21, 1, 121),
//    (21, 2, 201),
//    (22, 1, ),
//    (22, 2, ),
//    (23, 1, 307),
//    (23, 2, 160),
    (24, 1, 10439961859L),
    (24, 2, 72050269)
//    (25, 1, ),
//    (25, 2, ),
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