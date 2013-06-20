package test

import scala.testing.Benchmark

object BenchUtil {

  private def avg(xs:List[BigDecimal]):BigDecimal =
    xs.sum / xs.size

  private def std(avg:BigDecimal, xs:List[BigDecimal]):BigDecimal =
    Math.sqrt((xs.foldLeft(BigDecimal(0))((s,c) => s + (c-avg) * (c+avg)) / xs.size).toDouble)

  private def center(xs:List[BigDecimal]) = xs.toSet.toList.sortWith(_ < _) match {
    case n :: Nil => n
    case xs if xs.size % 2 !=0 => xs(xs.size / 2)
    case xs if xs.size % 2 == 0 =>  {
      val a = xs(xs.size / 2 -1)
      val b = xs(xs.size / 2)
      (a + b)/2
    }
    case _ => throw new RuntimeException
  }

  private def mode(xs:List[BigDecimal]):BigDecimal =
    xs.foldLeft(Map[BigDecimal,Int]().withDefaultValue(0)){(map,key) =>map + (key -> (map(key) + 1))} maxBy(_._2) _1

  def bench(n:Int)(f: => Unit) {
    val l = new Benchmark{ def run = f }.runBenchmark(n)
    val result = l.filterNot(e => e  == l.max || e == l.min).map(BigDecimal(_))
    if (result.size > 0 ){
      val avgValue = avg(result)
      println("avg = %5.2f, std = %5.2f, center = %5.2f, mode = %5.2f, min = %5.2f, max = %5.2f".
        format(avgValue, std(avgValue, result), center(result), mode(result), result.min, result.max))
    }
  }
}
