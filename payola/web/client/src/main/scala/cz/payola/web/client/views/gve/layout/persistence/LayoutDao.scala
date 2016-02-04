package cz.payola.web.client.views.gve.layout.persistence

/**
  * Created by Ales Woska on 31.1.2016.
  */
trait LayoutDao {
    def store(layout: Any)
}