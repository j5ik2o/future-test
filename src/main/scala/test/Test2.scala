package test

import test.BenchUtil._
import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

object Test2 extends App {

  bench(100) {
    val productFuture = future {
      ProductDao.findByName("MacPro")
    }.map {
      product =>
       product.toString
    }

    productFuture.foreach(println)

    Await.ready(productFuture, Duration.Inf)
  }
  Thread.sleep(1000)

}
