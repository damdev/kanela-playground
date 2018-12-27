package com.github.damdev.kanela.example.interceptor

import java.util.concurrent.Callable

import com.github.damdev.kanela.Main
import com.github.damdev.kanela.example.{Bar, Foo}
import com.github.damdev.kanela.example.suite.KanelaSuite
import kanela.agent.api.instrumentation.listener.InstrumentationRegistryListener
import utest.{*, TestSuite, Tests}
import kanela.agent.libs.net.bytebuddy.implementation.bind.annotation.{RuntimeType, SuperCall, This}
import kanela.agent.scala.KanelaInstrumentation

object FooMethodInterceptor {
  @RuntimeType
  def aroundMethod(@SuperCall callable: Callable[Any], @This envelope: Object): String = "lalala"
}

class TestInstrumentation extends KanelaInstrumentation {
  forTargetType("com.github.damdev.kanela.example.Foo") { builder =>
    builder.withInterceptorFor(method("fooMethod"), FooMethodInterceptor).build()
  }
}

object InterceptorTest extends TestSuite with KanelaSuite {
  val tests: Tests = Tests {
    * - {
      Main.main(Array.empty)
      println(InstrumentationRegistryListener.instance().scrapeData())
      val foo = Foo()
      assert(
        foo.fooMethod("asd", new Bar("prefix", "postfix")) == "lalala"
      )
    }
  }
}
