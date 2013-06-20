package test

import test.BenchUtil._
import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

object Test3_1 extends App {

  bench(100) {
    val productFuture1 = future {
      ProductDao.findByName("MacPro")
    }

    val productFuture2 = future {
      ProductDao.findByName("MacBookPro")
    }

    val productFuture = productFuture1.flatMap {
      macPro =>
        productFuture2.map {
          macBookPro =>
            macPro.toString + ", " + macBookPro.toString
        }
    }

    productFuture.foreach(println)

    Await.ready(productFuture, Duration.Inf)
  }
  Thread.sleep(1000)


}
