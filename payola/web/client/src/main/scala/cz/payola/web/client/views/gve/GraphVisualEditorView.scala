package cz.payola.web.client.views.gve

import cz.payola.web.client.views.graph.PluginView
import cz.payola.web.client.models.PrefixApplier
import cz.payola.web.client.views.elements._
import cz.payola.web.client.views.gve.data._
import cz.payola.web.client.views.gve.loaders._
import s2js.compiler._
import cz.payola.common.rdf._
import cz.payola.web.shared.transformators.GraphVisualEditorTransformator
import cz.payola.web.client.views.bootstrap.modals.FatalErrorModal
import cz.payola.web.shared._
import cz.payola.web.client.views.gve.layout._
import cz.payola.web.client.views.bootstrap.Modal

class GraphVisualEditorView(prefixApplier: scala.Option[PrefixApplier] = scala.None)
    extends PluginView[cz.payola.common.rdf.Graph]("Graph Visual Editor", prefixApplier) {

    protected var pluginWrapper = getPluginWrapper
    def createSubViews = List(pluginWrapper)
    private def getPluginWrapper = {
        val pluginWrapper: Div = new Div()
        pluginWrapper.setAttribute("style", "margin: 0 20px 20px 20px; background-color: white; position: relative; height: 1000px;")
        pluginWrapper.id = "gve_wrapper"
        pluginWrapper
    }



    override def updateGraph(graph: Option[cz.payola.common.rdf.Graph], contractLiterals: Boolean = true) {
        pluginWrapper.removeAllChildNodes()
        pluginWrapper.htmlElement.appendChild(createLayoutButton(graph))

        if (!LayoutLoader.isLoaded) {
            val dialog = new LayoutDialog()
            dialog.render()
            dialog.confirming += { x =>
                if (dialog.urlField.field.value == "") {
                    val alertBox = new Modal("URL field is empty")
                    alertBox.render(pluginWrapper.htmlElement)
                    dialog.destroy()
                    false
                } else {
                    GVE.setUri(dialog.urlField.field.value)
                    // LayoutLoader.loadLayouts(dialog.urlInput.value)
                    if (dialog.graphField.field.value != "") {
                        GVE.setNamedGraph(dialog.graphField.field.value)
                    }
                    val list = LayoutLoader.getLayoutList
                    LayoutLoader.loaded = true
                    renderPlugin(graph)
                    true
                }
            }
        }
    }

    def renderPlugin(graph: scala.Option[cz.payola.common.rdf.Graph]) {
        if (graph != currentGraph) {

            if (graph.isEmpty) {
                renderMessage(pluginWrapper.htmlElement, "The graph is empty...")
            } else {
                val pluginWorkArea = new Div().htmlElement
                pluginWrapper.htmlElement.appendChild(pluginWorkArea)
                val dataStructure = new RdfStructure(graph.get)
                LayoutLoader.createDemoLayouts()

                /*
                val test: Layout = new Layout("Test Layout", "test.woska.layout", List(), List())

                for (b:BlockLayout <- LayoutLoader.blockLayouts) {
                    b.store("", test)
                }

                for (l:LineLayout <- LayoutLoader.lineLayouts) {
                    l.store("", test)
                }
                */

                // LayoutLoader.assignLayouts(dataStructure)
                // dataStructure.draw(pluginWorkArea)
            }
        }

        super.updateGraph(graph, contractLiterals = true)
    }

    override def isAvailable(availableTransformators: List[String], evaluationId: String,
        success: () => Unit, fail: () => Unit) {

        GraphVisualEditorTransformator.getSampleGraph(evaluationId) { sample =>
            if(sample.isEmpty && availableTransformators.exists(_.contains("GraphVisualEditorTransformator"))) {
                success()
            } else {
                fail()
            }
        }
        { error =>
            fail()
            val modal = new FatalErrorModal(error.toString())
            modal.render()
        }
    }

    override def loadDefaultCachedGraph(evaluationId: String, updateGraph: Option[Graph] => Unit) {
        GraphVisualEditorTransformator.getCachedPage(evaluationId) {
            pageOfGraph => updateGraph(pageOfGraph)
        } {
            error =>
                val modal = new FatalErrorModal(error.toString())
                modal.render()
        }
    }

    private def createLayoutButton(graph: scala.Option[cz.payola.common.rdf.Graph]) = {
        val settingsButton = new Button(new Text("Load layout..."))
        settingsButton.setAttribute("style", "position: absolute; top: 0; right: 0;")
        settingsButton.mouseClicked += { e =>
            val dialog = new LayoutDialog()
            dialog.render()
            dialog.confirming += { x =>
            // LayoutLoader.loadLayouts(dialog.urlInput.value)
                LayoutLoader.loaded = true
                renderPlugin(graph)
                true
            }
            false
        }
        settingsButton.htmlElement
    }

    @javascript("""console.log(str)""")
    def log(str: Any) {}
}
