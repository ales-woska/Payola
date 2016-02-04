package cz.payola.web.client.views.gve.layout

sealed trait TitleType {def name: String}
case object URL extends TitleType {val name = "url"}
case object LABEL extends TitleType {val name = "label"}
case object CONSTANT extends TitleType {val name = "constant"}
case object PROPERTY extends TitleType {val name = "property"}