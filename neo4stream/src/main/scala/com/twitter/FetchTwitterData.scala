package com.twitter

import java.io.{FileWriter, PrintWriter, File}
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer
import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.slf4j.LoggerFactory
import twitter4j._
import twitter4j.conf.ConfigurationBuilder
import com.typesafe.config.{Config, ConfigFactory}

object FetchTwitterData {
  val consumerKey = "mF784s3TeqgkcIFcvRz95y7yQ"
  val consumerSecret = "ckWmon7rMyqFLv021D05z3fKpycPEN040jdeDh5YJUfLR6iBfh"
  val accessToken = "2989719674-0RXR4ZYpe997y9Euubp18cRAMS3iZk8IbkPtl9M"
  val accessSecret = "L2WBKLdAAWWOAUAwWNH1kR0T22Oo772qe8AKQAfsAFMx9"

  val logger = LoggerFactory.getLogger(this.getClass)
  val config = ConfigFactory.load("references")
  val path_1 = config.getConfig("file_path")
  val path = path_1.getString("file_path")

  /**
   * This method return total number of follower's
   * @param twitterHandle provide your twitter handle
   * @return it returns total count of your follower or none if twitter handle is not valid or occurred any error
   */
  def getTotalCountOfFollower(twitterHandle: String): Option[Int] = {
    try {
      val configurationBuilder = new ConfigurationBuilder()
      configurationBuilder.setDebugEnabled(true)
        .setOAuthConsumerKey(consumerKey)
        .setOAuthConsumerSecret(consumerSecret)
        .setOAuthAccessToken(accessToken)
        .setOAuthAccessTokenSecret(accessSecret)
      val twitterFactory = new TwitterFactory(configurationBuilder.build())
      val twitter: Twitter = twitterFactory.getInstance()
      val friendsIDs = twitter.getFollowersIDs(twitterHandle, -1)
      Some(friendsIDs.getIDs.toList.size)
    } catch {
      case ex: Exception =>
        logger.error(ex.getMessage)
        None
    }
  }

  /**
   * This Method is used for fetching the follower's information of any user.
   * @param twitterHandle provide your twitter handle
   * @return it returns Option[String] with success, Unable to get follower's count or none.
   */

  def getFollowers(twitterHandle: String): Option[String] = {
    try {
      val totalNumberOfFollowers = getTotalCountOfFollower(twitterHandle)
      if (totalNumberOfFollowers.isDefined) {
        var cursor = -1
        var count = totalNumberOfFollowers.get
        val consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret)
        consumer.setTokenWithSecret(accessToken, accessSecret)
        removeDataFromFile
        while (count != 0) {
          if (count > 200) {
            val request = new HttpGet(s"https://api.twitter.com/1.1/followers/list.json?cursor=$cursor&screen_name=$twitterHandle&count=200")
            consumer.sign(request)
            val httpclient = HttpClientBuilder.create.build()
            val response = httpclient.execute(request)
            val getStatusCode: Int = response.getStatusLine.getStatusCode
            val follower_info = IOUtils.toString(response.getEntity.getContent, "UTF8")
            logger.info("Status Code is : " + getStatusCode)
            logger.info(follower_info)
            count = (totalNumberOfFollowers.get) - 200
            cursor = cursor - 1
            uploadDataIntoFile(follower_info)
          } else {
            val request = new HttpGet(s"https://api.twitter.com/1.1/followers/list.json?cursor=$cursor&screen_name=$twitterHandle&count=$count")
            consumer.sign(request)
            val httpclient = HttpClientBuilder.create.build()
            val response = httpclient.execute(request)
            val getStatusCode: Int = response.getStatusLine.getStatusCode
            val follower_info = IOUtils.toString(response.getEntity.getContent, "UTF8")
            logger.info("Status Code is : " + getStatusCode)
            logger.info(follower_info)
            count = 0
            uploadDataIntoFile(follower_info)
          }
        }
        Some("Success")
      } else {
        Some("Unable to get follower's count")
      }
    } catch {
      case ex: Exception =>
        logger.error(ex.getMessage)
        None
    }
  }

  def uploadDataIntoFile(data: String): Unit = {
    val file: File = new File(path)
    val writer = new FileWriter(file, true)
    writer.append(" " + data)
    writer.close()
  }

  def removeDataFromFile(): Unit = {
    val writer = new PrintWriter(path)
    writer.print("")
    writer.close()
  }
}
