package controllers

import javax.inject._

import play.api.mvc._
import models._

@Singleton
class TaskList @Inject() (cc: ControllerComponents)
    extends AbstractController(cc) {

  def login = Action {
    Ok(views.html.login())
  }

  def validateLoginGet(username: String, password: String) = Action {
    Ok(s"Your username $username and your password is $password")
  }

  def validateLoginPost = Action { request =>
    val postVals = request.body.asFormUrlEncoded
    postVals
      .map { args =>
        val username = args("username").head
        val password = args("password").head
        if (TaskListModel.validateUser(username, password)) {
          // add session to a log in
          Redirect(routes.TaskList.taskList).withSession("username" -> username)
        } else {
          Redirect(routes.TaskList.login)
        }

      }
      .getOrElse(Redirect(routes.TaskList.login))

  }

  def createUser = Action { request =>
    val postVals = request.body.asFormUrlEncoded
    postVals
      .map { args =>
        val username = args("username").head
        val password = args("password").head
        if (TaskListModel.createUser(username, password)) {
          // add session to a log in
          Redirect(routes.TaskList.taskList).withSession("username" -> username)
        } else {
          Redirect(routes.TaskList.login)
        }

      }
      .getOrElse(Redirect(routes.TaskList.login))

  }

  def taskList = Action { request =>
    val usernameOption = request.session.get("username")
    // if user got here without a cookie, redirect them
    usernameOption
      .map { username =>
        val tasks = TaskListModel.getTasks(username)
        Ok(views.html.tasklist(tasks))
      }
      .getOrElse(Redirect(routes.TaskList.login))

  }

  def logout = Action {
    Redirect(routes.TaskList.login).withNewSession
  }

}
