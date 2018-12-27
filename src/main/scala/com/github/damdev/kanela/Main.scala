package com.github.damdev.kanela

import com.github.damdev.kanela.example.{Bar, Foo}

object Main extends App {
  private val foo = Foo()
  val v = foo.fooMethod("nameExample", new Bar("prefix ", postfix = " postfix"))
  println(s"End with ${v}")
}
