package cz.payola.web.client.views.gve.layout.dialog

import cz.payola.common.visual.Color
import cz.payola.web.client.events._
import cz.payola.web.client.views.bootstrap._
import cz.payola.web.client.views.elements._
import cz.payola.web.client.views.elements.form.fields._
import cz.payola.web.client.views.gve.layout._

class AddBlockDialog(sender: EditLayoutDialog) extends Modal("Add Block")
{
    val classInput = new InputControl[TextInput]("Class", new TextInput("class", "", "Class"), Some("small"), None)
    val titleInput = new InputControl[TextInput]("Title/Source", new TextInput("title", "", "Class title"), Some("span1"), None)
    val titleTypeInput = sender.customTypeSelect("titleType", "Title Type", sender.titleTypeOptions)
    val heightInput = new InputControl[NumericInput]("Height", new NumericInput("height", 100, "Height"), Some("span1"), None)
    val widthInput = new InputControl[NumericInput]("Width", new NumericInput("width", 100, "Width"), Some("span1"), None)
    val leftInput = new InputControl[NumericInput]("Left", new NumericInput("left", 0, "Left"), Some("span1"), None)
    val topInput = new InputControl[NumericInput]("Top", new NumericInput("top", 0, "Top"), Some("span1"), None)
    val fillColorInput = new InputControl[ColorInput]("Background color:", new ColorInput("fillColor", Option(Color.Black), ""), Some("span1"), None)
    val fontSizeInput = new InputControl[NumericInput]("Font size", new NumericInput("fontSize", 0, "Font size"), Some("span1"), None)
    val fontColorInput = new InputControl[ColorInput]("Font color:", new ColorInput("fontColor", Option(Color.Black), ""), Some("span1"), None)

    val lineTypeInput = sender.customTypeSelect("lineType", "Border type", sender.lineTypeOptions)
    val lineThicknessInput = new InputControl[NumericInput]("Border Thickness", new NumericInput("lineThickness", 100, "Border Thickness"), Some("span1"), None)
    val lineColorInput = new InputControl[ColorInput]("Border color", new ColorInput("lineColor", Option(Color.Black), ""), Some("span1"), None)

    val hLineTypeInput = sender.customTypeSelect("hLineType", "Horizontal lines type", sender.lineTypeOptions)
    val hLineThicknessInput = new InputControl[NumericInput]("Horizontal lines Thickness", new NumericInput("hLineThickness", 100, "Horizontal lines Thickness"), Some("span1"), None)
    val hLineColorInput = new InputControl[ColorInput]("Horizontal lines color", new ColorInput("hLineColor", Option(Color.Black), ""), Some("span1"), None)

    val vLineTypeInput = sender.customTypeSelect("vLineType", "Vertical lines type", sender.lineTypeOptions)
    val vLineThicknessInput = new InputControl[NumericInput]("Vertical lines Thickness", new NumericInput("vLineThickness", 100, "Vertical lines Thickness"), Some("span1"), None)
    val vLineColorInput = new InputControl[ColorInput]("Vertical lines color", new ColorInput("vLineColor", Option(Color.Black), ""), Some("span1"), None)

    val propertyUrlInput = new TextInput("propertyUrl", "", "Property URL")
    val propertyTitleTypeInput = sender.customTypeSelect("propertyTitleType", "Property Tile Type", sender.titleTypeOptions)
    val propertyTitle = new TextInput("propertyTitle", "", "Property Title")
    val propertyAggrFuncInput = sender.customTypeSelect("propertyAggrFunc", "Property Aggregate Function", sender.aggrFuncTypeOptions)
    
    val allElements = List(
        classInput,
        titleInput,
        titleTypeInput,
        heightInput,
        widthInput,
        leftInput,
        topInput,
        fillColorInput,
        fontSizeInput,
        fontColorInput,
        lineTypeInput,
        lineThicknessInput,
        lineColorInput,
        hLineTypeInput,
        hLineThicknessInput,
        hLineColorInput,
        vLineTypeInput,
        vLineThicknessInput,
        vLineColorInput,
        propertyUrlInput,
        propertyTitleTypeInput,
        propertyTitle,
        propertyAggrFuncInput
    )

    confirming += { e =>
        var color: Color = hLineColorInput.field.value.get
        val horizontalLine = hLineThicknessInput.field.value + "px " + hLineTypeInput.field.value + " " + color.toString

        color = vLineColorInput.field.value.get
        val verticalLine = vLineThicknessInput.field.value + "px " + vLineTypeInput.field.value + " " + color.toString

        List(new RowLayout("rdf:type", List(TitleType.URL), "name", List(AggregateFunction.NOTHING)))
        val properties = List(new RowLayout(
            propertyUrlInput.value,
            List(TitleType.fromString(propertyTitleTypeInput.field.value)),
            propertyTitle.value,
            List(AggregateFunction.fromString(propertyAggrFuncInput.field.value))))

        color = fillColorInput.field.value.get
        val newBlock: BlockLayout = new BlockLayout(
            classInput.field.value,
            titleInput.field.value,
            titleInput.field.value,
            color.toString,
            heightInput.field.value,
            verticalLine,
            horizontalLine,
            properties
        )
        newBlock.titleTypes = List(TitleType.fromString(titleTypeInput.field.value))
        newBlock.left = leftInput.field.value
        newBlock.top = topInput.field.value
        newBlock.width = widthInput.field.value
        color = fontColorInput.field.value.get
        newBlock.fontColor = color.toString
        newBlock.fontSize = fontSizeInput.field.value
        color = lineColorInput.field.value.get
        newBlock.lineColor = color.toString
        newBlock.lineType = lineTypeInput.field.value
        newBlock.lineThickness = lineThicknessInput.field.value
    
        sender.blocks = sender.blocks ++ List(newBlock)

        val newDiv = new Div(List(new Text(classInput.field.value)))
        newDiv.htmlElement.innerHTML = classInput.field.value
        val scale = 3
        newDiv.setAttribute("style",
            "position: absolute;" +
            "width: " + newBlock.width/scale +  "px; " +
            "height:"  + newBlock.height/scale +  "px; " +
            "background-color: " + newBlock.background + ";" +
            "top: "  + (70 + newBlock.top/scale) +  "px;" +
            "left: "  + (5 + newBlock.left/scale) +  "px;")
        newDiv.render(sender.blockHtmlElement)

        true
    }

    val div:Div = new Div(allElements)

    for (e <- allElements) {
        e.render(div.htmlElement)
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