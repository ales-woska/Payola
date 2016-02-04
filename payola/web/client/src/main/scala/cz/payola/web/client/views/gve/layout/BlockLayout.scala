package cz.payola.web.client.views.gve.layout

class BlockLayout(
    val forClass: String,
    var title: String,
    var titleSouce: String,
    var background: String,
    var height: Int,
    var verticalLines: String,
    var horizontalLines: String,
    var properties: List[RowLayout]) extends AbstractLayout() {

    def this() = this("")

    def this(forClass: String) = this(forClass, "")

    def this(forClass: String, title: String) = this(
        forClass,
        title,
        defaultBlockLayout.titleSouce,
        defaultBlockLayout.background,
        defaultBlockLayout.height,
        defaultBlockLayout.horizontalLines,
        defaultBlockLayout.verticalLines,
        defaultBlockLayout.properties
    )

    object defaultBlockLayout {
        val titleSouce = "no-name"
        val background = "CCFF33"
        val height = 250
        val horizontalLines = "1px solid gray"
        val verticalLines = "1px solid silver"
        val properties = List(new RowLayout("rdf:type", List(URL), "name", List(NOTHING)))
    }

    def getRowLayout(name: String): RowLayout = {
        for (r: RowLayout <- properties) {
            if (r.getPropertyName.eq(name)) {
                return r
            }
        }
        return null
    }
}
