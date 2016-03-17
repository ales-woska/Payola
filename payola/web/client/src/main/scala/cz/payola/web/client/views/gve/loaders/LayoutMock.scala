package cz.payola.web.client.views.gve.loaders

import cz.payola.web.client.views.gve.layout._

/**
  * Created by Ales Woska on 4.2.2016.
  */
class LayoutMock() {

    def COUNTRY_LAYOUT:BlockLayout = {
        val mock:BlockLayout = new BlockLayout("http://dbpedia.org/ontology/Country")
        mock.title = "Country"
        mock.background = "#CCFF33"
        mock.lineThickness = 2
        mock.lineType = "solid"
        mock.lineColor = "black"
        mock.height = 250
        mock.width = 370
        mock.left = 980
        mock.top = 10
        mock.properties = List(
            new RowLayout("http://dbpedia.org/ontology/areaTotal", List(TitleType.CONSTANT), "Area", List(AggregateFunction.NOTHING)))
        mock.horizontalLines = "1px solid gray"
        mock.verticalLines = "1px solid silver"
        mock.titleTypes = List(TitleType.CONSTANT)
        mock
    }

    def CITY_LAYOUT:BlockLayout = {
        val mock:BlockLayout = new BlockLayout("http://dbpedia.org/ontology/City")
        mock.title = "City"
        mock.background = "#CCFF33"
        mock.lineThickness = 2
        mock.lineType = "solid"
        mock.lineColor = "black"
        mock.height = 250
        mock.width = 700
        mock.left = 10
        mock.top = 10
        mock.properties = List(
            new RowLayout("http://dbpedia.org/ontology/populationTotal", List(TitleType.CONSTANT), "Population", List(AggregateFunction.NOTHING)),
            new RowLayout("http://dbpedia.org/ontology/populationDensity", List(TitleType.CONSTANT), "Density", List(AggregateFunction.NOTHING)))
        mock.horizontalLines = "1px solid gray"
        mock.verticalLines = "1px solid silver"
        mock.titleTypes = List(TitleType.CONSTANT)
        mock
    }

    def COUNTRY_CITY_LINE:LineLayout = {
        val mock:LineLayout = new LineLayout(CITY_LAYOUT.forClass, COUNTRY_LAYOUT.forClass, "In country")
        mock.lineThickness = 1
        mock.lineColor = "black"
        mock.lineType = "solid"
        mock.width = 2
        mock.fontColor = "gray"
        mock.fontSize = 8
        mock.width = 246
        mock.left = 734
        mock.top = 75
        mock.titleTypes = List(TitleType.CONSTANT)
        mock
    }

}
