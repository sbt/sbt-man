package sbtman

import sbt._
import Keys._
import org.json4s._

object Plugin extends sbt.Plugin {
  override lazy val settings = Seq(commands += manCommand)

  def manCommand = Command.args("man", "<query>") { (state, args) => 
    lookupScalex(args.mkString(" "))
    state
  }

  private[this] def lookupScalex(q: String): Unit = {
    import dispatch._, Defaults._
    val http = new Http // with NoLogging {}
    val u = url("http://api.scalex.org/") <<? Map("q" -> q, "per_page" -> "1")
    val res = http(u OK as.json4s.Json)
    processResponse(res())
  }

  private[this] def processResponse(json: JValue): Unit = {
    implicit val formats = DefaultFormats
    val results: List[JValue] = for { 
      JArray(array) <- json \ "results"
      jvalue <- array
    } yield jvalue

    results map { jvalue =>
      val result = jvalue.extract[Result]
      println("[man] %s" format result.parent.qualifiedName)
      println("[man] def %s%s%s: %s" format (result.name,
        if (result.typeParams == "") "" else result.typeParams,
        result.valueParams,
        result.resultType))
      jvalue \ "comment" match {
        case json: JObject =>
          json.extract[Comment] match {
            case Comment(_, Some(body)) => println("[man] %s" format body.txt)
            case _ =>
          }
        case _ =>
      }
      jvalue
    }
  }
}

case class Parent(name: String, qualifiedName: String, typeParams: String)
case class Comment(short: Option[Text], body: Option[Text])
case class Text(txt: String, html: String)
case class Result(name: String, qualifiedName: String, parent: Parent, typeParams: String, 
                  valueParams: String, resultType: String, comment: Option[Comment])
