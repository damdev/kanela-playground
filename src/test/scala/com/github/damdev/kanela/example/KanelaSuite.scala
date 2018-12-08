package com.github.damdev.kanela.example

import com.ea.agentloader.AgentLoader
import kanela.agent.KanelaEntryPoint

object KanelaSuite {

  def init(clazz: Class[_]): Unit = {
    val folder = clazz.getPackage.getName.replace('.', '/')
    System.setProperty("config.resource", s"$folder/application.conf")
    AgentLoader.loadAgentClass(classOf[KanelaEntryPoint].getName, null)
  }

}

trait KanelaSuite {
  KanelaSuite.init(this.getClass)
}