import com.twitter.util.Future
import cats.syntax.either._

// design question asked by twitter

class DbStore {
  type K = String
  type V = String

  def multiget(keys: Seq[K]): Seq[Future[V]] = ???
}

val demandSources = Seq("A", "B", "C")
val dbStore = new DbStore

val responses = Future.collect(dbStore.multiget(demandSources))

// ====

trait Storable[K, V] {
  def get(key: K): Future[Option[V]]

  def set(key: K, value: V): Future[Unit]

  def multiget(keys: Set[K]): Future[Map[K, V]]

  def multiSet(kvs: Map[K, V]): Future[Unit]
}

class CacheStore[K, V] extends Storable[K, V] {
  override def get(key: K): Future[Option[V]] = ???

  override def set(key: K, value: V): Future[Unit] = ???

  override def multiget(keys: Set[K]): Future[Map[K, V]] = ???

  override def multiSet(kvs: Map[K, V]): Future[Unit] = ???
}

class DbStore2[K, V] extends Storable[K, V] {
  override def get(key: K): Future[Option[V]] = ???

  override def set(key: K, value: V): Future[Unit] = ???

  override def multiget(keys: Set[K]): Future[Map[K, V]] = ???

  override def multiSet(kvs: Map[K, V]): Future[Unit] = ???
}

class Store[K, V](cache: CacheStore[K, V], db: DbStore2[K, V]) extends Storable[K, V] {
  override def get(key: K): Future[Option[V]] = ???

  override def set(key: K, value: V): Future[Unit] = ???

  override def multiget(keys: Set[K]): Future[Map[K, V]] = {
    val cacheResults = keys.map { key =>
      cache.get(key).map {
        _.map(value => Map(key -> value).asRight).getOrElse(key.asLeft)
      }
    }

    Future.collect(cacheResults.toSeq).flatMap { (eithers: Seq[Either[K, Map[K, V]]]) =>
      val cacheMissKeys = eithers.flatMap(_.left.toOption)
      val cacheHitResults = eithers.flatMap(_.right.toOption).flatten.toMap

      for {
        dbResults <- db.multiget(cacheMissKeys.toSet)
        _ <- cache.multiSet(dbResults)
      } yield dbResults ++ cacheHitResults
    }
  }

  override def multiSet(kvs: Map[K, V]): Future[Unit] = ???
}

