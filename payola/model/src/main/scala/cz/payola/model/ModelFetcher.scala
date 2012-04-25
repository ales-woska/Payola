package cz.payola.model

import cz.payola.common.entities._
import analysis.PluginInstance
import cz.payola.common.entities.analyses.{Parameter, PluginInstance, Plugin}

abstract class ModelFetcher
{
    def analysesWithIDs(ids: List[String]): List[Analysis]

    def groupsWithIDs(ids: List[String]): List[Group]

    def parametersWithIDs(ids: List[String]): List[Parameter[_]]

    def pluginWithIDs(ids: List[String]): List[AnalyticalPlugin]

    def pluginInstancesWithIDs(ids: List[String]): List[AnalyticalPluginInstance]

    def usersWithIDs(ids: List[String]): List[User]
}
