package cz.payola.web.client.views.plugins.visual.graph.positioning

import s2js.adapters.js.browser.window
import cz.payola.web.client.views.plugins.visual.graph.Component
import cz.payola.web.client.views.plugins.visual._

/**
  * IMPORTANT, the first component must have number 1 (not 0)!!!!!
  * @param componentNumber COUNT FROM 1!!!!! not form 0
  */
class ComponentPositionHelper(val componentNumber: Int, val componentsCount: Int, val prevComp: Option[Component])
    extends PositionHelper {

    def getPositionCorrection(): Vector = {
        val componentSpacing = 50.0

        if(componentNumber == 0 || componentsCount < 0) {
            window.alert("Error in component position helper")
            Vector(0, 0)
        }

        val bottomRight = if(prevComp.isDefined) { prevComp.get.getBottomRight() } else { Point(0, 0) }
        val topRight = if(prevComp.isDefined) { prevComp.get.getTopRight() } else { Point(0, 0) }
        val previousComponentBottomRight = bottomRight.toVector + Vector(50, 100)


        val componentsInRowCount =
            if(componentsCount <= 4) {
                2.0
            } else {
                math.ceil(math.sqrt(componentsCount))
            }

        //lets enjoy some little math :-)
        val numberOfCurrentLine = math.ceil(componentNumber / componentsInRowCount)
        val positionInRow = componentNumber - ((numberOfCurrentLine - 1) * componentsInRowCount)

        if(positionInRow == 1) { //next row
            Vector(componentSpacing, previousComponentBottomRight.y + componentSpacing)
        } else { //continue in the current row
            Vector(previousComponentBottomRight.x + componentSpacing, topRight.y)
        }
    }
}