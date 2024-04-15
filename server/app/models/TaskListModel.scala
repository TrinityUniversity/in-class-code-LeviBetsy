package models

import collection.mutable

object TaskListModel {
  private val tasks =
    mutable.Map[String, List[String]]("Levi" -> List("game", "code"))

  private val users = mutable.Map[String, String]()
  users.put("Levi", "password")

  def validateUser(username: String, password: String): Boolean = {
    users.get(username).map(p => p == password).getOrElse(false)
  }

  def createUser(username: String, password: String): Boolean = {
    if (users.contains(username)) {
      false
    } else {
      users(username) = password
      true
    }
  }

  def getTasks(username: String): Seq[String] = {
    tasks.get(username).getOrElse(Nil)
  }

  def addTask(username: String, task: String): Unit = ???

  def removeTask(username: String, index: Int): Boolean = ???
}
