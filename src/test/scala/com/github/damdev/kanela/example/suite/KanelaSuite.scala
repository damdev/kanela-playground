package com.github.damdev.kanela.example.suite

import kanela.agent.attacher.Attacher

object KanelaSuite {

  def init(clazz: Class[_]): Unit = {
    val folder = clazz.getPackage.getName.replace('.', '/')
    System.setProperty("config.resource", s"$folder/application.conf")
    Attacher.attach()
  }

}

trait KanelaSuite {
  KanelaSuite.init(this.getClass)
}