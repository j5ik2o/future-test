package test

import test.BenchUtil._
import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

object Test3_2 extends App {

  bench(100) {
    val productFuture1 = future {
      ProductDao.findByName("MacPro")
    }

    val productFuture2 = future {
      ProductDao.findByName("MacBookPro")
    }

    val productFuture = for {
      macPro <- productFuture1
      macBookPro <- productFuture2
    } yield {
      macPro.toString + ", " + macBookPro.toString
    }

    productFuture.foreach(println)

    Await.ready(productFuture, Duration.Inf)
  }
  Thread.sleep(1000)

}
