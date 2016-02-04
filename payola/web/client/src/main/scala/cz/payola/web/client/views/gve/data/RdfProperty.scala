package cz.payola.web.client.views.gve.data

/**
  * Concrete cell in GveTable. propertyUrl is same as columnsURIs in GveTable.
  * @param subject Instance (table row) where this property belongs
  * @param propertyURI URI of this property
  * @param value concrete property value - content of table cell
  */
class RdfProperty (
    val subject: RdfObject,
    val propertyURI: String,
    val value: Any) {
}
