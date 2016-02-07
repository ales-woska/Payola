package cz.payola.web.client.views.gve.render

import cz.payola.common.rdf.Graph
import cz.payola.web.client.views.gve.layout.LineLayout
import cz.payola.web.client.views.gve.data._
import s2js.adapters.browser.`package`._
import s2js.adapters.html
import s2js.adapters.html.Element

object RdfPredicateRenderer {

    def render(element: Element, layout: LineLayout): Unit = {
        val div = document.createElement[html.Element]("div")
        div.innerHTML = layout.title
        div.setAttribute("style", getStyle(layout))
        element.appendChild(div)
    }

    def getStyle(layout: LineLayout) = {
        "z-index: 1; " +
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
}
