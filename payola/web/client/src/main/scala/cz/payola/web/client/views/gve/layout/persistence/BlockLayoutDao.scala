package cz.payola.web.client.views.gve.layout.persistence

import cz.payola.web.client.views.gve.layout.BlockLayout
import cz.payola.web.shared.GVE

/**
  * Created by Ales Woska on 31.1.2016.
  */
class BlockLayoutDao extends LayoutDao
{
    override def store(layout: Any): Unit = {
        this.store(layout.asInstanceOf[BlockLayout])
    }

    private def store(layout: BlockLayout) {
        val graph: String = ""
        val data: List[String] = List(
            layout.forClass + " gve:layout XXX ",
            layout.forClass + " gve:titleType " + layout.titleTypes,
            layout.forClass + " gve:left " + layout.left,
            layout.forClass + " gve:top " + layout.top,
            layout.forClass + " gve:width " + layout.width,
            layout.forClass + " gve:fontColor " + layout.fontColor,
            layout.forClass + " gve:fontSize " + layout.fontSize,
            layout.forClass + " gve:lineColor " + layout.lineColor,
            layout.forClass + " gve:lineType " + layout.lineType,
            layout.forClass + " gve:lineThickness " + layout.lineThickness,
            layout.forClass + " gve:title " + layout.title,
            layout.forClass + " gve:background " + layout.background,
            layout.forClass + " gve:height " + layout.height,
            layout.forClass + " gve:verticalLines " + layout.verticalLines,
            layout.forClass + " gve:horizontalLines " + layout.horizontalLines)
        GVE.insert(graph, data)
    }
}
