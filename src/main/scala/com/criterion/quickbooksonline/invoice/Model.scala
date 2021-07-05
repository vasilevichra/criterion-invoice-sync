package com.criterion.quickbooksonline.invoice

import enumeratum._
import io.circe.Decoder
import io.circe.generic.auto._


object Model {

  import SimpleEnum._

  final case class EnrichedInvoice(
    Invoice: Invoice,
    StartDate: java.time.ZonedDateTime,
    EndDate: java.time.ZonedDateTime
  )

  final case class QuickBooksOnlineInvoice(
    Invoice: Invoice,
    time: java.time.ZonedDateTime
  )

  final case class Invoice(
    Id: Option[String] = None,
    Line: Seq[InvoiceLine],
    CustomerRef: ReferenceType,
    SyncToken: Option[String] = None,
    CurrencyRef: Option[CurrencyRefType] = None,
    DocNumber: Option[String] = None,
    BillEmail: Option[EmailAddress] = None,
    TxnDate: Option[java.time.LocalDate] = None,
    ShipFromAddr: Option[PhysicalAddress] = None,
    ShipDate: Option[Date] = None,
    TrackingNum: Option[String] = None,
    ClassRef: Option[ReferenceType] = None,
    PrintStatus: Option[String] = None,
    SalesTermRef: Option[ReferenceType] = None,
    TxnSource: Option[String] = None,
    LinkedTxn: Option[Seq[LinkedTxn]] = None, // bad design but it's optional
    DepositToAccountRef: Option[ReferenceType] = None,
    GlobalTaxCalculation: Option[GlobalTaxCalculationEnum] = None,
    AllowOnlineACHPayment: Option[Boolean] = None,
    TransactionLocationType: Option[String] = None,
    DueDate: Option[java.time.LocalDate] = None, // bug in specification: expected Date but actually java.time.LocalDate
    MetaData: Option[ModificationMetaData] = None,
    PrivateNote: Option[String] = None,
    BillEmailCc: Option[EmailAddress] = None,
    CustomerMemo: Option[MemoRef] = None,
    EmailStatus: Option[String] = None,
    ExchangeRate: Option[Double] = None,
    Deposit: Option[Double] = None,
    TxnTaxDetail: Option[TxnTaxDetail] = None,
    AllowOnlineCreditCardPayment: Option[Boolean] = None,
    CustomField: Option[Seq[CustomField]] = None, // bad design but it's optional
    ShipAddr: Option[PhysicalAddress] = None,
    DepartmentRef: Option[ReferenceType] = None,
    BillEmailBcc: Option[EmailAddress] = None,
    ShipMethodRef: Option[ReferenceType] = None,
    BillAddr: Option[PhysicalAddress] = None,
    ApplyTaxAfterDiscount: Option[Boolean] = None,
    HomeBalance: Option[Double] = None, // bug in specification: expected mandatory parameter but actually it's optional
    DeliveryInfo: Option[DeliveryInfo] = None, // bug in specification: expected mandatory parameter but actually it's optional
    TotalAmt: BigDecimal,
    InvoiceLink: Option[String] = None, // bug in specification: expected mandatory parameter but actually it's optional
    RecurDataRef: Option[ReferenceType] = None, // bug in specification: expected mandatory parameter but actually it's optional
    TaxExemptionRef: Option[ReferenceType] = None, // bug in specification: expected mandatory parameter but actually it's optional
    Balance: Double,
    HomeTotalAmt: Option[Double] = None, // bug in specification: expected mandatory parameter but actually it's optional
    FreeFormAddress: Option[Boolean] = None, // bug in specification: expected mandatory parameter but actually it's optional
    AllowOnlinePayment: Option[Boolean], // deprecated
    AllowIPNPayment: Option[Boolean] // deprecated
  )

  final case class ReferenceType(
    value: String,
    name: Option[String] = None
  )

  final case class CurrencyRefType(
    value: String,
    name: Option[String] = None
  )

  final case class EmailAddress(
    Address: Option[String] = None
  )

  final case class PhysicalAddress(
    Id: Option[String] = None,
    PostalCode: Option[String] = None,
    City: Option[String] = None,
    Country: Option[String] = None,
    Line5: Option[String] = None,
    Line4: Option[String] = None,
    Line3: Option[String] = None,
    Line2: Option[String] = None,
    Line1: Option[String] = None,
    Lat: Option[String] = None,
    Long: Option[String] = None,
    CountrySubDivisionCode: Option[String] = None
  )

  final case class Date(
    date: String
  )

  final case class DateTime(
    dateTime: String
  )

  final case class LinkedTxn(
    TxnId: String,
    TxnType: String,
    TxnLineId: Option[String] = None
  )

  final case class ModificationMetaData(
    CreateTime: Option[java.time.ZonedDateTime] = None,
    LastUpdatedTime: Option[java.time.ZonedDateTime] = None
  )

  final case class MemoRef(
    value: String
  )

  final case class TxnTaxDetail(
    TxnTaxCodeRef: Option[ReferenceType] = None,
    TotalTax: Option[Double] = None,
    TaxLine: Option[Seq[TaxLine]] = None // bad design but it's optional
  )

  final case class TaxLine(
    DetailType: LineDetailTypeEnum,
    TaxLineDetail: TaxLineDetail,
    Amount: Option[Double] = None
  )

  final case class TaxLineDetail(
    TaxRateRef: ReferenceType,
    NetAmountTaxable: Option[Double] = None,
    PercentBased: Option[Boolean] = None,
    TaxInclusiveAmount: Option[Double] = None,
    OverrideDeltaAmount: Option[Double] = None,
    TaxPercent: Option[Double] = None
  )

  final case class CustomField(
    DefinitionId: String,
    StringValue: Option[String] = None,
    Name: Option[String] = None,
    Type: CustomFieldTypeEnum
  )

  final case class DeliveryInfo(
    DeliveryType: DeliveryTypeEnum,
    DeliveryTime: DateTime
  )

  final case class LineDetail(
    ItemRef: Option[ReferenceType] = None
  )

  final case class MarkupInfo(
    PriceLevelRef: Option[ReferenceType] = None,
    Percent: Option[Double] = None,
    MarkUpIncomeAccountRef: Option[ReferenceType] = None
  )

  final case class SalesItemLineDetail(
    TaxInclusiveAmt: Option[Double] = None,
    DiscountAmt: Option[Double] = None,
    ItemRef: Option[ReferenceType] = None,
    ClassRef: Option[ReferenceType] = None,
    TaxCodeRef: Option[ReferenceType] = None,
    MarkupInfo: Option[MarkupInfo] = None,
    ItemAccountRef: Option[ReferenceType] = None,
    ServiceDate: Option[java.time.ZonedDateTime] = None,
    DiscountRate: Option[Double] = None,
    Qty: Option[Double] = None,
    UnitPrice: Option[Double] = None,
    TaxClassificationRef: Option[ReferenceType] = None
  )

  final case class GroupLineDetail(
    Quantity: Option[Double] = None,
    Line: Seq[GroupLineDetailLine],
    GroupItemRef: Option[ReferenceType] = None
  )

  final case class GroupLineDetailLine(
    Id: String,
    DetailType: LineDetailTypeEnum,
    SalesItemLineDetail: SalesItemLineDetail,
    Amount: Double,
    Description: Option[String] = None,
    LineNum: Option[Double] = None
  )

  final case class DescriptionLineDetail(
    TaxCodeRef: Option[ReferenceType] = None,
    ServiceDate: Option[Date] = None
  )

  final case class DiscountLineDetail(
    ClassRef: Option[ReferenceType] = None,
    TaxCodeRef: Option[ReferenceType] = None,
    DiscountAccountRef: Option[ReferenceType] = None,
    PercentBased: Option[Boolean] = None,
    DiscountPercent: Option[Double] = None
  )

  sealed trait InvoiceLine
  object InvoiceLine {
    implicit val decodeData: Decoder[InvoiceLine] =
      Decoder[SalesItemLine].map[InvoiceLine](identity)
        .or(Decoder[GroupLine].map[InvoiceLine](identity))
        .or(Decoder[DescriptionOnlyLine].map[InvoiceLine](identity))
        .or(Decoder[DiscountLine].map[InvoiceLine](identity))
        .or(Decoder[SubTotalLine].map[InvoiceLine](identity))
  }

  case class SalesItemLine(
    Id: String,
    DetailType: LineDetailTypeEnum,
    SalesItemLineDetail: SalesItemLineDetail,
    Amount: Double,
    Description: Option[String] = None,
    LineNum: Option[Double] = None
  ) extends InvoiceLine

  final case class GroupLine(
    Id: String,
    GroupLineDetail: GroupLineDetail,
    DetailType: LineDetailTypeEnum,
    LineNum: Option[Double] = None,
    Description: Option[String] = None
  ) extends InvoiceLine

  final case class DescriptionOnlyLine(
    Id: String,
    DetailType: LineDetailTypeEnum,
    DescriptionLineDetail: DescriptionLineDetail,
    Description: Option[String] = None,
    LineNum: Option[Double] = None,
    Amount: Double
  ) extends InvoiceLine

  final case class DiscountLine(
    Id: String,
    DiscountLineDetail: DiscountLineDetail,
    DetailType: LineDetailTypeEnum,
    Amount: Double,
    Description: Option[String] = None,
    LineNum: Option[Double] = None
  ) extends InvoiceLine

  final case class SubTotalLine(
    Id: Option[String] = None, // bug in specification: expected mandatory parameter but actually it's optional
    SubtotalLineDetail: Option[LineDetail] = None,
    DetailType: LineDetailTypeEnum,
    Amount: Double,
    Description: Option[String] = None,
    LineNum: Option[Double] = None
  ) extends InvoiceLine

  object SimpleEnum {

    sealed trait GlobalTaxCalculationEnum extends EnumEntry
    case object GlobalTaxCalculationEnum extends CirceEnum[GlobalTaxCalculationEnum] with Enum[GlobalTaxCalculationEnum] {
      case object TaxExcluded extends GlobalTaxCalculationEnum
      case object TaxInclusive extends GlobalTaxCalculationEnum
      case object NotApplicable extends GlobalTaxCalculationEnum
      val values: IndexedSeq[GlobalTaxCalculationEnum] = findValues
    }

    sealed trait LineDetailTypeEnum extends EnumEntry
    case object LineDetailTypeEnum extends CirceEnum[LineDetailTypeEnum] with Enum[LineDetailTypeEnum] {
      case object TaxLineDetail extends LineDetailTypeEnum
      case object SubTotalLineDetail extends LineDetailTypeEnum
      case object SalesItemLineDetail extends LineDetailTypeEnum
      case object GroupLineDetail extends LineDetailTypeEnum
      case object DescriptionOnly extends LineDetailTypeEnum
      case object DiscountLineDetail extends LineDetailTypeEnum
      val values: IndexedSeq[LineDetailTypeEnum] = findValues
    }

    sealed trait CustomFieldTypeEnum extends EnumEntry
    case object CustomFieldTypeEnum extends CirceEnum[CustomFieldTypeEnum] with Enum[CustomFieldTypeEnum] {
      case object StringType extends CustomFieldTypeEnum
      val values: IndexedSeq[CustomFieldTypeEnum] = findValues
    }

    sealed trait DeliveryTypeEnum extends EnumEntry
    case object DeliveryTypeEnum extends CirceEnum[DeliveryTypeEnum] with Enum[DeliveryTypeEnum] {
      case object Email extends DeliveryTypeEnum
      val values: IndexedSeq[DeliveryTypeEnum] = findValues
    }

  }

}
