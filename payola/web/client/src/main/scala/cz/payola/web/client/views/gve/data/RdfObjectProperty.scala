package cz.payola.web.client.views.gve.data

/**
  * Property which value is RdfSubject
  * @param propertyUri URI of this property
  * @param subject RDF subject (source)
  * @param property RDF object (destination)
  */
class RdfObjectProperty(
    val propertyUri: String,
    val subject: RdfObject,
    val property: RdfObject) {
}
