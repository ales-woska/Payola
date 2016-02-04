package cz.payola.web.client.views.gve.layout

class ScreenLayout(
        name: String,
        uri: String,
        blockLayouts: List[BlockLayout] = List(),
        lineLayouts: List[LineLayout] = List()) {

    def getName: String = name
    def getUri: String = uri
    def getBlockLayouts: List[BlockLayout] = blockLayouts
    def getLineLayouts: List[LineLayout] = lineLayouts


}
