package com.github.damdev.kanela.example.advisor.enter

import com.github.damdev.kanela.Main
import com.github.damdev.kanela.example.KanelaSuite
import kanela.agent.libs.net.bytebuddy.asm.Advice._
import kanela.agent.scala.KanelaInstrumentation
import utest._

class FooMethodEnterAdvisor
object FooMethodEnterAdvisor {
  @OnMethodEnter
  def onEnter() = {
    println("[enter advisor] Enter to fooMethod")
  }
}

class TestInstrumentation extends KanelaInstrumentation {
  forTargetType("com.github.damdev.kanela.example.Foo") { builder =>
    builder.withAdvisorFor(method("fooMethod"), classOf[FooMethodEnterAdvisor]).build()
  }
}

object KanelaOnEnterInstrumentationTest extends TestSuite with KanelaSuite {
  val tests: Tests = Tests {
    * - {
      Main.main(Array.empty)
    }
  }
}
