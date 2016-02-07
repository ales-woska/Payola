package cz.payola.web.client.views.gve.loaders

import cz.payola.common.rdf._
import cz.payola.web.client.views.gve.layout._
import s2js.compiler._
import cz.payola.domain.rdf.Graph
import cz.payola.web.shared.GVE
import cz.payola.web.client.views.gve.layout.TitleType

object LayoutLoader {
    final val GVE_LAYOUT_TYPE = "gve:layoutType"
    final val GVE_BLOCK_TYPE = "gve:block"
    final val GVE_LINE_TYPE = "gve:line"


    def loadLayout():ScreenLayout = {
        val blockLayouts: Array[BlockLayout] = Array()
        val lineLayouts: Array[LineLayout] = Array()

        // mock
        blockLayouts :+ (LayoutMock.CITY_LAYOUT, LayoutMock.COUNTRY_LAYOUT)
        lineLayouts :+ LayoutMock.COUNTRY_CITY_LINE



//        val test: ScreenLayout = new ScreenLayout("Test Layout", "test.woska.layout", List(), List())
//        LayoutLoader.assignLayouts(dataModel)

        new ScreenLayout("", "", blockLayouts.toList, lineLayouts.toList)
    }


    //test.blockLayouts = GVE.select(List("?layout"), "{?layout gve:Layout " + uri + " ; ?layout gve:layoutType \"gve:Block\"}")
    //test.lineLayouts = GVE.select(List("?layout"), "{?layout gve:Layout " + uri + " ; ?layout gve:layoutType \"gve:Line\"}")

    @javascript("""console.log(str)""")
    def log(str: Any) {}

    def getLayoutList: List[String] = {
        GVE.setNamedGraph("woska.example.layout")
        val test = GVE.getTest
        val layoutList = null
        List("")
    }

    def loadLayouts(url: String) {

        // val graph = new Graph(Nil, Nil, None);
        val graph: Graph = null

        val blockLayoutEdges = graph.edges.filter{e => e.uri == GVE_LAYOUT_TYPE && e.destination.toString == GVE_BLOCK_TYPE}
        var blockLayoutVertices:List[Vertex] = List()
        for (e: Edge <- blockLayoutEdges) {
            blockLayoutVertices = blockLayoutVertices ++ List(e.origin)
        }

        for (v: Vertex <- blockLayoutVertices) {
            val newBlock = new BlockLayout(v.toString)

            val edges = graph.getOutgoingEdges(v)
            for (e: Edge <- edges) {
                val value: String = e.destination.toString
                e.uri match {
                    case "gve:titleType" => newBlock.titleTypes = newBlock.titleTypes ++ List(TitleType.fromString(value))
                    case "gve:left" => newBlock.left = value.toInt
                    case "gve:top" => newBlock.top = value.toInt
                    case "gve:width" => newBlock.width = value.toInt
                    case "gve:fontColor" => newBlock.fontColor = value
                    case "gve:fontSize" => newBlock.fontSize = value.toInt
                    case "gve:lineColor" => newBlock.lineColor = value
                    case "gve:lineType" => newBlock.lineType = value
                    case "gve:lineThickness" => newBlock.lineThickness = value.toInt

                    case "gve:title" => newBlock.title = value
                    case "gve:background" => newBlock.background = value
                    case "gve:height" => newBlock.height = value.toInt
                    case "gve:verticalLines" => newBlock.verticalLines = value
                    case "gve:horizontalLines" => newBlock.horizontalLines = value
                    // case "gve:property" => newBlock.properties = newBlock.properties ++ List(value)
                }
            }
        }

        val lineLayoutEdges = graph.edges.filter{e => e.uri == GVE_LAYOUT_TYPE && e.destination.toString == GVE_LINE_TYPE}
        var lineLayoutVertices: List[Vertex] = List()
        for (e: Edge <- lineLayoutEdges) {
            lineLayoutVertices = lineLayoutVertices ++ List(e.origin)
        }

        for (v: Vertex <- lineLayoutVertices) {
            val newLine = new LineLayout("", "", "")

            val edges = graph.getOutgoingEdges(v)
            for (e: Edge <- edges) {
                val value: String = e.destination.toString
                e.uri match {
                    case "gve:titleType" => newLine.titleTypes = newLine.titleTypes ++ List(TitleType.fromString(value))
                    case "gve:left" => newLine.left = value.toInt
                    case "gve:top" => newLine.top = value.toInt
                    case "gve:width" => newLine.width = value.toInt
                    case "gve:fontColor" => newLine.fontColor = value
                    case "gve:fontSize" => newLine.fontSize = value.toInt
                    case "gve:lineColor" => newLine.lineColor = value
                    case "gve:lineType" => newLine.lineType = value
                    case "gve:lineThickness" => newLine.lineThickness = value .toInt
                    case "gve:title" => newLine.title = value
                    case "gve:fromClass" => newLine.fromClass = value
                    case "gve:toClass" => newLine.toClass = value
                }
            }
        }
    }
}
