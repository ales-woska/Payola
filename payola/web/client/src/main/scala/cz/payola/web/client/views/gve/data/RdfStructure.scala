package cz.payola.web.client.views.gve.data

import cz.payola.common.rdf._
import s2js.adapters.html

class RdfStructure (val graph: Graph) {
    var classes: List[RdfClass] = List()
    var lines: List[RdfProperty] = List()
    this.constructDataStructure()

    def constructDataStructure() = {
        // find all classes
        val typeOfEdges = graph.edges.filter{e => e.uri == Edge.rdfTypeEdge}
        var classes = List[String]()
        for (e:Edge <- typeOfEdges) {
            val classUri = e.destination.toString
            if (classes.contains(classUri) == false) {
                classes = classes ++ List(classUri)
            }
        }

        // create founded classes
        for (c <- classes) {
            addClass(c)
        }

        for (c: RdfClass <- this.classes) {
            c.connectClasses(this)
        }
    }

    def getClass(name: String): RdfClass = {
        var returnClass: RdfClass = null
        for (c: RdfClass <- this.classes) {
           if (c.URI == name) {
               returnClass = c
           }
        }
        returnClass
    }

    def addClass(newClassName: String) {
        val newClass = new RdfClass(graph, newClassName)
        classes = classes ++ List(newClass)
    }

    def draw(parent: html.Element) {
        for (c <- classes) {
            c.layout.drawBlock(this, parent)
        }
        for (c: RdfClass <- classes) {
            for (l: RdfProperty <- c.classProperties) {
                l.draw(parent)
            }
        }
    }

    def drawAllBlocks(parent: html.Element) {
        for (c <- classes) {
            c.layout.drawBlock(this, parent)
        }
    }

    def getLine(from: String, to: String): RdfProperty = {
        val line = lines.filter{l: RdfProperty => l.from.URI == from & l.to.URI == to}
        line.head
    }

    def addLine(newLineName: String, from: String, to: String) {
        val newLine = new RdfProperty(graph, newLineName, getClass(from), getClass(to))
        lines = lines ++ List(newLine)
    }

    def addLine(newLineName: String, from: RdfClass, to: RdfClass) {
        val newLine = new RdfProperty(graph, newLineName, from, to)
        lines = lines ++ List(newLine)
    }
}
