package com.github.damdev.kanela.example

class Bar(val prefix: String, val postfix: String) {
  def process(name: String): String = this.prefix + name + this.postfix

}
