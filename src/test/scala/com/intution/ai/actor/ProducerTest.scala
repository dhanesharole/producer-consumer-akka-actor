package com.intution.ai.actor

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import com.intution.ai.actor.Messages.NextItem
import com.intution.ai.data.Item
import org.scalatest.{BeforeAndAfterAll, FunSpec, FunSpecLike, Matchers}

class ProducerTest() extends TestKit(ActorSystem("ProducerSpec")) with ImplicitSender
  with FunSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  describe("Producer#receive default behaviour") {

    it("should produce next item only when NextItem message is received") {
      val producer = system.actorOf(Props(new Producer(testActor)), name="TestProducer")
      producer ! NextItem
      expectMsg(Item(1))

      producer ! NextItem
      expectMsg(Item(2))
    }
  }
}
