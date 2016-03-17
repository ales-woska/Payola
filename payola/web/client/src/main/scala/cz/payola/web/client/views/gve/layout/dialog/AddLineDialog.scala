package cz.payola.web.client.views.gve.layout.dialog

import cz.payola.common.visual.Color
import cz.payola.web.client.events._
import cz.payola.web.client.views.bootstrap._
import cz.payola.web.client.views.elements._
import cz.payola.web.client.views.elements.form.fields._
import cz.payola.web.client.views.gve.layout.{TitleType, LineLayout}

class AddLineDialog(sender: EditLayoutDialog) extends Modal("Add Line")
{
    val fromClassInput = new InputControl[TextInput]("From Class", new TextInput("fromClass", "", "From class"), Some("small"), None)
    val toClassInput = new InputControl[TextInput]("To Class", new TextInput("toClass", "", "To class"), Some("small"), None)
    val titleInput = new InputControl[TextInput]("Title", new TextInput("title", "", "Class title"), Some("span1"), None)
    val titleTypeInput = sender.customTypeSelect("Title Type", "titleType", sender.titleTypeOptions)

    val lineTypeInput = sender.customTypeSelect("lineType", "Line type", sender.lineTypeOptions)
    val lineThicknessInput = new InputControl[NumericInput]("Line Thickness", new NumericInput("lineThickness", 100, "Line Thickness"), Some("span1"), None)
    val lineColorInput = new InputControl[ColorInput]("Line color", new ColorInput("lineColor", Option(Color.Black), ""), Some("span1"), None)

    val fontSizeInput = new InputControl[NumericInput]("Font size", new NumericInput("fontSize", 0, "Font size"), Some("span1"), None)
    val fontColorInput = new InputControl[ColorInput]("Font color:", new ColorInput("fontColor", Option(Color.Black), ""), Some("span1"), None)
    val widthInput = new InputControl[NumericInput]("Width", new NumericInput("width", 100, "Width"), Some("span1"), None)
    val leftInput = new InputControl[NumericInput]("Left", new NumericInput("left", 100, "Left"), Some("span1"), None)
    val topInput = new InputControl[NumericInput]("Top", new NumericInput("top", 50, "Top"), Some("span1"), None)

    var els = List(
        fromClassInput,
        toClassInput,
        titleInput,
        titleTypeInput,
        lineTypeInput,
        lineThicknessInput,
        lineColorInput,
        fontSizeInput,
        fontColorInput,
        widthInput,
        leftInput,
        topInput
    )

    val div:Div = new Div(els)

    for (e <- els) {
        e.render(div.htmlElement)
    }

    confirming += { e =>
//        val newLine = new LineLayout(
//            fromClassInput.field.value,
//            toClassInput.field.value,
//            titleInput.field.value
//        )
//        newLine.titleTypes = List(TitleType.fromString(titleTypeInput.field.value))
//        newLine.left = leftInput.field.value
//        newLine.top = topInput.field.value
//        newLine.width = widthInput.field.value
//        var color: Color = fontColorInput.field.value.get
//        newLine.fontColor = color.toString
//        newLine.fontSize = fontSizeInput.field.value
//        color = lineColorInput.field.value.get
//        newLine.lineColor = color.toString
//        newLine.lineType = lineTypeInput.field.value
//        newLine.lineThickness = lineThicknessInput.field.value
//
//        sender.lines = sender.lines ++ List(newLine)
//
//        val newDiv = new Div(List(new Text(titleInput.field.value)))
//        newDiv.htmlElement.innerHTML = titleInput.field.value
//        val scale = 3
//        newDiv.setAttribute("style",
//            "position: absolute;" +
//                "color: " + newLine.fontColor +  "px; " +
//                "width: " + newLine.width/scale +  "px; " +
//                "height: 10px; " +
//                "border-bottom: 1px solid "  + newLine.lineColor +  "; " +
//                "top: "  + (70 + newLine.top/scale) +  "px;" +
//                "left: "  + (5 + newLine.left/scale) +  "px;")
//        newDiv.render(sender.blockHtmlElement)
        true
    }

    override val body = List(div)

    override def createSubViews = {
        saveButton.mouseClicked += { e => buttonClickedHandler(confirming)}
        cancelButton.mouseClicked += { e => buttonClickedHandler(closing)}
        closeButton.mouseClicked += { e => buttonClickedHandler(closing)}

        val modalDiv = new Div(List(
            new Div(
                (if (hasCloseButton) List(closeButton) else Nil) ++ List(new Heading(List(new Text(header)))),
                "modal-header"
            ),
            new Div(
                body,
                "modal-body"
            ),
            new Div(
                (if (cancelText.isDefined) List(cancelButton) else Nil) ++
                    (if (confirmText.isDefined) List(saveButton) else Nil),
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