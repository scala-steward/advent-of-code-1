import org.scalatest.prop.TableDrivenPropertyChecks._

@SuppressWarnings(Array("org.wartremover.warts.Any"))
class NonRegressionSpec extends AllPuzzlesSpec(
  Table(
    ("day", "part", "result"),
    (1, 1, 3457281),
    (1, 2, 5183030),
    (2, 1, 3765464),
    (2, 2, 7610),
    (3, 1, 399),
    (3, 2, 15678),
    (4, 1, 1864),
    (4, 2, 1258),
    (5, 1, 7265618),
    (5, 2, 7731427),
    (6, 1, 278744),
    (6, 2, 475),
    (7, 1, 21860),
    (7, 2, 2645740),
    (8, 1, 2975),
    (8, 2,
      """**** *  * ***  *  * ****
        |*    *  * *  * *  * *
        |***  **** *  * *  * ***
        |*    *  * ***  *  * *
        |*    *  * * *  *  * *
        |**** *  * *  *  **  **** """.stripMargin),
    (9, 1, 3063082071L),
    (9, 2, 81348)
  )
)