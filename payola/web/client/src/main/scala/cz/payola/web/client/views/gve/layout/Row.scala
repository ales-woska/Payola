package cz.payola.web.client.views.gve.layout

import s2js.adapters.html
import s2js.adapters.browser._
import cz.payola.web.client.views.elements._
import cz.payola.web.client.views.ElementView

class Row (
    val propertyName: String,
    val titleType: List[String],
    val titleResource: String,
    val aggregateFunction: String) {

    def this(
        rowName: String,
        titleType: String,
        titleResource: String,
        aggregateFunction: String) = this(rowName, List(titleType), titleResource, aggregateFunction)

    def getPropertyName: String = propertyName

    def getTitle: String = {
        var returnTitle: String = ""
        for (t:String <- titleType) {
            if (returnTitle == "")
            {
                returnTitle = t match {
                    case "url" => propertyName
                    case "constant" => titleResource
                    case _ => ""
                }
            }
        }
        returnTitle
    }

    def getValue(properties: List[String]): String = {
        if (properties.size == 1) properties.head
        else {
            this.aggregateFunction match {
                case "max" => max(properties)
                case "min" => min(properties)
                case "avg" => avg(properties)
                case "nothing" => flatten(properties)
            }
        }
    }

    private def max(strings: List[String]) = {
        var max: Int = Integer.MIN_VALUE
        for (s:String <- strings) {
            val i: Int = s.toInt
            if (i > max) max = i
        }
        max.toString
    }

    private def min(strings: List[String]) = {
        var min: Int = Integer.MAX_VALUE
        for (s:String <- strings) {
            val i: Int = s.toInt
            if (i < min) min = i
        }
        min.toString
    }

    private def avg(strings: List[String]) = {
        var sum: Long = 0
        for (s:String <- strings) {
            val i: Int = s.toInt
            sum += i
        }
        (sum/strings.length).toString
    }

    private def flatten(strings: List[String]) = {
        var returns: String = strings.head
        for (s <- strings if s != strings.head) {
            returns += " " + s
        }
        returns
    }
}
