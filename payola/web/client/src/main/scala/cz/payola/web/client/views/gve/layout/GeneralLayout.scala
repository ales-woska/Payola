package cz.payola.web.client.views.gve.layout

import cz.payola.common.rdf.Edge

abstract class GeneralLayout(
    var titleTypes: List[String] = List(),
    var left: Int,
    var top: Int,
    var width: Int,
    var fontColor: String,
    var fontSize: Int,
    var lineColor: String,
    var lineType: String,
    var lineThickness: Int) {

    def this() = this(
        defaultGeneralLayout.titleTypes,
        defaultGeneralLayout.left,
        defaultGeneralLayout.top,
        defaultGeneralLayout.width,
        defaultGeneralLayout.fontColor,
        defaultGeneralLayout.fontSize,
        defaultGeneralLayout.lineColor,
        defaultGeneralLayout.lineType,
        defaultGeneralLayout.lineThickness
    )
}
