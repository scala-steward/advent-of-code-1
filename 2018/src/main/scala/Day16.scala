object Day16 extends MultiPuzzle[Int, Int] {
  sealed trait OpCode {
    def op(in1: Int, in2: Int)(regs: Vector[Int]): Int
    def execute(in1: Int, in2: Int, out: Int)(regs: Vector[Int]): Vector[Int] =
      regs.updated(out, op(in1, in2)(regs))
  }
  case object addr extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = regs(in1) + regs(in2)
  }

  case object addi extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = regs(in1) + in2
  }

  case object mulr extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = regs(in1) * regs(in2)
  }

  case object muli extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = regs(in1) * in2
  }

  case object banr extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = regs(in1) & regs(in2)
  }

  case object bani extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = regs(in1) & in2
  }

  case object borr extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = regs(in1) | regs(in2)
  }

  case object bori extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = regs(in1) | in2
  }

  case object setr extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = regs(in1)
  }

  case object seti extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = in1
  }

  case object gtir extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = (if (in1 > regs(in2)) 1 else 0)
  }

  case object gtri extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = (if (regs(in1) > in2) 1 else 0)
  }

  case object gtrr extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int =
      (if (regs(in1) > regs(in2)) 1 else 0)
  }

  case object eqir extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = (if (in1 == regs(in2)) 1 else 0)
  }

  case object eqri extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int = (if (regs(in1) == in2) 1 else 0)
  }

  case object eqrr extends OpCode {
    override def op(in1: Int, in2: Int)(regs: Vector[Int]): Int =
      (if (regs(in1) == regs(in2)) 1 else 0)
  }

  val opcodes: Seq[OpCode] = Seq(
    addr,
    addi,
    mulr,
    muli,
    banr,
    bani,
    borr,
    bori,
    setr,
    seti,
    gtir,
    gtri,
    gtrr,
    eqir,
    eqri,
    eqrr
  )

  case class Sample(before: Vector[Int], instruction: Array[Int], after: Vector[Int])

  override def part1(input: Iterator[String]): Int = {
    val samples = input.take(3260).grouped(4).map { lines =>
      val s"Before: [$b1, $b2, $b3, $b4]" = lines(0)
      val instruction                     = lines(1).split(" ").map(_.toInt)
      val s"After:  [$a1, $a2, $a3, $a4]" = lines(2)
      Sample(
        Vector(b1.toInt, b2.toInt, b3.toInt, b4.toInt),
        instruction,
        Vector(a1.toInt, a2.toInt, a3.toInt, a4.toInt)
      )
    }

    samples.count(searchPossibleOpcodes(_).size >= 3)
  }

  override def part2(input: Iterator[String]): Int = {
    val samples = input.take(3260).grouped(4).map { lines =>
      val s"Before: [$b1, $b2, $b3, $b4]" = lines(0)
      val instruction                     = lines(1).split(" ").map(_.toInt)
      val s"After:  [$a1, $a2, $a3, $a4]" = lines(2)
      Sample(
        Vector(b1.toInt, b2.toInt, b3.toInt, b4.toInt),
        instruction,
        Vector(a1.toInt, a2.toInt, a3.toInt, a4.toInt)
      )
    }

    // the aim is to find out the bijection between op code value 0..15 and opcodes
    val possibleSolutions: Array[Set[OpCode]] = Array.fill[Set[OpCode]](16)(opcodes.toSet)

    samples.foreach { sample =>
      val valid = searchPossibleOpcodes(sample).toSet
      possibleSolutions(sample.instruction(0)) =
        possibleSolutions(sample.instruction(0)).intersect(valid)
    }
    val solution = solve(possibleSolutions)

    val program: Iterator[Array[Int]] = input.drop(2).map(_.split(" ").map(_.toInt))
    val init                          = Vector(0, 0, 0, 0)
    program
      .foldLeft(init) {
        case (regs, instruction) =>
          val i = solution(instruction.apply(0))
          i.execute(instruction(1), instruction(2), instruction(3))(regs)
      }.apply(0)
  }

  def solve(possibleSolutions: Array[Set[OpCode]]): Array[OpCode] = {
    while (possibleSolutions.exists(_.size > 1)) {
      val singles = possibleSolutions.filter(_.size == 1).flatten.toSet
      possibleSolutions.indices.foreach(i =>
        if (possibleSolutions(i).size > 1) possibleSolutions(i) = possibleSolutions(i).diff(singles)
      )
    }
    possibleSolutions.map(_.head)
  }

  def searchPossibleOpcodes(s: Sample): Seq[OpCode] =
    opcodes.filter { op =>
      op.execute(s.instruction(1), s.instruction(2), s.instruction(3))(s.before) == s.after
    }
}
