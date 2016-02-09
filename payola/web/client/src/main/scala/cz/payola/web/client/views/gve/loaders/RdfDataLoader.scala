package cz.payola.web.client.views.gve.loaders

import cz.payola.common.rdf._
import cz.payola.web.client.views.gve.data._

object RdfDataLoader {

    def constructDataStructure(graph: Graph): DataModel = {

        val tables: List[GveTable] = List()

        val classURIs: List[String] = this.getAllClassURIs(graph)
        for (classURI: String <- classURIs) {
            val instances: List[RdfObject] = this.getClassInstances(graph, classURI)

            val properties: Set[String] = Set()
            for (o <- instances) {
                for (p <- o.literalProperties) {
                    properties + p.propertyURI
                }
            }

            val table: GveTable = new GveTable(classURI, instances, properties.toList)
            tables :+ table
        }

        val dataModel:DataModel = new DataModel(tables.toList)
        dataModel
    }

    /**
      * Search in graph and finds all class URIs
      *
      * @param graph rdf graph
      * @return
      */
    private def getAllClassURIs(graph: Graph): List[String] = {
        val typeOfEdges = graph.edges.filter{e => e.uri == Edge.rdfTypeEdge}
        val classes = List[String]()
        for (e:Edge <- typeOfEdges) {
            val classUri = e.destination.toString
            if (!classes.contains(classUri)) {
                classes + classUri
            }
        }
        classes
    }

    private def getClassInstances(graph: Graph, classURI: String): List[RdfObject] = {
        val instances: List[RdfObject] = List()
        for (rdfTypeEdge <- graph.edges.filter{e:Edge => e.uri == Edge.rdfTypeEdge && e.destination.toString == classURI}) {
            instances :+ getClassInstance(rdfTypeEdge.origin, graph)
        }
        instances.toList
    }

    private def getClassInstance(origin: IdentifiedVertex, graph: Graph): RdfObject = {
        val edges = graph.getOutgoingEdges(origin)
        val classUri: String = edges.head.origin.uri
        val instance: RdfObject = new RdfObject(classUri)
        val objectProperties: List[RdfObjectProperty] = List()
        val literalProperties: List[RdfProperty] = List()

        for (propertyEdge: Edge <- edges) {
            val propertyVertex = propertyEdge.destination
            val propertyURI = propertyEdge.uri

            propertyVertex match {
                case l: LiteralVertex =>
                    literalProperties :+ new RdfProperty(instance, propertyURI, propertyVertex.toString)

                case i: IdentifiedVertex =>
                    val property: RdfObject = getClassInstance(i, graph)
                    objectProperties :+ new RdfObjectProperty(propertyURI, instance, property)
            }
        }

        instance.objectProperties = objectProperties.toList
        instance.literalProperties = literalProperties.toList
        instance
    }

}
