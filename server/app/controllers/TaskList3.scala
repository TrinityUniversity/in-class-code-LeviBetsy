package controllers

import javax.inject._

import play.api.mvc._
import models._
import play.api.libs.json.Json

@Singleton
class TaskList3 @Inject() (cc: ControllerComponents)
    extends AbstractController(cc) {
  def load = TODO
  def data = Action {
    Ok(Json.toJson(Seq("a", "b")))
  }
}
