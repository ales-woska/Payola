package cz.payola.web.client.views.entity.analysis

import cz.payola.web.client.presenters.components.AnalysisControls
import cz.payola.web.client.views.ComposedView
import cz.payola.common.entities.Analysis

class AnalysisOverviewView(analysis: Analysis) extends ComposedView
{
    val controls = new AnalysisControls
    val analysisVisualizer = new AnalysisVisualizer(analysis)

    def createSubViews = List(controls, analysisVisualizer)
}
