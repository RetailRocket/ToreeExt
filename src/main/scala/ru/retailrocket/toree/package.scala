package ru.retailrocket

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._


package object toree {
  def appUrl(sc: SparkContext) = sc.getConf.get("spark.org.apache.hadoop.yarn.server.webproxy.amfilter.AmIpFilter.param.PROXY_URI_BASE")
}
