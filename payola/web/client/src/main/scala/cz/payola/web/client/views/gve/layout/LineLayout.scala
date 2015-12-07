package cz.payola.web.client.views.gve.layout

import s2js.adapters.html
import s2js.adapters.browser._
import cz.payola.web.client.views.gve.data._
import s2js.compiler.javascript
import cz.payola.web.shared.GVE

class LineLayout (
    var uri: String,
    var fromClass: String,
    var toClass: String,
    var title: String = "")
extends GeneralLayout {

    var points: List[(Int, Int)] = List()

    @javascript("""console.log(str)""")
    def log(str: Any) {}

    def drawLine(property: RdfProperty, parent: html.Element) {
        val div = document.createElement[html.Element]("div")
        div.innerHTML = property.getTitle
        div.setAttribute("style", getStyle)
        parent.appendChild(div)
    }



    def store(graph: String, layout: ScreenLayout) = {
        var data = List(uri + " gve:layout xxx ",
            uri + " gve:titleType " + this.titleTypes,
            uri + " gve:left " + this.left,
            uri + " gve:top " + this.top,
            uri + " gve:width " + this.width,
            uri + " gve:fontColor " + this.fontColor,
            uri + " gve:fontSize " + this.fontSize,
            uri + " gve:lineColor " + this.lineColor,
            uri + " gve:lineType " + this.lineType,
            uri + " gve:lineThickness " + this.lineThickness,
            uri + " gve:title " + this.title,
            uri + " gve:fromClass " + this.fromClass,
            uri + " gve:toClass " + this.toClass)
        GVE.insert(graph, data)
    }

    def from(): (Int, Int) = (left, top)
    def to(): (Int, Int) = (left + width, top)

    def getStyle = "z-index: 1; " +
        "position: absolute; " +
        "height: auto; " +
        "text-align: center; " +
        "top: " + top + "px; " +
        "left: " + left + "px; " +
        "width:" + width + "px; " +
        "border-bottom: " + lineThickness + "px " + lineType + " " + lineColor + ";" +
        "font-size: " + fontSize + "px;" +
        "color: " + fontColor + ";"
}
