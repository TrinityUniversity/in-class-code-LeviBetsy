package controllers

import javax.inject._

import play.api.mvc._
import models._
import play.api.libs.streams.ActorFlow
import akka.actor.ActorSystem
import akka.stream.Materializer
import actors.ChatActor

@Singleton
class WebSocketChat @Inject() (cc: ControllerComponents)(implicit
    system: ActorSystem,
    mat: Materializer
) extends AbstractController(cc) {
  def index = Action { implicit request =>
    Ok(views.html.chatPage())
  }

  // the input and output types are string,  string
  def socket = WebSocket.accept[String, String] { request =>
    println("Getting socket")
    // getting socket by sending it to actor
    ActorFlow.actorRef { out => ChatActor.props() }
  }
}
