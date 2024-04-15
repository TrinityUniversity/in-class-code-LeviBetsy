package actors

import akka.actor.Actor
import akka.actor.Props

class ChatActor extends Actor {  
    def receive: Receive = {
        case s : String =>
            println("Got message " + s)
        case m => println("Unhandled message in ChatActor: " + m)
    }
}

object ChatActor {
    //build an actor 
    def props() = Props(new ChatActor) 
}