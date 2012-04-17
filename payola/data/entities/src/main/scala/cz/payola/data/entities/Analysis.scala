package cz.payola.data.entities

import org.squeryl.KeyedEntity
import schema.PayolaDB
import collection.mutable.ArrayBuffer
import org.squeryl.PrimitiveTypeMode._

class Analysis(
        id: String,
        name: String,
        owner: User)
    extends cz.payola.domain.entities.Analysis(id, name, owner)
    with PersistableEntity
{
    val ownerId: String = if (owner == null) "" else owner.id

    private lazy val _pluginInstancesQuery =  PayolaDB.analysesPluginInstances.left(this)

    override def pluginInstances : collection.Seq[PluginInstanceType] = {
        evaluateCollection(_pluginInstancesQuery)
    }
}
