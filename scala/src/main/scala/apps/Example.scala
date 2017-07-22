package apps

import cats.implicits._
import com.typesafe.scalalogging.LazyLogging

object Example extends App with LazyLogging {
  logger.info("This is a test")

  logger.info("The test is complete")
}