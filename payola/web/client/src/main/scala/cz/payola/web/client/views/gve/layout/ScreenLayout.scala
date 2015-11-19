package cz.payola.web.client.views.gve.layout

import cz.payola.web.shared.GVE

class ScreenLayout(
        name: String,
        uri: String,
        blockLayouts: List[BlockLayout] = List(),
        lineLayouts: List[LineLayout] = List()) {

    def getName: String = name
    def getUri: String = uri

    def load() = {
        //val blockLayouts = GVE.select(List("?layout"), "{?layout gve:Layout " + uri + " ; ?layout gve:layoutType \"gve:Block\"}")
        //val lineLayouts = GVE.select(List("?layout"), "{?layout gve:Layout " + uri + " ; ?layout gve:layoutType \"gve:Line\"}")
    }

    def store(graph: String) = {
        GVE.insert(graph, uri + " gve:layoutName " + name)
        for (block: BlockLayout <- blockLayouts) {
            block.store(graph, this)
        }
        for (line: LineLayout <- lineLayouts) {
            line.store(graph, this)
        }
    }
}
