package cz.payola.web.shared.transformators

import cz.payola.web.shared.Payola
import s2js.compiler._
import cz.payola.common.rdf.Graph

@remote object GraphVisualEditorTransformator extends GraphTransformator
{
    private val defaultPage = 0
    private val defaultRecordsOnPage = 50
    val NAME = "GraphVisualEditorTransformator"

    @async
    def transform(evaluationId: String)(successCallback: Option[Graph] => Unit)(errorCallback: Throwable => Unit) {
        successCallback(getGraphPage(evaluationId, defaultPage, defaultRecordsOnPage))
    }

    @async
    def isAvailable(input: Graph)
        (successCallback: Boolean => Unit)(errorCallback: Throwable => Unit) {

        successCallback(isAvailable(input))
    }

    def isAvailable(input: Graph): Boolean = {
        true //tripleTable is always available
    }

    @async def getSampleGraph(evaluationId: String)
        (successCallback: Graph => Unit)(errorCallback: Throwable => Unit) {
        successCallback(Payola.model.analysisResultStorageModel.getEmptyGraph())
    }


    @async def getCachedPage(evaluationId: String, page: Int = defaultPage, tripplesOnPage: Int = defaultRecordsOnPage)
        (successCallback: Option[Graph] => Unit)(errorCallback: Throwable => Unit) {

        val pageNumber = if(page > -1) page else defaultPage
        val recordsOnPage = if(tripplesOnPage > -1) tripplesOnPage else defaultRecordsOnPage
        successCallback(getGraphPage(evaluationId, pageNumber, recordsOnPage))
    }

    /**
      * Returns first page of the graph using default count of records on page
      */
    private def getGraph(evaluationId: String): Option[Graph] = {
        getGraphPage(evaluationId, defaultPage, defaultRecordsOnPage)
    }


    private def getGraphPage(evaluationId: String, pageNumber: Int, recordsOnPage: Int): Option[Graph] = {
        //            "CONSTRUCT { ?s ?p ?o } WHERE {?s ?p ?o.} ORDER BY ?s OFFSET "+((pageNumber)*recordsOnPage) + " LIMIT "+recordsOnPage,
        val resultGraph = Payola.model.analysisResultStorageModel.getGraph(
            "CONSTRUCT { ?s ?p ?o } WHERE {?s ?p ?o.} ORDER BY ?s",
            evaluationId)
        if(resultGraph.isEmpty) {
            None
        } else {
            Some(resultGraph)
        }
    }
}
