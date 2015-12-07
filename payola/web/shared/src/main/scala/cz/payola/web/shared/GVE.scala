package cz.payola.web.shared

import s2js.compiler._
import cz.payola.domain.rdf._

@remote @secured object GVE {
    var uri: String = null
    var sparqlEndpoint: SparqlEndpoint = null
    var namedGraph: String = null

    @remote def setUri(newUri: String) {
        this.uri = newUri
        this.sparqlEndpoint = new SparqlEndpoint(uri)
    }

    @remote def setNamedGraph(newGraphName: String) {
        if (newGraphName != null && !newGraphName.isEmpty()) {
            this.namedGraph = newGraphName
        }
    }


    @remote def getTest: cz.payola.common.rdf.Graph = {
        val query = "SELECT * FROM <" + namedGraph + "> WHERE { ?s ?p ?o }"
        val ret: Graph = sparqlEndpoint.executeQuery(query)
        ret.asInstanceOf[cz.payola.common.rdf.Graph]
    }

    @async def select(row: String, constraints: String)
        (successCallback: (cz.payola.common.rdf.Graph => Unit))(failCallback: (Throwable => Unit)) {

        val rows: List[String] = List(row)
        val ret:cz.payola.common.rdf.Graph = null
        successCallback(ret)
    }

    @async def select(rows: List[String], constraints: String)
        (successCallback: (cz.payola.common.rdf.Graph => Unit))(failCallback: (Throwable => Unit)) {

        if (sparqlEndpoint == null) {
            throw new Exception("Sparql endpoint is not set")
        }

        val query: StringBuilder = new StringBuilder("SELECT ")
        query.append(rows.mkString(", "))
        if (namedGraph != null && namedGraph != "") {
            query.append(" FROM <").append(namedGraph).append(">")
        }
        if (constraints != "") {
            query.append(" WHERE ").append(constraints)
        }

        val ret = sparqlEndpoint.executeQuery(query.toString).asInstanceOf[cz.payola.common.rdf.Graph]
        successCallback(ret)
    }

    @remote def insert(graph: String, values: List[String]): cz.payola.common.rdf.Graph = {
        if (sparqlEndpoint == null) {
            throw new Exception("Sparql endpoint is not set")
        }
        var query:String = "INSERT INTO <" + graph + "> {"
        for (value <- values) {
            query += value + " ."
        }
        query += "}"

        val response: Graph = sparqlEndpoint.executeQuery(query)
        response.asInstanceOf[cz.payola.common.rdf.Graph]
    }

    @remote def insert(graph: String, value: String): cz.payola.common.rdf.Graph = {
        insert(graph, List(value))
    }

    @remote def delete(graph: String, values: List[String]): cz.payola.common.rdf.Graph = {
        if (sparqlEndpoint == null) {
            throw new Exception("Sparql endpoint is not set")
        }
        var query:String = "DELETE DATA FROM <" + graph + "> {"
        for (value <- values) {
            query += value + " ."
        }
        query += "}"

        val response: Graph = sparqlEndpoint.executeQuery(query)
        response.asInstanceOf[cz.payola.common.rdf.Graph]
    }
}
