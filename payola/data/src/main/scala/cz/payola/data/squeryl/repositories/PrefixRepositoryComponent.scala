package cz.payola.data.squeryl.repositories

import cz.payola.data.squeryl._
import cz.payola.data.squeryl.entities.analyses._
import org.squeryl.PrimitiveTypeMode._
import cz.payola.data.squeryl.entities._

/**
 * Provides repository to access persisted prefixes
 */
trait PrefixRepositoryComponent extends TableRepositoryComponent {
    self: SquerylDataContextComponent =>

    /**
     * A repository to access persisted analyses
     */
    lazy val prefixRepository = new PrefixDefaultTableRepository

    class PrefixDefaultTableRepository
        extends OptionallyOwnedEntityDefaultTableRepository[Prefix](schema.prefixes, Prefix)
        with PrefixRepository
        with NamedEntityTableRepository[Prefix]
    {
        def getAllAvailableToUser(userId: Option[String]): Seq[Prefix] =
            selectWhere(p => (p.ownerId === None or p.ownerId === userId) and p.isPublic === true).sortBy(_.name)
    }
}
