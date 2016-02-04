package cz.payola.web.client.views.gve.render

import cz.payola.common.rdf._
import cz.payola.web.client.views.ElementView
import cz.payola.web.client.views.elements.form.fields.TextInput
import cz.payola.web.client.views.elements.{TableRow, TableHeadCell, Text, Div}
import cz.payola.web.client.views.gve.layout._
import s2js.adapters.browser.`package`._
import s2js.adapters.dom.Node
import s2js.adapters.html
import s2js.compiler.javascript
import cz.payola.web.client.views.gve.data._
import scala.collection.mutable.HashMap

class RdfSubjectRenderer(
    var graph: Graph,
    var rdfSubject: RdfObject,
    var blockLayout: BlockLayout) extends RdfRenderer {

    private final val HEADER_INPUT_STYLE: String = "width: 30px; font-size: 9px; height: 9px;"

    private val filters: HashMap[Int, String] = new HashMap[Int, String]()

    override def render(element: html.Element): Unit = {
        this.drawBlock(element)
    }

    def drawBlock(element: html.Element) {
        val classBlockElement = document.createElement[html.Element]("div")
        classBlockElement.setAttribute("style", getDivStyle)

        val classTitleElement = document.createElement[html.Element]("h3")
        classTitleElement.setAttribute("style", getTableHeadStyle)
        classTitleElement.innerHTML = getTitle()
        classBlockElement.appendChild(classTitleElement)

        val table = generateTable()
        classBlockElement.appendChild(table)
        element.appendChild(classBlockElement)
    }

    @javascript("""console.log(str)""")
    def log(str: Any) {}

    def getTitle(): String = {
        var title = ""
        for (t: TitleType <- blockLayout.titleTypes) {
            if (title == "") {
                title = t match {
                    case URL => rdfSubject.URI
                    case LABEL => blockLayout.titleSouce
                    case CONSTANT => blockLayout.title
                    case PROPERTY => blockLayout.getRowLayout(blockLayout.titleSouce).getTitle
                    case _ => rdfSubject.URI
                }
            }
        }
        title
    }

    def generateTable() = {
        val classLinesElement = document.createElement[html.Element]("table")
        classLinesElement.setAttribute("style", getTableStyle)

        val linesTitlesElement = document.createElement[html.Element]("tr")


        // val createDataCubePluginClicked = new SimpleUnitEvent[PluginDialog]
        val headerCellText = new Div(List(new Text("URI")))
        headerCellText.mouseClicked += { e =>
            sortByTitle(headerCellText)
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
            val headerCellText = new Div(List(new Text(r.uri)))
            headerCellText.mouseClicked += { e =>
                sortByTitle(headerCellText)
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

        for (line: RdfObjectProperty <- rdfSubject.literalProperties) {
            val lineView = new TableRow(List())
            lineView.mouseClicked += { e =>
                this.filterLinked(line, lineView)
                false
            }
            val lineElement = lineView.htmlElement

            val lineValueElement = document.createElement[html.Element]("td")
            lineValueElement.setAttribute("style", getTdStyle)
            lineValueElement.innerHTML = line.uri
            lineElement.appendChild(lineValueElement)

            for (property: RowLayout <- properties) {
                val lineValueElement = document.createElement[html.Element]("td")
                lineValueElement.setAttribute("style", getTdStyle)
                lineValueElement.innerHTML = line.valueProperties.getOrElse(property.propertyName, "-")
                lineElement.appendChild(lineValueElement)
            }
            classLinesElement.appendChild(lineElement)
        }
        classLinesElement
    }

    def getRightLimit = blockLayout.left + blockLayout.width + 2

    def getLeftLimit = blockLayout.left

    def getTopLimit = blockLayout.top

    def getBottomtLimit = blockLayout.top + blockLayout.height + 2

    def getDivStyle = "float: left; " +
        "position: absolute; " +
        "z-index: 2; " +
        "background-color: " + blockLayout.background + "; " +
        "border: " + blockLayout.lineThickness + "px " + blockLayout.lineType + " " + blockLayout.lineColor + "; " +
        "left:" + blockLayout.left + "px;" +
        "top: " + blockLayout.top + "px; "

    def getTableStyle = "height: " + blockLayout.height + "px; " +
        "width: " + blockLayout.width + "px; " +
        "display: block; " +
        "overflow-y: scroll; " +
        "padding: 10px;"

    def getTableHeadStyle = "font-size: large; font-weight: bold; padding: 5px; text-align: center;"

    def getTdStyle = "border-bottom: " + blockLayout.horizontalLines + "; border-right: " + blockLayout.verticalLines + ";"

    @javascript("""console.log(str)""")
    def log(str: Any) {}

    var desc = true
    def sortByTitle(sender: ElementView[html.Element]) {
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
                orderedList = rdfSubject.literalProperties.sortWith((a, b) => compareStrings(a.uri, b.uri))
            } else {
                orderedList = rdfSubject.literalProperties.sortWith((a, b) => compareStringsDesc(b.uri, a.uri))
            }
        } else {
            val propertyName = rdfSubject.literalProperties(pos - 1).uri
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
        val newTableNode = generateTable()
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

    def filterLinked(line: RdfObjectProperty, sender: TableRow) {

        for (property: RdfObjectProperty <- rdfSubject.objectProperties) {
//            val correctEdges = rdfSubject.graph.getOutgoingEdges(line.URI).filter{e => e.uri.toLowerCase == property.to.URI.toLowerCase}
//            for (e <- correctEdges) {
//                property.to.layout.filters.put(0, e.destination.toString)
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
