package cz.payola.web.client.views.gve.render

import cz.payola.web.client.views.ElementView
import cz.payola.web.client.views.elements.form.fields.TextInput
import cz.payola.web.client.views.elements.{TableRow, TableHeadCell, Text, Div}
import cz.payola.web.client.views.gve.layout._
import s2js.adapters.browser.`package`._
import s2js.adapters.dom.Node
import s2js.adapters.html
import s2js.compiler.javascript
import cz.payola.web.client.views.gve.data._
import scala.collection.mutable
import cz.payola.common.rdf._

object RdfSubjectRenderer {

    private final val HEADER_INPUT_STYLE: String = "width: 30px; font-size: 9px; height: 9px;"

    private val filters: mutable.HashMap[Int, String] = new mutable.HashMap[Int, String]()

    def render(graph: Graph, element: html.Element, rdfSubject: RdfObject, blockLayout: BlockLayout): Unit = {
        val classBlockElement = document.createElement[html.Element]("div")
        classBlockElement.setAttribute("style", getDivStyle(blockLayout))

        val classTitleElement = document.createElement[html.Element]("h3")
        classTitleElement.setAttribute("style", getTableHeadStyle)
        classTitleElement.innerHTML = getTitle(rdfSubject, blockLayout)
        classBlockElement.appendChild(classTitleElement)

        val table = generateTable(graph, rdfSubject, blockLayout)
        classBlockElement.appendChild(table)
        element.appendChild(classBlockElement)
    }

    def getTitle(rdfSubject: RdfObject, blockLayout: BlockLayout): String = {
        var title = ""
        for (t: TitleType <- blockLayout.titleTypes) {
            if (title == "") {
                title = t match {
                    case TitleType.URL => rdfSubject.objectURI
                    case TitleType.LABEL => blockLayout.titleSource
                    case TitleType.CONSTANT => blockLayout.title
                    case TitleType.PROPERTY => blockLayout.getRowLayout(blockLayout.titleSource).getTitle
                    case _ => rdfSubject.objectURI
                }
            }
        }
        title
    }

    def generateTable(graph: Graph, rdfSubject: RdfObject, blockLayout: BlockLayout) = {
        val classLinesElement = document.createElement[html.Element]("table")
        classLinesElement.setAttribute("style", getTableStyle(blockLayout))

        val linesTitlesElement = document.createElement[html.Element]("tr")


        // val createDataCubePluginClicked = new SimpleUnitEvent[PluginDialog]
        val headerCellText = new Div(List(new Text("URI")))
        headerCellText.mouseClicked += { e =>
            sortByTitle(graph, rdfSubject, blockLayout, headerCellText)
            false
        }
        val headerInput = new TextInput("textFilter", filters.getOrElse(0, ""), "Filter", "form-control input-sm col-xs-2")
        headerInput.setAttribute("style", HEADER_INPUT_STYLE)
        headerInput.keyReleased += { e =>
            filterByText(headerInput)
            false
        }
        val headerCell: TableHeadCell = new TableHeadCell(List(
            headerCellText,
            headerInput
        ))

        headerCell.render(linesTitlesElement)

        var i = 1
        for (r: RdfProperty <- rdfSubject.literalProperties) {
            val headerCellText = new Div(List(new Text(r.propertyURI)))
            headerCellText.mouseClicked += { e =>
                sortByTitle(graph, rdfSubject, blockLayout, headerCellText)
                false
            }
            val headerInput = new TextInput("textFilter", filters.getOrElse(i, ""), "Filter")
            headerInput.setAttribute("style", HEADER_INPUT_STYLE)
            headerInput.keyReleased += { e =>
                filterByText(headerInput)
                false
            }
            val headerCell: TableHeadCell = new TableHeadCell(List(
                headerCellText,
                headerInput
            ))
            headerCell.render(linesTitlesElement)
            i = i + 1
        }
        classLinesElement.appendChild(linesTitlesElement)

        for (line <- rdfSubject.objectProperties) {
            val lineView = new TableRow(List())
            lineView.mouseClicked += { e =>
                this.filterLinked(graph, rdfSubject, line, lineView)
                false
            }
            val lineElement = lineView.htmlElement

            val lineValueElement = document.createElement[html.Element]("td")
            lineValueElement.setAttribute("style", getTdStyle(blockLayout))
            lineValueElement.innerHTML = line.propertyUri
            lineElement.appendChild(lineValueElement)

//            for (property: RowLayout <- blockLayout) {
//                val lineValueElement = document.createElement[html.Element]("td")
//                lineValueElement.setAttribute("style", getTdStyle(blockLayout))
//                lineValueElement.innerHTML = property.propertyName
//                lineElement.appendChild(lineValueElement)
//            }
            classLinesElement.appendChild(lineElement)
        }
        classLinesElement
    }

    def getRightLimit(blockLayout: BlockLayout) = blockLayout.left + blockLayout.width + 2

    def getLeftLimit(blockLayout: BlockLayout) = blockLayout.left

    def getTopLimit(blockLayout: BlockLayout) = blockLayout.top

    def getBottomtLimit(blockLayout: BlockLayout) = blockLayout.top + blockLayout.height + 2

    def getDivStyle(blockLayout: BlockLayout) = {
        "float: left; " +
        "position: absolute; " +
        "z-index: 2; " +
        "background-color: " + blockLayout.background + "; " +
        "border: " + blockLayout.lineThickness + "px " + blockLayout.lineType + " " + blockLayout.lineColor + "; " +
        "left:" + blockLayout.left + "px;" +
        "top: " + blockLayout.top + "px; "
    }

    def getTableStyle(blockLayout: BlockLayout) = {
        "height: " + blockLayout.height + "px; " +
        "width: " + blockLayout.width + "px; " +
        "display: block; " +
        "overflow-y: scroll; " +
        "padding: 10px;"
    }

    def getTableHeadStyle = "font-size: large; font-weight: bold; padding: 5px; text-align: center;"

    def getTdStyle(blockLayout: BlockLayout) = {
        "border-bottom: " + blockLayout.horizontalLines + "; border-right: " + blockLayout.verticalLines + ";"
    }

    @javascript("""console.log(str)""")
    def log(str: Any) {}

    var desc = true
    def sortByTitle(graph: Graph, rdfSubject: RdfObject, blockLayout: BlockLayout, sender: ElementView[html.Element]) {
        val clickedTh = sender.htmlElement.parentNode

        var pos = 0
        var firstTh: Node = sender.htmlElement.parentNode.parentNode.firstChild

        while (firstTh != clickedTh) {
            pos += 1
            firstTh = firstTh.nextSibling
        }

        var orderedList: List[RdfProperty] = null

        if (pos == 0) {
            if (desc) {
                orderedList = rdfSubject.literalProperties.sortWith((a, b) => compareStrings(a.propertyURI, b.propertyURI))
            } else {
                orderedList = rdfSubject.literalProperties.sortWith((a, b) => compareStringsDesc(b.propertyURI, a.propertyURI))
            }
        } else {
//            val propertyName = rdfSubject.literalProperties(pos - 1).propertyURI
//            if (desc) {
//                orderedList = rdfSubject.literalProperties.sortWith((a, b) => {
//                    val paS = a.valueProperties.getOrElse(propertyName, "-")
//                    val pbS = b.valueProperties.getOrElse(propertyName, "-")
//
//                    val paF = paS.toFloat
//                    val pbF = pbS.toFloat
//
//                    if (paF == Float.NaN || pbF == Float.NaN) {
//                        compareStrings(paS, pbS)
//                    } else {
//                        paF < pbF
//                    }
//                })
//            } else {
//                orderedList = rdfSubject.literalProperties.sortWith((a, b) => {
//                    val paS = a.valueProperties.getOrElse(propertyName, "-")
//                    val pbS = b.valueProperties.getOrElse(propertyName, "-")
//
//                    val paF = paS.toFloat
//                    val pbF = pbS.toFloat
//
//                    if (paF == Float.NaN || pbF == Float.NaN) {
//                        compareStringsDesc(paS, pbS)
//                    } else {
//                        paF > pbF
//                    }
//                })
//            }
        }

        desc = !desc
        rdfSubject.literalProperties = orderedList
        val tableNode = sender.htmlElement.parentNode.parentNode.parentNode
        val newTableNode = generateTable(graph, rdfSubject, blockLayout)
        hideLines(newTableNode)
        tableNode.parentNode.replaceChild(newTableNode, tableNode.parentNode.lastChild)
    }

    @javascript(""" lines.sort(function(x,y){ return scala.Predef.get().augmentString(e1.URI).$less(e2.URI); }); """)
    def sort(lines: List[RdfObjectProperty]) {}

    @javascript(""" return a < b; """)
    def compareStrings(a: String, b: String): Boolean = {a < b}

    @javascript(""" return a > b; """)
    def compareStringsDesc(a: String, b: String): Boolean = {a > b}

    def filterByText(sender: TextInput) = {
        val filter = sender.value

        val clickedTh = sender.htmlElement.parentNode

        var pos = 0
        var firstTh: Node = sender.htmlElement.parentNode.parentNode.firstChild
        while (firstTh != clickedTh) {
            pos += 1
            firstTh = firstTh.nextSibling
        }

        filters.put(pos, filter)
        val tableNode = sender.htmlElement.parentNode.parentNode.parentNode
        hideLines(tableNode)
    }

    def hideLines(table: Node) = {
        var line = table.firstChild.nextSibling

        while (line != null) {

            var pos = 0
            var firstTd: Node = line.firstChild
            var isFiltered: Boolean = false
            while (firstTd != null) {

                val tdFilter = filters.getOrElse(pos, "")

                if (tdFilter != "") {
                    if (!firstTd.textContent.contains(tdFilter)) {
                        isFiltered = true
                    }
                }

                pos += 1
                firstTd = firstTd.nextSibling
            }

            if (isFiltered) {
                hide(line)
            } else {
                show(line)
            }
            line = line.nextSibling
        }
    }

    @javascript(""" n.setAttribute("style", "display: none;"); """)
    def hide(n: Node) {}

    @javascript(""" n.setAttribute("style", "display: table-row;"); """)
    def show(n: Node) {}

    @javascript(""" n.style.background = v; """)
    def setAttribute(n: Node, v: String) {}

    def filterLinked(graph: Graph, rdfSubject: RdfObject, line: RdfObjectProperty, sender: TableRow) {

        for (property: RdfObjectProperty <- rdfSubject.objectProperties) {
//            val correctEdges = graph.getOutgoingEdges(line.propertyUri).filter{e => e.uri.toLowerCase == property.property.objectURI.toLowerCase}
//            for (e <- correctEdges) {
//                property.property.layout.filters.put(0, e.destination.toString)
//            }
//            property.
//
//            property.to.layout.hideLines(property.to.layout.table)
        }

        var tr = sender.htmlElement.parentNode.firstChild
        while (tr != null) {
            setAttribute(tr, "none")
            tr = tr.nextSibling
        }
        hideLines(sender.htmlElement.parentNode)
        setAttribute(sender.htmlElement, "pink")
    }

}
