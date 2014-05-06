package cz.payola.web.client.views.gve.layout

import cz.payola.common.rdf.Edge

abstract class Layout(
    var titleTypes: List[String] = List(),
    var left: Int,
    var top: Int,
    var width: Int,
    var fontColor: String,
    var fontSize: Int,
    var lineColor: String,
    var lineType: String,
    var lineThickness: Int)
{

    def this() = this(
        defaultLayout.titleTypes,
        defaultLayout.left,
        defaultLayout.top,
        defaultLayout.width,
        defaultLayout.fontColor,
        defaultLayout.fontSize,
        defaultLayout.lineColor,
        defaultLayout.lineType,
        defaultLayout.lineThickness
    )
}
