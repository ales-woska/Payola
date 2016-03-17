package cz.payola.web.client.views.gve.loaders

import cz.payola.common.rdf.Graph
import cz.payola.common.rdf.Edge
import cz.payola.common.rdf.IdentifiedVertex
import cz.payola.common.rdf.LiteralVertex
import cz.payola.web.client.views.gve.data.DataModel
import cz.payola.web.client.views.gve.data.GveTable
import cz.payola.web.client.views.gve.data.RdfObject
import cz.payola.web.client.views.gve.data.RdfProperty
import cz.payola.web.client.views.gve.data.RdfObjectProperty

object RdfDataLoader {

    def constructDataStructure(graph: Option[Graph]): DataModel = {

        var tables: List[GveTable] = List()

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
            tables ++= List(table)
        }

        val dataModel:DataModel = new DataModel(tables)
        dataModel
    }

    /**
      * Search in graph and finds all class URIs
      *
      * @param graph rdf graph
      * @return
      */
    private def getAllClassURIs(graph: Option[Graph]): List[String] = {
        val typeOfEdges = graph.get.edges.filter{e => e.uri == Edge.rdfTypeEdge}
        val classes = List[String]()
        for (e:Edge <- typeOfEdges) {
            val classUri = e.destination.toString
            if (!classes.contains(classUri)) {
                classes + classUri
            }
        }
        classes
    }

    private def getClassInstances(graph: Option[Graph], classURI: String): List[RdfObject] = {
        var instances: List[RdfObject] = List()
        for (rdfTypeEdge <- graph.get.edges.filter{e:Edge => e.uri == Edge.rdfTypeEdge && e.destination.toString == classURI}) {
            instances ++= List(getClassInstance(rdfTypeEdge.origin, graph))
        }
        instances
    }

    private def getClassInstance(origin: IdentifiedVertex, graph: Option[Graph]): RdfObject = {
        val edges = graph.get.getOutgoingEdges(origin)
        val classUri: String = edges.head.origin.uri
        val instance: RdfObject = new RdfObject(classUri)
        var objectProperties: List[RdfObjectProperty] = List()
        var literalProperties: List[RdfProperty] = List()

        for (propertyEdge: Edge <- edges) {
            val propertyVertex = propertyEdge.destination
            val propertyURI = propertyEdge.uri

            propertyVertex match {
                case l: LiteralVertex =>
                    literalProperties ++= List(new RdfProperty(instance, propertyURI, propertyVertex.toString))

                case i: IdentifiedVertex =>
                    val property: RdfObject = getClassInstance(i, graph)
                    objectProperties ++= List(new RdfObjectProperty(propertyURI, instance, property))
            }
        }

        instance.objectProperties = objectProperties
        instance.literalProperties = literalProperties
        instance
    }

}
