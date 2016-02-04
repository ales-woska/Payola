package cz.payola.web.client.views.gve.layout.persistence

import cz.payola.web.client.views.gve.layout._
import cz.payola.web.shared.GVE

/**
  * Created by Ales Woska on 31.1.2016.
  */
class ScreenLayoutDao extends LayoutDao
{
    val LAYOUT_NAME: String = "gve:layoutName"

    final val blockDao = new BlockLayoutDao
    final val lineDao = new LineLayoutDao

    override def store(layout: Any): Unit = {
        this.store(layout.asInstanceOf[ScreenLayout])
    }

    def store(layout: ScreenLayout) = {
        val graph: String = ""
        GVE.insert(graph, layout.getUri + " " + LAYOUT_NAME + " " + layout.getName)
        for (block: BlockLayout <- layout.getBlockLayouts) {
            blockDao.store(graph, this)
        }
        for (line: LineLayout <- layout.getLineLayouts) {
            lineDao.store(graph, this)
        }
    }
}
