package com.criterion.quickbooksonline.invoice

import com.criterion.quickbooksonline.invoice.Model.QuickBooksOnlineInvoice
import io.circe.generic.auto._
import io.circe.parser._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MainTest extends AnyWordSpec with Matchers {

  "Invoice from an example json" should {

    "be parsed to the case class" in {

      val json =
        """{
          |  "Invoice": {
          |    "TxnDate": "2014-09-19",
          |    "domain": "QBO",
          |    "PrintStatus": "NeedToPrint",
          |    "SalesTermRef": {
          |      "value": "3"
          |    },
          |    "TotalAmt": 362.07,
          |    "Line": [
          |      {
          |        "Description": "Rock Fountain",
          |        "DetailType": "SalesItemLineDetail",
          |        "SalesItemLineDetail": {
          |          "TaxCodeRef": {
          |            "value": "TAX"
          |          },
          |          "Qty": 1,
          |          "UnitPrice": 275,
          |          "ItemRef": {
          |            "name": "Rock Fountain",
          |            "value": "5"
          |          }
          |        },
          |        "LineNum": 1,
          |        "Amount": 275.0,
          |        "Id": "1"
          |      },
          |      {
          |        "Description": "Fountain Pump",
          |        "DetailType": "SalesItemLineDetail",
          |        "SalesItemLineDetail": {
          |          "TaxCodeRef": {
          |            "value": "TAX"
          |          },
          |          "Qty": 1,
          |          "UnitPrice": 12.75,
          |          "ItemRef": {
          |            "name": "Pump",
          |            "value": "11"
          |          }
          |        },
          |        "LineNum": 2,
          |        "Amount": 12.75,
          |        "Id": "2"
          |      },
          |      {
          |        "Description": "Concrete for fountain installation",
          |        "DetailType": "SalesItemLineDetail",
          |        "SalesItemLineDetail": {
          |          "TaxCodeRef": {
          |            "value": "TAX"
          |          },
          |          "Qty": 5,
          |          "UnitPrice": 9.5,
          |          "ItemRef": {
          |            "name": "Concrete",
          |            "value": "3"
          |          }
          |        },
          |        "LineNum": 3,
          |        "Amount": 47.5,
          |        "Id": "3"
          |      },
          |      {
          |        "DetailType": "SubTotalLineDetail",
          |        "Amount": 335.25,
          |        "SubTotalLineDetail": {}
          |      }
          |    ],
          |    "DueDate": "2014-10-19",
          |    "ApplyTaxAfterDiscount": false,
          |    "DocNumber": "1037",
          |    "sparse": false,
          |    "CustomerMemo": {
          |      "value": "Thank you for your business and have a great day!"
          |    },
          |    "Deposit": 0,
          |    "Balance": 362.07,
          |    "CustomerRef": {
          |      "name": "Sonnenschein Family Store",
          |      "value": "24"
          |    },
          |    "TxnTaxDetail": {
          |      "TxnTaxCodeRef": {
          |        "value": "2"
          |      },
          |      "TotalTax": 26.82,
          |      "TaxLine": [
          |        {
          |          "DetailType": "TaxLineDetail",
          |          "Amount": 26.82,
          |          "TaxLineDetail": {
          |            "NetAmountTaxable": 335.25,
          |            "TaxPercent": 8,
          |            "TaxRateRef": {
          |              "value": "3"
          |            },
          |            "PercentBased": true
          |          }
          |        }
          |      ]
          |    },
          |    "SyncToken": "0",
          |    "LinkedTxn": [
          |      {
          |        "TxnId": "100",
          |        "TxnType": "Estimate"
          |      }
          |    ],
          |    "BillEmail": {
          |      "Address": "Familiystore@intuit.com"
          |    },
          |    "ShipAddr": {
          |      "City": "Middlefield",
          |      "Line1": "5647 Cypress Hill Ave.",
          |      "PostalCode": "94303",
          |      "Lat": "37.4238562",
          |      "Long": "-122.1141681",
          |      "CountrySubDivisionCode": "CA",
          |      "Id": "25"
          |    },
          |    "EmailStatus": "NotSet",
          |    "BillAddr": {
          |      "Line4": "Middlefield, CA  94303",
          |      "Line3": "5647 Cypress Hill Ave.",
          |      "Line2": "Sonnenschein Family Store",
          |      "Line1": "Russ Sonnenschein",
          |      "Long": "-122.1141681",
          |      "Lat": "37.4238562",
          |      "Id": "95"
          |    },
          |    "MetaData": {
          |      "CreateTime": "2014-09-19T13:16:17-07:00",
          |      "LastUpdatedTime": "2014-09-19T13:16:17-07:00"
          |    },
          |    "CustomField": [
          |      {
          |        "DefinitionId": "1",
          |        "StringValue": "102",
          |        "Type": "StringType",
          |        "Name": "Crew #"
          |      }
          |    ],
          |    "Id": "130"
          |  },
          |  "time": "2015-07-24T10:48:27.082-07:00"
          |}
          |""".stripMargin

      decode[QuickBooksOnlineInvoice](json).isRight shouldBe true

    }

  }

}
