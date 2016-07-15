package com.twitter

import org.scalatest.FunSuite

class FetchTwitterDataTest extends FunSuite {

   /*test("test twitter followers count") {
      val totalCountOfFollower = FetchTwitterData.getTotalCountOfFollower("vhazrati")
      assert(totalCountOfFollower.get == 317)
    }*/

   test("test twitter data") {
      val getFollowers: Option[String] = FetchTwitterData.getFollowers("vhazrati")
      assert(getFollowers.get == "Success")
    }

  /*test("get none when user not found") {
    val totalCountOfFollower = FetchTwitterData.getTotalCountOfFollower("vsjakshshakjas")
    assert(totalCountOfFollower.isEmpty)
  }

  test("get none in get follower when asked for follower's count ") {
    val getFollowers: Option[String] = FetchTwitterData.getFollowers("vsjakshshakjas")
    assert(getFollowers == Some("Unable to get follower's count"))
  }*/
}
