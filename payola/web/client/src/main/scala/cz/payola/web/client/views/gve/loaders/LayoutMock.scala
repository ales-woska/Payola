package cz.payola.web.client.views.gve.loaders

import cz.payola.web.client.views.gve.layout._

/**
  * Created by Ales Woska on 4.2.2016.
  */
object LayoutMock {
    final val COUNTRY_LAYOUT = new BlockLayout("http://dbpedia.org/ontology/Country")
    COUNTRY_LAYOUT.title = "Country"
    COUNTRY_LAYOUT.background = "#CCFF33"
    COUNTRY_LAYOUT.lineThickness = 2
    COUNTRY_LAYOUT.lineType = "solid"
    COUNTRY_LAYOUT.lineColor = "black"
    COUNTRY_LAYOUT.height = 250
    COUNTRY_LAYOUT.width = 370
    COUNTRY_LAYOUT.left = 980
    COUNTRY_LAYOUT.top = 10
    COUNTRY_LAYOUT.properties = List(
        new RowLayout("http://dbpedia.org/ontology/areaTotal", List(TitleType.CONSTANT), "Area", List(AggregateFunction.NOTHING)))
    COUNTRY_LAYOUT.horizontalLines = "1px solid gray"
    COUNTRY_LAYOUT.verticalLines = "1px solid silver"
    COUNTRY_LAYOUT.titleTypes = List(TitleType.CONSTANT)

    final val CITY_LAYOUT = new BlockLayout("http://dbpedia.org/ontology/City")
    CITY_LAYOUT.title = "City"
    CITY_LAYOUT.background = "#CCFF33"
    CITY_LAYOUT.lineThickness = 2
    CITY_LAYOUT.lineType = "solid"
    CITY_LAYOUT.lineColor = "black"
    CITY_LAYOUT.height = 250
    CITY_LAYOUT.width = 700
    CITY_LAYOUT.left = 10
    CITY_LAYOUT.top = 10
    CITY_LAYOUT.properties = List(
        new RowLayout("http://dbpedia.org/ontology/populationTotal", List(TitleType.CONSTANT), "Population", List(AggregateFunction.NOTHING)),
        new RowLayout("http://dbpedia.org/ontology/populationDensity", List(TitleType.CONSTANT), "Density", List(AggregateFunction.NOTHING)))
    CITY_LAYOUT.horizontalLines = "1px solid gray"
    CITY_LAYOUT.verticalLines = "1px solid silver"
    CITY_LAYOUT.titleTypes = List(TitleType.CONSTANT)

    final val COUNTRY_CITY_LINE = new LineLayout(CITY_LAYOUT.forClass, COUNTRY_LAYOUT.forClass, "In country")
    COUNTRY_CITY_LINE.lineThickness = 1
    COUNTRY_CITY_LINE.lineColor = "black"
    COUNTRY_CITY_LINE.lineType = "solid"
    COUNTRY_CITY_LINE.width = 2
    COUNTRY_CITY_LINE.fontColor = "gray"
    COUNTRY_CITY_LINE.fontSize = 8
    COUNTRY_CITY_LINE.width = 246
    COUNTRY_CITY_LINE.left = 734
    COUNTRY_CITY_LINE.top = 75
    COUNTRY_CITY_LINE.titleTypes = List(TitleType.CONSTANT)

}
