package cz.payola.data.squeryl.entities.analyses

import cz.payola.data.squeryl.entities._
import org.squeryl.annotations.Transient
import cz.payola.data.squeryl.entities.plugins.PluginInstance
import cz.payola.data.squeryl.SquerylDataContextComponent
import cz.payola.domain.entities.User
import scala.Some
import cz.payola.domain.entities.Analysis

/**
  * This objects converts [[cz.payola.common.entities.analyses.PluginInstanceBinding]]
  * to [[cz.payola.data.squeryl.entities.analyses.PluginInstanceBinding]]
  */
object PluginInstanceBinding extends EntityConverter[PluginInstanceBinding]
{
    def convert(entity: AnyRef)(implicit context: SquerylDataContextComponent): Option[PluginInstanceBinding] = {
        entity match {
            case e: PluginInstanceBinding => Some(e)
            case e: cz.payola.common.entities.analyses.PluginInstanceBinding => {
                val convertedBinding = new PluginInstanceBinding(e.id, PluginInstance(e.sourcePluginInstance),
                    PluginInstance(e.targetPluginInstance), e.targetInputIndex)
                Some(convertedBinding)
            }
            case _ => None
        }
    }
}

class PluginInstanceBinding(
    override val id: String,
    source: PluginInstance,
    target: PluginInstance,
    _targetInputIdx: Int = 0)(implicit val context: SquerylDataContextComponent)
    extends cz.payola.domain.entities.analyses.PluginInstanceBinding(source, target, _targetInputIdx)
    with PersistableEntity
{
    val sourcePluginInstanceId: String = Option(source).map(_.id).getOrElse(null)

    val targetPluginInstanceId: String = Option(target).map(_.id).getOrElse(null)

    val inputIndex = _targetInputIndex

    var analysisId: String = null

    @Transient
    private var _sourceLoaded = false
    private var _source: plugins.PluginInstance = null
    private lazy val _sourcesQuery = context.schema.bindingsOfSourcePluginInstances.right(this)

    @Transient
    private var _targetLoaded = false
    private var _target: plugins.PluginInstance = null
    private lazy val _targetsQuery = context.schema.bindingsOfTargetPluginInstances.right(this)

    override def sourcePluginInstance = {
        try{
            if (!_sourceLoaded) {
                wrapInTransaction {
                    _source = _sourcesQuery.head
                }

                _sourceLoaded = true
            }

            _source
        }
        catch {
            case e: Exception => println("source error")
            null
        }
    }

    override def targetPluginInstance = {
        try {
            if (!_targetLoaded) {
                wrapInTransaction {
                    _target = _targetsQuery.head
                }

                _targetLoaded = true
            }

            _target
        }
        catch {
            case e: Exception => println("target error")
            null
        }
    }
}
