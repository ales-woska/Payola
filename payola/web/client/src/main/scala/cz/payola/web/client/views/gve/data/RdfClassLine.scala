package cz.payola.web.client.views.gve.data

import scala.collection.mutable.HashMap

class RdfClassLine(
    val URI: String,
    val forNode: String) {

    var valueProperties: HashMap[String, String] = new HashMap[String, String]
    var classProperties: HashMap[String, String] = new HashMap[String, String]
}
