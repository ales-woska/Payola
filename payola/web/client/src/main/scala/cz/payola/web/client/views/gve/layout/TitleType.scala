package cz.payola.web.client.views.gve.layout

trait TitleType {
    def name: String
}
object TitleType {
    def fromString(value: String): TitleType = {
        if (value.eq(URL.name)) {
            return URL
        } else if (value.eq(LABEL.name)) {
            return LABEL
        } else if (value.eq(CONSTANT.name)) {
            return CONSTANT
        } else if (value.eq(PROPERTY.name)) {
            return PROPERTY
        } else {
            return null
        }
    }
}
case object URL extends TitleType {val name = "url"}
case object LABEL extends TitleType {val name = "label"}
case object CONSTANT extends TitleType {val name = "constant"}
case object PROPERTY extends TitleType {val name = "property"}