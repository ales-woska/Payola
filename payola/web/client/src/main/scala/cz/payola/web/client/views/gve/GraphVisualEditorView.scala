package cz.payola.web.client.views.gve

import cz.payola.web.client.views.graph.PluginView
import cz.payola.web.client.models.PrefixApplier
import cz.payola.web.client.views.elements._
import cz.payola.web.client.views.gve.data.DataModel
import cz.payola.web.client.views.gve.layout.{LineLayout, BlockLayout, ScreenLayout}
import cz.payola.web.client.views.gve.loaders._
import cz.payola.web.client.views.gve.render.{RdfPredicateRenderer, RdfSubjectRenderer}
import s2js.adapters.html
import s2js.compiler._
import cz.payola.common.rdf._
import cz.payola.web.shared.transformators.GraphVisualEditorTransformator
import cz.payola.web.client.views.bootstrap.modals.FatalErrorModal
import cz.payola.web.shared._
import cz.payola.web.client.views.bootstrap.Modal
import cz.payola.web.client.views.gve.layout.dialog.LayoutDialog

class GraphVisualEditorView(prefixApplier: Option[PrefixApplier] = None)
    extends PluginView[Graph]("Graph Visual Editor", prefixApplier) {

    final val STYLE = "style"
    final val GVE_WRAPPER_ID = "gve_wrapper"
    final val GVE_WRAPPER_STYLE = "margin: 0 20px 20px 20px; background-color: white; position: relative; height: 1000px;"

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

    override def updateGraph(graph: Option[Graph], contractLiterals: Boolean = true) {

        if (!LayoutLoader.isLoaded) {
            val dialog = new LayoutDialog()
            dialog.render()
            val pluginWrapper = getPluginWrapper
            dialog.confirming += { x =>
                if (dialog.urlField.field.value == "") {
                    val alertBox = new Modal("URL field is empty")
                    alertBox.render(pluginWrapper.htmlElement)
                    dialog.destroy()
                    false
                } else {
                    GVE.setUri(dialog.urlField.field.value)
                    LayoutLoader.loadLayouts(dialog.urlField.field.value)
                    if (dialog.graphField.field.value != "") {
                        GVE.setNamedGraph(dialog.graphField.field.value)
                    }
                    LayoutLoader.loaded = true
                    pluginWrapper.removeAllChildNodes()
                    this.render(graph.get, pluginWrapper)
                    super.updateGraph(graph, contractLiterals = true)
                    true
                }
            }
        }
    }

    def render(graph: Graph, pluginWrapper: Div) {

        if (graph.isEmpty) {
            renderMessage(pluginWrapper.htmlElement, "The graph is empty...")
            return
        }

        val pluginWorkArea = new Div().htmlElement
        pluginWrapper.htmlElement.appendChild(pluginWorkArea)

        val dataModel: DataModel = RdfDataLoader.constructDataStructure(graph)

        LayoutLoader.createDemoLayouts()
        val test: ScreenLayout = new ScreenLayout("Test Layout", "test.woska.layout", List(), List())
        //test.blockLayouts = GVE.select(List("?layout"), "{?layout gve:Layout " + uri + " ; ?layout gve:layoutType \"gve:Block\"}")
        //test.lineLayouts = GVE.select(List("?layout"), "{?layout gve:Layout " + uri + " ; ?layout gve:layoutType \"gve:Line\"}")
        LayoutLoader.assignLayouts(dataModel)
        pluginWrapper.htmlElement.appendChild(createLayoutButton(graph))

        for (c:BlockLayout <- test.getBlockLayouts) {
            val blockRenderer = new RdfSubjectRenderer(graph, rdfStructure.getRdfSubjectByUri(c.forClass), c)
            blockRenderer.render(pluginWrapper.htmlElement)
        }
        for (c:LineLayout <- test.getLineLayouts) {
            val blockRenderer = new RdfPredicateRenderer(graph, rdfStructure.getRdfPredicateByUri(c.fromClass), c)
            blockRenderer.render(pluginWrapper.htmlElement)
        }
    }

    private def createLayoutButton(graph: Graph): html.Element = {
        val settingsButton = new Button(new Text("Load layout..."))
        settingsButton.setAttribute(STYLE, "position: absolute; top: 0; right: 0;")
        settingsButton.mouseClicked += { e =>
            val dialog = new LayoutDialog()
            dialog.render()
            dialog.confirming += { x =>
                LayoutLoader.loadLayouts(dialog.urlField.field.value)
                LayoutLoader.loaded = true
                render(graph, getPluginWrapper)
                true
            }
            false
        }
        settingsButton.htmlElement
    }

    private def getPluginWrapper = {
        val pluginWrapper: Div = new Div()
        pluginWrapper.setAttribute(STYLE, GVE_WRAPPER_STYLE)
        pluginWrapper.id = GVE_WRAPPER_ID
        pluginWrapper
    }

    @javascript("""console.log(str)""")
    def log(str: Any) {}
}
