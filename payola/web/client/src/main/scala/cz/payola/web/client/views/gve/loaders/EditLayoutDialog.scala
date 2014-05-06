package cz.payola.web.client.views.gve.loaders

import cz.payola.web.client.View
import cz.payola.web.client.views.ComposedView
import cz.payola.web.client.views.bootstrap._
import cz.payola.web.client.views.elements.form.fields._
import cz.payola.web.client.views.elements._
import s2js.adapters.html
import cz.payola.common.visual.Color
import cz.payola.web.client.events._
import s2js.adapters.dom.Element
import s2js.adapters.dom.Node
import s2js.adapters.dom.NodeList
import cz.payola.web.client.views.elements.form.Field
import cz.payola.web.client.views.gve.layout._

class EditLayoutDialog() extends Modal("Edit layout configuration", Nil, Some("Save"))
{
    var blocks: List[BlockLayout] = List()
    var lines: List[LineLayout] = List()

    val newBlockButton = new Button(new Text("Add Block"))
    newBlockButton.setAttribute("style", layoutInputTextStyle)
    newBlockButton.mouseClicked += { e =>
        val addBlockDialog = new AddBlockDialog(this)
        addBlockDialog.render()
        addBlockDialog.confirming += { x =>
            true
        }
        false
    }

    val newLineButton = new Button(new Text("Add Line"))
    newLineButton.setAttribute("style", layoutInputTextStyle)
    newLineButton.mouseClicked += { e =>
        val addLineDialog = new AddLineDialog(this)
        addLineDialog.render()
        addLineDialog.confirming += { x =>
            true
        }
        false
    }

    def layoutInputTextStyle = "font-size: 10px; padding: 0 2px;"

    val placeholder = new Div()

    def lineTypeOptions = List(
        new SelectOption("Solid", "solid"),
        new SelectOption("Dotted", "dotted"),
        new SelectOption("Dashed", "dashed"),
        new SelectOption("Double", "double"),
        new SelectOption("None", "none"))

    def titleTypeOptions = List(
        new SelectOption("String", "constant"),
        new SelectOption("URL", "url"),
        new SelectOption("RDF Title", "rdf:title"),
        new SelectOption("Other property", "other"))

    def aggrFuncTypeOptions = List(
        new SelectOption("None", "none"),
        new SelectOption("Min", "min"),
        new SelectOption("Max", "max"),
        new SelectOption("Average", "avg"))

    def customTypeSelect(name: String, text: String, options: List[SelectOption]): InputControl[Select] = {
        val selectedValue: SelectOption = options(0)
        new InputControl[Select](text, new Select(name, selectedValue.htmlElement.value, text, options), Some("span1"), None)
    }

    override val body = List(placeholder)

    override def createSubViews = {
        saveButton.mouseClicked += { e => buttonClickedHandler(confirming)}
        val saveAsButton = new Button(new Text("Save as..."), "btn-primary")
        saveAsButton.mouseClicked += { e => buttonClickedHandler(confirming)}
        cancelButton.mouseClicked += { e => buttonClickedHandler(closing)}
        closeButton.mouseClicked += { e => buttonClickedHandler(closing)}

        val bodyDiv = new Div(
            body,
            "modal-body"
        )
        bodyDiv.setAttribute("style", "height: 500px;")

        val modalDiv = new Div(List(
            new Div(
                (if (hasCloseButton) List(closeButton) else Nil) ++ List(new Heading(List(new Text(header))), newBlockButton, newLineButton),
                "modal-header"
            ),
            bodyDiv,
            new Div(
                List(cancelButton) ++ List(saveButton) ++ List(saveAsButton),
                "modal-footer"
            )
        ), "modal hide " + cssClass)
        modalDiv.setAttribute("style", "width: 1200px; margin-left: -600px;")
        List(modalDiv)
    }

    private def buttonClickedHandler(eventToTrigger: SimpleBooleanEvent[this.type]): Boolean = {
        if (eventToTrigger.trigger(new EventArgs[this.type](this))) {
            destroy()
        }
        false
    }
}