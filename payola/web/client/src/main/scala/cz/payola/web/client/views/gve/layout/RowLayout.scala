package cz.payola.web.client.views.gve.layout

class RowLayout (
    val propertyName: String,
    val titleType: List[TitleType],
    val titleResource: String,
    val aggregateFunctions: List[AggregateFunction]) {

    def getTitle: String = {
        var returnTitle: String = ""
        for (t:TitleType <- titleType) {
            if (returnTitle == "")
            {
                returnTitle = t match {
                    case TitleType.URL => propertyName
                    case TitleType.PROPERTY => propertyName
                    case TitleType.CONSTANT => titleResource
                    case _ => ""
                }
            }
        }
        returnTitle
    }

    def getValue(properties: List[String]): String = {
        if (properties.size == 1) {
            properties.head
        } else {
            var returnValue: String = ""
            for (a: AggregateFunction <- aggregateFunctions) {
                if (returnValue == "") {
                    returnValue = a match {
                        case AggregateFunction.MAX => max(properties)
                        case AggregateFunction.MIN => min(properties)
                        case AggregateFunction.AVG => avg(properties)
                        case AggregateFunction.NOTHING => flatten(properties)
                    }
                }
            }
            returnValue
        }
    }

    private def max(strings: List[String]): String = {
        var max: Int = Integer.MIN_VALUE
        for (s:String <- strings) {
            val i: Int = s.toInt
            if (i > max) max = i
        }
        max.toString
    }

    private def min(strings: List[String]): String = {
        var min: Int = Integer.MAX_VALUE
        for (s:String <- strings) {
            val i: Int = s.toInt
            if (i < min) min = i
        }
        min.toString
    }

    private def avg(strings: List[String]): String = {
        var sum: Long = 0
        for (s:String <- strings) {
            val i: Int = s.toInt
            sum += i
        }
        (sum/strings.length).toString
    }

    private def flatten(strings: List[String]): String = {
        var returns: String = strings.head
        for (s <- strings if s != strings.head) {
            returns += " " + s
        }
        returns
    }
}
