package cz.payola.web.client.views.gve.layout

import cz.payola.common.rdf.Edge

object defaultGeneralLayout {
    var titleTypes: List[String] = Edge.rdfLabelEdges ++ List(Edge.rdfTypeEdge)
    var left: Int = 10
    var top: Int = 10
    val width = 500
    var fontColor: String = "black"
    var fontSize: Int = 10
    var lineColor: String = "black"
    var lineType: String = "solid"
    var lineThickness: Int = 1
}
