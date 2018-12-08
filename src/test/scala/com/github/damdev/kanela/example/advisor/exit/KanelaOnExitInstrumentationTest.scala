package com.github.damdev.kanela.example.advisor.exit

import com.github.damdev.kanela.Main
import com.github.damdev.kanela.example.KanelaSuite
import kanela.agent.libs.net.bytebuddy.asm.Advice._
import kanela.agent.scala.KanelaInstrumentation
import utest._

class TestMethodExitAdvisor
object TestMethodExitAdvisor {
  @OnMethodExit
  def onEnter() = {
    println("[exit advisor] Exit from fooMethod")
  }
}

class TestInstrumentation extends KanelaInstrumentation {
  forTargetType("com.github.damdev.kanela.example.Foo") { builder =>
    builder.withAdvisorFor(method("fooMethod"), classOf[TestMethodExitAdvisor]).build()
  }
}

object KanelaOnEnterInstrumentationTest extends TestSuite with KanelaSuite {
  val tests: Tests = Tests {
    * - {
      Main.main(Array.empty)
    }
  }
}
