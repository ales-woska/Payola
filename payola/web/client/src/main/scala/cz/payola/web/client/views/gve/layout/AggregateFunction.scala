package cz.payola.web.client.views.gve.layout

sealed trait AggregateFunction { def name: String }

object AggregateFunction {
    case object NOTHING extends AggregateFunction {val name = "nothing"}
    case object MIN extends AggregateFunction {val name = "min"}
    case object MAX extends AggregateFunction {val name = "max"}
    case object AVG extends AggregateFunction {val name = "avg"}

    def fromString(value: String): AggregateFunction = {
        if (value.eq(NOTHING.name)) {
            NOTHING
        } else if (value.eq(MIN.name)) {
            MIN
        } else if (value.eq(MAX.name)) {
            MAX
        } else if (value.eq(AVG.name)) {
            AVG
        } else {
            null
        }
    }
}