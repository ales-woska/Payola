package cz.payola.web.client.views.gve.layout

class BlockLayout(
    val forClass: String,
    var title: String,
    var titleSource: String,
    var background: String,
    var height: Int,
    var verticalLines: String,
    var horizontalLines: String,
    var properties: List[RowLayout]) extends AbstractLayout() {

    def this(forClass: String, title: String) = this(
        forClass,
        title,
        DefaultBlockLayout.titleSouce,
        DefaultBlockLayout.background,
        DefaultBlockLayout.height,
        DefaultBlockLayout.horizontalLines,
        DefaultBlockLayout.verticalLines,
        DefaultBlockLayout.properties
    )

    def this(forClass: String) = this(forClass, "")

    def getRowLayout(name: String): RowLayout = {
        for (r: RowLayout <- properties) {
            if (r.propertyName.eq(name)) {
                return r
            }
        }
        null
    }
}

object DefaultBlockLayout {
    val titleSouce = "no-name"
    val background = "CCFF33"
    val height = 250
    val horizontalLines = "1px solid gray"
    val verticalLines = "1px solid silver"
    val properties = List(new RowLayout("rdf:type", List(TitleType.URL), "name", List(AggregateFunction.NOTHING)))
}
