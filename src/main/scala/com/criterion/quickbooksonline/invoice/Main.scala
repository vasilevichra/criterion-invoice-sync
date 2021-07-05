package com.criterion.quickbooksonline.invoice

import com.criterion.quickbooksonline.invoice.Model.{EnrichedInvoice, QuickBooksOnlineInvoice}
import com.typesafe.config.ConfigFactory
import io.circe.generic.auto._
import io.circe.parser._

import java.time.ZonedDateTime
import scala.io.Source

object Main extends App {

  val startDate = ZonedDateTime.now()

  val conf = ConfigFactory.load()
  val url = conf.getString("url")

  val json = Source.fromURL(url).mkString

  decode[QuickBooksOnlineInvoice](json)
    .map(invoice => EnrichedInvoice(Invoice = invoice.Invoice, StartDate = startDate, EndDate = ZonedDateTime.now()))
    .map(enrichedInvoice => pprint.tokenize(enrichedInvoice).mkString)
    .map(print)

}
