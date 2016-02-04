package cz.payola.web.client.views.gve.data

/**
  * GveTable is table which contains subjects as lines. It's columns are RdfProperties from within subjects.
  * @param classURI: Uri of rdf class of this table (each table has exactly one rdf cass)
  * @param instances: class instances
  * @param columnsURIs: list of columns identified by their URIs
  */
class GveTable (
    val classURI: String,
    val instances: List[RdfObject],
    val columnsURIs: List[String]) {
}
