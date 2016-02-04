package cz.payola.web.client.views.gve.layout

sealed trait AggregateFunction { def name: String }
case object NOTHING extends AggregateFunction {val name = "nothing"}
case object MIN extends AggregateFunction {val name = "min"}
case object MAX extends AggregateFunction {val name = "max"}
case object AVG extends AggregateFunction {val name = "avg"}