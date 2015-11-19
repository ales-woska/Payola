package cz.payola.web.client.views.gve.layout.dialog

import cz.payola.web.client.views.bootstrap._
import cz.payola.web.client.views.elements.{Div, _}
import cz.payola.web.client.views.elements.form.fields.TextInput

class LayoutDialog() extends Modal("Load layout") {

    val urlField = new InputControl[TextInput](
        "Layout URL",
        new TextInput("url", "", "Layout URL"),
        None,
        None)

    val graphField = new InputControl[TextInput](
        "Graph name",
        new TextInput("graph", "", "Graph Name"),
        None,
        None)

    val editButton = new Button(new Text("Edit layout..."))
    editButton.mouseClicked += { e =>
        val editDialog = new EditLayoutDialog()
        editDialog.render()
        editDialog.confirming += { x =>
            true
        }
        false
    }

    override val body = List(
        new Div(List(
            urlField,
            graphField,
            editButton)))
}