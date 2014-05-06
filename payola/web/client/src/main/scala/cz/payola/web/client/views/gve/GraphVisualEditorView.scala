package cz.payola.web.client.views.gve

import cz.payola.web.client.views.graph.PluginView
import s2js.adapters.html
import cz.payola.web.client.models.PrefixApplier
import s2js.adapters.browser._
import cz.payola.web.client.views.elements._
import cz.payola.web.client.views.gve.data._
import cz.payola.web.client.views.gve.loaders._
import s2js.compiler._
import cz.payola.domain.rdf._
import cz.payola.common.rdf.Graph

class GraphVisualEditorView(prefixApplier: scala.Option[PrefixApplier] = scala.None)
    extends PluginView[cz.payola.common.rdf.Graph]("Graph Visual Editor", prefixApplier) {

    protected var pluginWrapper: Div = new Div()
    pluginWrapper.setAttribute("style", "margin: 0 20px 20px 20px; background-color: white; position: relative;")
    pluginWrapper.id = "gve_wrapper"

    def createSubViews = scala.List(pluginWrapper)

    @javascript("""console.log(str)""")
    def log(str: Any) {}


    override def updateGraph(graph: Option[cz.payola.common.rdf.Graph], contractLiterals: Boolean) {

        /*
        val uri: String = "http://internal.opendata.cz:8893/sparql"
        val query: String = "SELECT * FROM <woska.example.layout> WHERE { ?s ?p ?o }"


        val sparqlQuery = QueryFactory.create(query)
        sparqlQuery.addGraphURI(uri)
        */





        pluginWrapper.removeAllChildNodes()

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
        pluginWrapper.htmlElement.appendChild(settingsButton.htmlElement)

        if (!LayoutLoader.isLoaded) {
            val dialog = new LayoutDialog()
            dialog.render()
            dialog.confirming += { x =>
                // LayoutLoader.loadLayouts(dialog.urlInput.value)
                LayoutLoader.loaded = true
                renderPlugin(graph)
                true
            }
        }

        if (LayoutLoader.isLoaded) {
            renderPlugin(graph)
        }
    }

    def renderPlugin(graph: scala.Option[cz.payola.common.rdf.Graph]) {
        if (graph != currentGraph) {

            if (graph.isEmpty) {
                renderMessage(pluginWrapper.htmlElement, "The graph is empty...")
            } else {
                val pluginWorArea = document.createElement[html.Element]("div")
                pluginWorArea.id = "gve_plugin"

                pluginWorArea.setAttribute("style", "min-height: 1000px;")
                pluginWrapper.htmlElement.appendChild(pluginWorArea)

                val dataStructure = new RdfStructure(graph.get)
                dataStructure.constructDataStructure()

                LayoutLoader.createDemoLayouts()
                LayoutLoader.assignLayouts(dataStructure)

                dataStructure.drawAllBlocks(pluginWorArea)
                dataStructure.drawAllLines(pluginWorArea)

                val cleardiv = document.createElement[html.Element]("div")
                cleardiv.setAttribute("style", "clear: both;")
                pluginWorArea.appendChild(cleardiv)
            }
        }

        super.updateGraph(graph, contractLiterals = true)
    }

    override def isAvailable(availableTransformators: List[String], evaluationId: String, success: () => Unit, fail: () => Unit){}
    override def loadDefaultCachedGraph(evaluationId: String, updateGraph: Option[cz.payola.common.rdf.Graph] => Unit) {
        updateGraph

    }


}
