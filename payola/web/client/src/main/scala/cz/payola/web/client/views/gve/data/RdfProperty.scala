package cz.payola.web.client.views.gve.data

import cz.payola.web.client.views.gve.layout._
import s2js.adapters.html
import s2js.adapters.browser.`package`._
import cz.payola.common.rdf._
import s2js.compiler.javascript

class RdfProperty(
    val graph: Graph,
    val classType: String,
    val from: RdfClass,
    val to: RdfClass) {

    var layout: LineLayout = new LineLayout("", "", "")

    def setLayout(lineLayout: LineLayout) {
        this.layout = lineLayout
    }

    @javascript("""console.log(str)""")
    def log(str: Any) {}

    def draw(parent: html.Element) {
        layout.drawLine(this, parent)
    }

    def getTitle: String = {
        var title = ""
        for (t: String <- layout.titleTypes) {
            if (title == "") {
                title = t match {
                    case "url" => classType
                    case "label" => graph.getOutgoingEdges(from.URI).filter{e:Edge => Edge.rdfLabelEdges.contains(e.uri)}.head.toString
                    case "constant" => layout.title
                    case "other" => graph.getOutgoingEdges(from.URI).filter{e:Edge => e.uri == title}.head.toString
                    case _ => classType
                }
            }
        }
        title
    }
}
