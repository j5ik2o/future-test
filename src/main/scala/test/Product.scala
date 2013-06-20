package test

case class Product(name: String, price: Int)

object ProductDao {

  def findByName(name: String) = {
    Thread.sleep(100)
    Product(name, 100)
  }

}
