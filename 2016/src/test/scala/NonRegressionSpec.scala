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
    (1, 1, 300),
    (1, 2, 159),
    (2, 1, "12578"),
    (2, 2, "516DD"),
    (3, 1, 993),
    (3, 2, 1849),
    (4, 1, 185371),
    (4, 2, "984"),
//    (5, 1, "1a3099aa"),
//    (5, 2, "uqwqemis"),
    (6, 1, "cyxeoccr"),
    (6, 2, "batwpask"),
    (7, 1, 118),
    (7, 2, 260),
    (8, 1, 128),
    (8, 2, "EOARGPHYAO"),
    (9, 1, 102239),
    (9, 2, 10780403063L),
    (10, 1, "113"),
    (10, 2, 12803),
//    (11, 1, 47),
//    (11, 2, 71),
    (12, 1, 318007),
    (12, 2, 9227661),
    (13, 1, 92),
    (13, 2, 124),
    (14, 1, 23769),
    // very slow
    // (14, 2, 20606),
    (15, 1, 317371),
    (15, 2, 2080951),
    (16, 1, "01110011101111011"),
    (16, 2, "11001111011000111"),
    (17, 1, "RDDRLDRURD"),
    (17, 2, 448),
    (18, 1, 1963),
    (18, 2, 20009568),
    (19, 1, 1834903),
    (19, 2, 1420280),
    (20, 1, 31053880),
    (20, 2, 117),
    (21, 1, "gfdhebac"),
    (21, 2, "dhaegfbc"),
    (22, 1, 1024),
//    (22, 2, 230),
    (23, 1, 10584),
    // very slow
    // (23, 2, 479007144),
    (24, 1, 474),
    (24, 2, 696),
    (25, 1, 180)
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
