package cz.payola.web.client.views.gve.render

import cz.payola.web.client.views.gve.data.DataModel
import cz.payola.web.client.views.gve.layout.{ScreenLayout, LineLayout, BlockLayout}
import s2js.adapters.html.Element

/**
  * Created by Ales Woska on 4.2.2016.
  */
object PluginRenderer {
    def render(element: Element, dataModel: DataModel, layout: ScreenLayout) {
        for (c:BlockLayout <- layout.getBlockLayouts) {
//            RdfSubjectRenderer.re
//            val blockRenderer = new RdfSubjectRenderer(graph, rdfStructure.getRdfSubjectByUri(c.forClass), c)
//            blockRenderer.render(element)
        }
        for (c:LineLayout <- layout.getLineLayouts) {
//            val blockRenderer = new RdfPredicateRenderer(graph, rdfStructure.getRdfPredicateByUri(c.fromClass), c)
//            blockRenderer.render(element)
        }
    }
}
