import org.scalatest.prop.TableDrivenPropertyChecks._

class NonRegressionSpec
    extends AllPuzzlesSpec(
      Table(
        ("day", "part", "result"),
        (1, 1, 74198),
        (1, 2, 209914),
        (2, 1, 13526),
        (2, 2, 14204),
        (3, 1, 7742),
        (3, 2, 2276),
        (4, 1, 526),
        (4, 2, 886),
        (5, 1, "FWNSHLDNZ"),
        (5, 2, "RNRGDNFQG"),
        (6, 1, 1566),
        (6, 2, 2265),
        (7, 1, 1477771),
        (7, 2, 3579501),
        (8, 1, 1690),
        (8, 2, 535680),
        (9, 1, 5695),
        (9, 2, 2434),
        (10, 1, 14860),
        (10, 2, """###...##..####.####.#..#.#..#.###..#..#.
                  |#..#.#..#....#.#....#..#.#..#.#..#.#.#..
                  |#..#.#......#..###..####.#..#.#..#.##...
                  |###..#.##..#...#....#..#.#..#.###..#.#..
                  |#.#..#..#.#....#....#..#.#..#.#.#..#.#..
                  |#..#..###.####.####.#..#..##..#..#.#..#.""".stripMargin),
        (11, 1, 58794L),
        (11, 2, 20151213744L),
        (12, 1, 423),
        (12, 2, 416),
        (13, 1, 6187),
        (13, 2, 23520),
// too slow
//    (14, 1, 737),
//    (14, 2, 28145),
        (15, 1, 4907780),
        (15, 2, 13639962836448L),
// doesn't work in CI
        //        (16, 1, 1947),
        (17, 1, 3147),
        (18, 1, 4244),
        (20, 1, 2215),
        (20, 2, 8927480683L),
        (21, 1, 160274622817992L),
        (22, 1, 77318),
        (23, 1, 3864),
        (23, 2, 946),
        (25, 1, "2-02===-21---2002==0")
      )
    )
