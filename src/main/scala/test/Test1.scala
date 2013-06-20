package test

import scala.concurrent._
import scala.util._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import BenchUtil._

object Test1 extends App {

  bench(100) {
    val productFuture = future {
      ProductDao.findByName("MacPro")
    }

    productFuture onComplete {
      case Success(product) => println(product)
      case Failure(ex) => ex.printStackTrace()
    }

    Await.ready(productFuture, Duration.Inf)
  }
  Thread.sleep(1000)

}
