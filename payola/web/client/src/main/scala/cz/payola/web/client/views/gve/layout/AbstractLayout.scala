package cz.payola.web.client.views.gve.layout

abstract class AbstractLayout(
    var titleTypes: List[TitleType] = List[TitleType](),
    var left: Int,
    var top: Int,
    var width: Int,
    var fontColor: String,
    var fontSize: Int,
    var lineColor: String,
    var lineType: String,
    var lineThickness: Int) {

    def this() = this(
        DefaultGeneralLayout.titleTypes,
        DefaultGeneralLayout.left,
        DefaultGeneralLayout.top,
        DefaultGeneralLayout.width,
        DefaultGeneralLayout.fontColor,
        DefaultGeneralLayout.fontSize,
        DefaultGeneralLayout.lineColor,
        DefaultGeneralLayout.lineType,
        DefaultGeneralLayout.lineThickness
    )
}

object DefaultGeneralLayout {
    //         var titleTypes: List[TitleType] = Edge.rdfLabelEdges ++ List(Edge.rdfTypeEdge)
    var titleTypes: List[TitleType] = List(TitleType.PROPERTY)
    var left: Int = 10
    var top: Int = 10
    val width = 500
    var fontColor: String = "black"
    var fontSize: Int = 10
    var lineColor: String = "black"
    var lineType: String = "solid"
    var lineThickness: Int = 1
}
