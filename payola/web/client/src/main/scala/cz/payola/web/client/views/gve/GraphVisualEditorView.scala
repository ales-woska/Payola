package cz.payola.web.client.views.gve

import s2js.adapters.html.Element
import cz.payola.web.client.View
import cz.payola.web.client.views.graph.PluginView
import cz.payola.web.client.models.PrefixApplier
import cz.payola.web.client.views.elements._
import cz.payola.common.rdf.Graph
import cz.payola.web.client.views.gve.data.DataModel
import cz.payola.web.client.views.gve.layout.ScreenLayout
import cz.payola.web.client.views.gve.loaders.{LayoutLoader, RdfDataLoader}
import cz.payola.web.client.views.gve.render.PluginRenderer
import cz.payola.web.shared.transformators.GraphVisualEditorTransformator
import cz.payola.web.client.views.bootstrap.modals.FatalErrorModal

class GraphVisualEditorView(prefixApplier: Option[PrefixApplier] = None)
    extends PluginView[Graph]("Graph Visual Editor", prefixApplier) {

    final val STYLE = "style"
    final val GVE_WRAPPER_STYLE = "margin: 0 20px 20px 20px; background-color: white; position: relative; height: 1000px;"

    protected val pluginWrapper = new Div()
    protected val wrapper = new Div().setAttribute(STYLE, GVE_WRAPPER_STYLE)

    override def updateGraph(graph: Option[Graph], contractLiterals: Boolean = true) {
//        val dialog = new LayoutDialog()
//        dialog.render()
//        dialog.confirming += { x =>
//            if (dialog.urlField.field.value == "") {
//                val alertBox = new Modal("URL field is empty")
//                alertBox.render(pluginWrapper.htmlElement)
//                dialog.destroy()
//                false
//            } else {
//                GVE.setUri(dialog.urlField.field.value)
//                LayoutLoader.loadLayouts(dialog.urlField.field.value)
//                if (dialog.graphField.field.value != "") {
//                    GVE.setNamedGraph(dialog.graphField.field.value)
//                }
//                this.render(graph.get)
//                super.updateGraph(graph, contractLiterals = true)
//                true
//            }
//        }
        if (graph.isEmpty) {
            pluginWrapper.setAttribute(STYLE, "height: 300px;")
            renderMessage(pluginWrapper.htmlElement, "The graph is empty...")
        } else {
            pluginWrapper.setAttribute(STYLE, "")
            if (graph != currentGraph) {
                wrapper.removeAllChildNodes()
                pluginWrapper.removeAllChildNodes()
                pluginWrapper.htmlElement.appendChild(wrapper.htmlElement)
                this.render(graph)
            }
        }
        super.updateGraph(graph, contractLiterals = true)
    }

    private def render(graph: Option[Graph]) {
        //        pluginWrapper.htmlElement.appendChild(createLayoutButton(graph))
        val layout: ScreenLayout = LayoutLoader.loadLayout()
        val dataModel: DataModel = RdfDataLoader.constructDataStructure(graph)
        PluginRenderer.render(wrapper.htmlElement, dataModel, layout)
    }

    private def createLayoutButton(graph: Graph): Element = {
        val settingsButton = new Button(new Text("Load layout..."))
        settingsButton.setAttribute(STYLE, "position: absolute; top: 0; right: 0;")
        settingsButton.mouseClicked += { e =>
            this.updateGraph(Option(graph), true)
            false
        }
        settingsButton.htmlElement
    }

    override def isAvailable(availableTransformators: List[String], evaluationId: String,
        success: () => Unit, fail: () => Unit) {

        GraphVisualEditorTransformator.getSampleGraph(evaluationId) { sample =>
            if(sample.isEmpty && availableTransformators.exists(_.contains(GraphVisualEditorTransformator.NAME))) {
                success()
            } else {
                fail()
            }
        }
        { error =>
            fail()
            val modal = new FatalErrorModal(error.toString)
            modal.render()
        }
    }

    override def loadDefaultCachedGraph(evaluationId: String, updateGraph: Option[Graph] => Unit) {
        GraphVisualEditorTransformator.getCachedPage(evaluationId) {
            pageOfGraph => updateGraph(pageOfGraph)
        } {
            error =>
                val modal = new FatalErrorModal(error.toString)
                modal.render()
        }
    }

    override def createSubViews: Seq[View] = {
        List(pluginWrapper)
    }

}
