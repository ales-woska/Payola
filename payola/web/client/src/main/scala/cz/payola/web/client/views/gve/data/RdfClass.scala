package cz.payola.web.client.views.gve.data

import cz.payola.common.rdf._
import cz.payola.web.client.views.gve.layout._
import scala.collection.mutable.HashMap
import s2js.compiler.javascript

class RdfClass(
    val graph:Graph,
    val URI: String) {

    var layout: BlockLayout = new BlockLayout()
    var lines: List[RdfClassLine] = List()

    var classPropertiesUris: List[String] = List()
    var classPropertiesClassTypes: HashMap[String, String] = new HashMap[String, String]()
    var classProperties: List[RdfProperty] = List()

    def setLayout(layout: BlockLayout) = {
        this.layout = layout
    }

    for (e <- graph.edges.filter{e:Edge => e.uri == Edge.rdfTypeEdge && e.destination.toString == URI}) {
        val origin: Vertex = e.origin
        val newLine = new RdfClassLine(e.origin.uri, e.destination.toString)

        val connectedEdges = graph.getOutgoingEdges(origin)

        for (propertyEdge: Edge <- connectedEdges) {
            val propertyVertex = propertyEdge.destination

            propertyVertex match {
                case l: LiteralVertex => newLine.valueProperties.put(propertyEdge.uri, propertyVertex.toString)
                case i: IdentifiedVertex => {
                    val propertyVertexEdges = graph.getOutgoingEdges(propertyVertex)
                    val propertyVertexTypes = propertyVertexEdges.filter{e: Edge => e.uri == Edge.rdfTypeEdge}
                    if (propertyVertexTypes.size > 0) {
                        val propertyType = propertyVertexTypes.head.destination.toString
                        if (classPropertiesUris.contains(propertyEdge.uri) == false) {
                            classPropertiesUris = classPropertiesUris ++ List(propertyEdge.uri)
                            classPropertiesClassTypes.put(propertyEdge.uri, propertyType)
                        }
                    }
                    true
                }
            }
        }

        lines = lines ++ List(newLine)
    }


    @javascript("""console.log(str)""")
    def log(str: Any) {}

    def connectClasses(structure: RdfStructure) = {
        for (propertyUri <- classPropertiesUris) {
            val connectedClassType: String = classPropertiesClassTypes.getOrElse(propertyUri, "-")
            val property = new RdfProperty(graph, propertyUri, this, structure.getClass(connectedClassType))
            classProperties = classProperties ++ List(property)
        }
    }

    def getTitle: String = {
        var title = ""
        for (t: String <- layout.titleTypes) {
            if (title == "") {
                title = t match {
                    case "url" => URI
                    case "label" => graph.getOutgoingEdges(URI).filter{e:Edge => Edge.rdfLabelEdges.contains(e.uri)}.head.toString
                    case "constant" => layout.title
                    case "other" => graph.getOutgoingEdges(URI).filter{e:Edge => e.uri == title}.head.toString
                    case _ => URI
                }
            }
        }
        title
    }

    def getAllValuePropertyNames: List[String] = {
        var props: List[String] = List[String]()

        for (line: RdfClassLine <- lines) {
            for ((key: String, value: String) <- line.valueProperties) {
                props = props ++ List(key)
            }
        }

        props
    }

    def getAllClassPropertyNames: List[String] = {
        var props: List[String] = List()

        for (line: RdfClassLine <- lines) {
            for ((key: String, value: String) <- line.classProperties) {
                props = props ++ List(key)
            }
        }

        props
    }

    def getAllClassPropertyValues: List[String] = {
        var props: List[String] = List()

        for (line: RdfClassLine <- lines) {
            for ((key: String, value: String) <- line.classProperties) {
                props = props ++ List(value)
            }
        }

        props
    }
}
