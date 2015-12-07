package cz.payola.web.client.views.gve.loaders

import cz.payola.common.rdf._
import cz.payola.web.client.views.gve.data._
import cz.payola.web.client.views.gve.layout._
import s2js.compiler._
import cz.payola.domain.rdf.Graph
import cz.payola.web.shared.GVE

object LayoutLoader {

    var loaded: Boolean = false
    def isLoaded: Boolean = loaded

    var blockLayouts: List[BlockLayout] = List()
    var lineLayouts: List[LineLayout] = List()

    def addBlockLayout(block: BlockLayout) {
        this.blockLayouts ::= block
    }

    def addLineLayout(line: LineLayout) {
        this.lineLayouts ::= line
    }

    def createDemoLayouts() {
        val country = new BlockLayout("http://dbpedia.org/ontology/Country")
        country.title = "Country"
        country.background = "#CCFF33"
        country.lineThickness = 2
        country.lineType = "solid"
        country.lineColor = "black"
        country.height = 250
        country.width = 370
        country.left = 980
        country.top = 10
        country.properties = List(
            new Row("http://dbpedia.org/ontology/areaTotal", List("constant"), "Area", "nothing"))
        country.horizontalLines = "1px solid gray"
        country.verticalLines = "1px solid silver"
        country.titleTypes = List[String]("constant")

        val city = new BlockLayout("http://dbpedia.org/ontology/City")
        city.title = "City"
        city.background = "#CCFF33"
        city.lineThickness = 2
        city.lineType = "solid"
        city.lineColor = "black"
        city.height = 250
        city.width = 700
        city.left = 10
        city.top = 10
        city.properties = List(
            new Row("http://dbpedia.org/ontology/populationTotal", List("constant"), "Population", "nothing"),
            new Row("http://dbpedia.org/ontology/populationDensity", List("constant"), "Density", "nothing"))
        city.horizontalLines = "1px solid gray"
        city.verticalLines = "1px solid silver"
        city.titleTypes = List[String]("constant")

        val line = new LineLayout(city.forClass, country.forClass, "In country")
        line.lineThickness = 1
        line.lineColor = "black"
        line.lineType = "solid"
        line.width = 2
        line.fontColor = "gray"
        line.fontSize = 8
        line.width = 246
        line.left = 734
        line.top = 75
        line.titleTypes = List[String]("constant")

        blockLayouts = blockLayouts ++ List(city, country)
        lineLayouts = lineLayouts ++ List(line)

        loaded = true
    }

    def assignLayouts(dataStructure: RdfStructure) = {
        for (c: RdfClass <- dataStructure.classes) {
            c.setLayout(getBlockLayout(c.URI))

            for (l: RdfProperty <- c.classProperties) {
                val layout = getLineLayout(l)
                l.setLayout(layout)
            }
        }
    }

    def getBlockLayout(classType: String): BlockLayout = {
        var returnBlock: BlockLayout = null
        for (b: BlockLayout <- this.blockLayouts) {
            if (b.forClass == classType) {
                returnBlock = b
            }
        }
        returnBlock
    }

    @javascript("""console.log(str)""")
    def log(str: Any) {}

    def getLineLayout(property: RdfProperty): LineLayout = {
        val bs = lineLayouts.filter(l => l.fromClass == property.from.URI && l.toClass == property.to.URI)
        bs.head
    }

    def getLayoutList: List[String] = {
        GVE.setNamedGraph("woska.example.layout")
        val test = GVE.getTest
        val layoutList = null
        List("")
    }



    val LAYOUT_TYPE = "gve:layoutType"
    val BLOCK_TYPE = "gve:block"
    val LINE_TYPE = "gve:line"

    def loadLayouts(url: String) {

        // val graph = new Graph(Nil, Nil, None);
        val graph: Graph = null

        val blockLayoutEdges = graph.edges.filter{e => e.uri == LAYOUT_TYPE && e.destination.toString == BLOCK_TYPE}
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
                    case "gve:titleType" => newBlock.titleTypes = newBlock.titleTypes ++ List(value)
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

            addBlockLayout(newBlock)
        }

        val lineLayoutEdges = graph.edges.filter{e => e.uri == LAYOUT_TYPE && e.destination.toString == LINE_TYPE}
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
                    case "gve:titleType" => newLine.titleTypes = newLine.titleTypes ++ List(value)
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
                    // case "gve:point" => newLine.points = newLine.points ++ List(value)
                }
            }

            addLineLayout(newLine)
        }





        loaded = true
    }

}
