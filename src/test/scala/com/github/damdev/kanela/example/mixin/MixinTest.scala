package com.github.damdev.kanela.example.mixin

import com.github.damdev.kanela.Main
import com.github.damdev.kanela.example.Foo
import com.github.damdev.kanela.example.suite.KanelaSuite
import kanela.agent.api.instrumentation.listener.InstrumentationRegistryListener
import kanela.agent.libs.net.bytebuddy.asm.Advice._
import kanela.agent.scala.KanelaInstrumentation
import utest._

class FooMixin extends FooMixinTrait {
  private var name: String = ""

  def setName(name: String) = this.name = name
  def getName: String = this.name
}

trait FooMixinTrait {
  def setName(name: String)
  def getName: String
}

class FooConstructorAdvisor
object FooConstructorAdvisor {
  @OnMethodExit
  def onExit(@This foo: AnyRef): Unit = {
    foo.asInstanceOf[FooMixinTrait].setName("lalalal")
    println(s"ff: ${foo.asInstanceOf[FooMixinTrait].getName}")
  }
}

class TestInstrumentation extends KanelaInstrumentation {
  forTargetType("com.github.damdev.kanela.example.Foo") { builder =>
    builder
      .withMixin(classOf[FooMixin])
      .withAdvisorFor(Constructor, classOf[FooConstructorAdvisor]).build()
  }
}

object MixinTest extends TestSuite with KanelaSuite {
  val tests: Tests = Tests {
    * - {
      val foo = Foo()
      foo.asInstanceOf[FooMixinTrait].getName ==> "lalalal"
      println(InstrumentationRegistryListener.instance().scrapeData())
    }
  }
}
