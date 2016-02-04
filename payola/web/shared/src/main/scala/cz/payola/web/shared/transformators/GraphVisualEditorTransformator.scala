package cz.payola.web.shared.transformators

import cz.payola.web.shared.Payola
import s2js.compiler._
import scala.collection.immutable
import scala.collection.mutable
import cz.payola.common.rdf._

@remote object GraphVisualEditorTransformator extends GraphTransformator {

    final val NAME = "GraphVisualEditorTransformator"

    @async
    def transform(evaluationId: String)(successCallback: Option[Graph] => Unit)(errorCallback: Throwable => Unit) {
        successCallback(getGraphPage(evaluationId))
    }

    @async
    def isAvailable(input: Graph)(successCallback: Boolean => Unit)(errorCallback: Throwable => Unit) {
        successCallback(isAvailable(input))
    }

    def isAvailable(input: Graph): Boolean = true

    @async def getSampleGraph(evaluationId: String)(successCallback: Graph => Unit)(errorCallback: Throwable => Unit) {
        successCallback(Payola.model.analysisResultStorageModel.getEmptyGraph())
    }

    @async def getCachedPage(evaluationId: String)(successCallback: Option[Graph] => Unit)(errorCallback: Throwable => Unit) {
        successCallback(getGraphPage(evaluationId))
    }

    private def getGraph(evaluationId: String): Option[Graph] = {
        getGraphPage(evaluationId)
    }

    private def getGraphPage(evaluationId: String): Option[Graph] = {
        /*
        val offset = (pageNumber)*recordsOnPage
        val query = "CONSTRUCT { ?s ?p ?o } WHERE {?s ?p ?o.} ORDER BY ?s OFFSET "+ offset + " LIMIT "+ recordsOnPage
        */
        val query = "CONSTRUCT { ?s ?p ?o } WHERE {?s ?p ?o.} ORDER BY ?s"
        val resultGraph = Payola.model.analysisResultStorageModel.getGraph(query, evaluationId)
        if (resultGraph.isEmpty) {
            None
        } else {
            Some(resultGraph)
        }
    }
}
