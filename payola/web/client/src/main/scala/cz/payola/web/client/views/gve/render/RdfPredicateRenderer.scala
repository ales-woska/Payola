package cz.payola.web.client.views.gve.render

import cz.payola.common.rdf.Graph
import cz.payola.web.client.views.gve.layout.LineLayout
import cz.payola.web.client.views.gve.data._
import s2js.adapters.browser.`package`._
import s2js.adapters.html
import s2js.adapters.html.Element

class RdfPredicateRenderer (
    var graph: Graph = null,
    var rdfPredicate: RdfObjectProperty = null,
    var layout: LineLayout = null) extends RdfRenderer {

    override def render(element: Element): Unit = {
        this.drawLine(element)
    }

    def drawLine(parent: Element) {
        val div = document.createElement[html.Element]("div")
        div.innerHTML = layout.title
        div.setAttribute("style", getStyle)
        parent.appendChild(div)
    }

    def getStyle = "z-index: 1; " +
        "position: absolute; " +
        "height: auto; " +
        "text-align: center; " +
        "top: " + layout.top + "px; " +
        "left: " + layout.left + "px; " +
        "width:" + layout.width + "px; " +
        "border-bottom: " + layout.lineThickness + "px " + layout.lineType + " " + layout.lineColor + ";" +
        "font-size: " + layout.fontSize + "px;" +
        "color: " + layout.fontColor + ";"
}
