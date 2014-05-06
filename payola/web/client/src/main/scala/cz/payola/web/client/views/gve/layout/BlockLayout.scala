package cz.payola.web.client.views.gve.layout

import s2js.adapters._
import s2js.adapters.browser._
import cz.payola.web.client.views.gve.data._
import cz.payola.web.client.views.elements._
import cz.payola.web.client.events.SimpleUnitEvent
import cz.payola.web.client.views.ElementView
import java.util.Arrays.sort
import s2js.compiler._
import cz.payola.web.client.views.bootstrap.InputControl
import cz.payola.web.client.views.elements.form.fields.TextInput
import scala.collection.mutable.HashMap
import org.w3c.dom.Attr
import s2js.adapters.dom.Node
import s2js.adapters.dom.Element

class BlockLayout(
    val forClass: String,
    var title: String = "",
    var background: String,
    var height: Int,
    var verticalLines: String,
    var horizontalLines: String,
    var properties: List[Row])
extends Layout() {

    def this(forClass: String, title: String) = this(
        forClass,
        title,
        defaultBlockLayout.background,
        defaultBlockLayout.height,
        defaultBlockLayout.horizontalLines,
        defaultBlockLayout.verticalLines,
        defaultBlockLayout.properties
    )

    def this(forClass: String) = this(forClass, "")
    def this() = this("")

    def getRow(id: String): Row = {
        val rows = properties.filter {p => p.getPropertyName == id}
        rows.head
    }

    var thisClass: RdfClass = null
    var globalStructure: RdfStructure = null
    var parentElement: html.Element = null
    var table: html.Element = null

    def drawBlock(structure: RdfStructure, parent: html.Element) {
        this.parentElement = parent
        this.globalStructure = structure
        thisClass = structure.getClass(forClass)

        val classBlockElement = document.createElement[html.Element]("div")
        classBlockElement.setAttribute("style", getDivStyle)

        val classTitleElement = document.createElement[html.Element]("h3")
        classTitleElement.setAttribute("style", getTableHeadStyle)
        classTitleElement.innerHTML = thisClass.getTitle
        classBlockElement.appendChild(classTitleElement)

        table = generateTable()
        classBlockElement.appendChild(table)
        parentElement.appendChild(classBlockElement)
    }

    def headerInputStyle = "width: 30px; font-size: 9px; height: 9px;"

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
        headerInput.setAttribute("style", headerInputStyle)
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
        for (r: Row <- properties) {
            val headerCellText = new Div(List(new Text(r.getTitle)))
            headerCellText.mouseClicked += { e =>
                sortByTitle(headerCellText)
                false
            }
            val headerInput = new TextInput("textFilter", filters.getOrElse(i, ""), "Filter")
            headerInput.setAttribute("style", headerInputStyle)
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

        for (line: RdfClassLine <- thisClass.lines) {
            val lineView = new TableRow(List())
            lineView.mouseClicked += { e =>
                this.filterLinked(line, lineView)
                false
            }
            val lineElement = lineView.htmlElement

            val lineValueElement = document.createElement[html.Element]("td")
            lineValueElement.setAttribute("style", getTdStyle)
            lineValueElement.innerHTML = line.URI
            lineElement.appendChild(lineValueElement)

            for (property: Row <- properties) {
                val lineValueElement = document.createElement[html.Element]("td")
                lineValueElement.setAttribute("style", getTdStyle)
                lineValueElement.innerHTML = line.valueProperties.getOrElse(property.propertyName, "-")
                lineElement.appendChild(lineValueElement)
            }
            classLinesElement.appendChild(lineElement)
        }
        classLinesElement
    }

    def getRightLimit = left + width + 2

    def getLeftLimit = left

    def getTopLimit = top

    def getBottomtLimit = top + height + 2

    def getDivStyle = "float: left; " +
        "position: absolute; " +
        "z-index: 2; " +
        "background-color: " + background + "; " +
        "border: " + lineThickness + "px " + lineType + " " + lineColor + "; " +
        "left:" + left + "px;" +
        "top: " + top + "px; "

    def getTableStyle = "height: " + height + "px; " +
        "width: " + width + "px; " +
        "display: block; " +
        "overflow-y: scroll; " +
        "padding: 10px;"

    def getTableHeadStyle = "font-size: large; font-weight: bold; padding: 5px; text-align: center;"

    def getTdStyle = "border-bottom: " + horizontalLines + "; border-right: " + verticalLines + ";"

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

        var orderedList: List[RdfClassLine] = null

        if (pos == 0) {
            if (desc) {
                orderedList = thisClass.lines.sortWith((a, b) => compareStrings(a.URI, b.URI))
            } else {
                orderedList = thisClass.lines.sortWith((a, b) => compareStringsDesc(a.URI, b.URI))
            }
        } else {
            val propertyName = properties(pos - 1).propertyName

            if (desc) {
                orderedList = thisClass.lines.sortWith((a, b) => {
                    val paS = a.valueProperties.getOrElse(propertyName, "-")
                    val pbS = b.valueProperties.getOrElse(propertyName, "-")

                    val paF = paS.toFloat
                    val pbF = pbS.toFloat

                    if (paF == Float.NaN || pbF == Float.NaN) {
                        compareStrings(paS, pbS)
                    } else {
                        paF < pbF
                    }
                })
            } else {
                orderedList = thisClass.lines.sortWith((a, b) => {
                    val paS = a.valueProperties.getOrElse(propertyName, "-")
                    val pbS = b.valueProperties.getOrElse(propertyName, "-")

                    val paF = paS.toFloat
                    val pbF = pbS.toFloat

                    if (paF == Float.NaN || pbF == Float.NaN) {
                        compareStringsDesc(paS, pbS)
                    } else {
                        paF > pbF
                    }
                })
            }
        }

        desc = !desc
        thisClass.lines = orderedList
        val tableNode = sender.htmlElement.parentNode.parentNode.parentNode
        val newTableNode = generateTable()
        hideLines(newTableNode)
        tableNode.parentNode.replaceChild(newTableNode, tableNode.parentNode.lastChild)
    }

    @javascript(""" lines.sort(function(x,y){ return scala.Predef.get().augmentString(e1.URI).$less(e2.URI); }); """)
    def sort(lines: List[RdfClassLine]) {}

    @javascript(""" return a < b; """)
    def compareStrings(a: String, b: String): Boolean = {a < b}

    @javascript(""" return a > b; """)
    def compareStringsDesc(a: String, b: String): Boolean = {a > b}

    var filters: HashMap[Int, String] = new HashMap[Int, String]()

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

    def filterLinked(line: RdfClassLine, sender: TableRow) {

        for (property: RdfProperty <- thisClass.classProperties) {
            val correctEdges = thisClass.graph.getOutgoingEdges(line.URI).filter{e => e.uri.toLowerCase == property.to.URI.toLowerCase}
            for (e <- correctEdges) {
                property.to.layout.filters.put(0, e.destination.toString)
            }
            property.to.layout.hideLines(property.to.layout.table)
        }

        var tr = sender.htmlElement.parentNode.firstChild
        while (tr != null) {
            setAttribute(tr, "none")
            tr = tr.nextSibling
        }
        hideLines(sender.htmlElement.parentNode)
        setAttribute(sender.htmlElement, "pink")
    }

    @javascript(""" n.style.background = v; """)
    def setAttribute(n: Node, v: String) {}
}
