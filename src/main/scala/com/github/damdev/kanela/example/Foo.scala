package com.github.damdev.kanela.example

class Foo {

  def fooMethod(name: String, bar: Bar): String = {
    bar.process(name)
  }

}

object Foo {
  def apply(): Foo = new Foo()
}