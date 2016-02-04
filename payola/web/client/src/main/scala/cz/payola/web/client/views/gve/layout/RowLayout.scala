package cz.payola.web.client.views.gve.layout

class RowLayout (
    val propertyName: String,
    val titleType: List[TitleType],
    val titleResource: String,
    val aggregateFunctions: List[AggregateFunction]) {

    def this(
        rowName: String,
        titleType: String,
        titleResource: String,
        aggregateFunction: String) = this(rowName, List(titleType), titleResource, aggregateFunction)

    def getPropertyName: String = propertyName

    def getTitle: String = {
        var returnTitle: String = ""
        for (t:TitleType <- titleType) {
            if (returnTitle == "")
            {
                returnTitle = t match {
                    case URL => propertyName
                    case PROPERTY => propertyName
                    case CONSTANT => titleResource
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
                        case MAX => max(properties)
                        case MIN => min(properties)
                        case AVG => avg(properties)
                        case NOTHING => flatten(properties)
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
