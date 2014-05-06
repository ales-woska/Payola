package cz.payola.web.client.views.gve.loaders

import cz.payola.common.entities.Analysis
import cz.payola.web.client.views.bootstrap._
import cz.payola.web.client.views.elements.Div
import cz.payola.web.client.views.elements._
import cz.payola.web.client.views.elements.form.fields.TextInput
import cz.payola.web.client.views.entity.analysis.ReadOnlyAnalysisVisualizer
import s2js.adapters.browser._
import s2js.adapters.html

class LayoutDialog() extends Modal("Load layout configuration")
{
    val urlInput = new TextInput("url", "", "Layout URL")
    val urlField = new InputControl[TextInput]("Layout URL", urlInput, None, None)

    val editButtonCaption = new Text("Edit layout...")
    val editButton = new Button(editButtonCaption)
    editButton.mouseClicked += { e =>
        val editDialog = new EditLayoutDialog()
        editDialog.render()
        editDialog.confirming += { x =>
            true
        }
        false
    }

    val placeholder = new Div(List(urlField, editButton))
    override val body = List(placeholder)
}