package cz.payola.web.client.views.gve.data

/**
  * Rdf class instance - line in GveTable, its literal properties makes columns.
  * @param objectURI URI of the instance
  */
class RdfObject (
    val objectURI: String) {
    var objectProperties: List[RdfObjectProperty] = null
    var literalProperties: List[RdfProperty] = null
}
