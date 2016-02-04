package cz.payola.web.client.views.gve.layout.persistence

import cz.payola.web.client.views.gve.layout.LineLayout
import cz.payola.web.shared.GVE

/**
  * Created by Ales Woska on 31.1.2016.
  */
class LineLayoutDao extends LayoutDao
{
    override def store(layout: Any): Unit = {
        this.store(layout.asInstanceOf[LineLayout])
    }

    private def store(layout: LineLayout) = {
        val graph: String = ""
        val data = List(
            layout.uri + " gve:layout xxx ",
            layout.uri + " gve:titleType " + layout.titleTypes,
            layout.uri + " gve:left " + layout.left,
            layout.uri + " gve:top " + layout.top,
            layout.uri + " gve:width " + layout.width,
            layout.uri + " gve:fontColor " + layout.fontColor,
            layout.uri + " gve:fontSize " + layout.fontSize,
            layout.uri + " gve:lineColor " + layout.lineColor,
            layout.uri + " gve:lineType " + layout.lineType,
            layout.uri + " gve:lineThickness " + layout.lineThickness,
            layout.uri + " gve:title " + layout.title,
            layout.uri + " gve:fromClass " + layout.fromClass,
            layout.uri + " gve:toClass " + layout.toClass)
        GVE.insert(graph, data)
    }
}
