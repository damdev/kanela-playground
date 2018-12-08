package com.github.damdev.kanela

import com.github.damdev.kanela.example.{Bar, Foo}

object Main extends App {
  val v = Foo().fooMethod("nameExample", new Bar("prefix ", postfix = " postfix"))
  println(s"End with ${v}")
}
