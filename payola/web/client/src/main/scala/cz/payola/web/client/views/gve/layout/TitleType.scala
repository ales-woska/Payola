package cz.payola.web.client.views.gve.layout

trait TitleType {
    def name: String
}

object TitleType {
    case object URL extends TitleType {override val name = "url"}
    case object LABEL extends TitleType {override val name = "label"}
    case object CONSTANT extends TitleType {override val name = "constant"}
    case object PROPERTY extends TitleType {override val name = "property"}

    def fromString(value: String): TitleType = {
        if (value.eq(URL.name)) {
            URL
        } else if (value.eq(LABEL.name)) {
            LABEL
        } else if (value.eq(CONSTANT.name)) {
            CONSTANT
        } else if (value.eq(PROPERTY.name)) {
            PROPERTY
        } else {
            null
        }
    }
}