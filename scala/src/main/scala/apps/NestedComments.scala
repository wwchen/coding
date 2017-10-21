package apps

case class Comment(id: Int, user: String, body: String) {
  def asNestedComment: NestedComment = NestedComment(id, user, body, Seq.empty)
}

case class NestedComment(id: Int, user: String, body: String, children: Seq[NestedComment]) {
  def updateChildren(child: NestedComment): NestedComment = this.copy(children = children :+ child)
}

object CommentConverter {
  implicit class CommentConversion(comment: Comment) {
    /**
      * converts flattened comments to nested comments
      * @param comments list of flattened comments
      * @param mapping  tuples of parent, child comment
      */
    def toNestedComment(comments: Seq[Comment], mapping: Seq[(Option[Int], Int)]): Seq[NestedComment] = {
      val commentMap = comments.map(c => c.id -> c.asNestedComment).toMap
      val nestedCommentMap = mapping.foldLeft(Map.empty[Int, NestedComment]) { (root, map) =>
        if (map._1.isDefined) {
          val nested = root(map._1.get).updateChildren(commentMap(map._2))
          root.updated(map._1.get, nested)
        } else {
          root.updated(map._2, commentMap(map._2))
        }
      }
      nestedCommentMap.values.toSeq
    }
  }
}
