package ru.retailrocket.toree

import org.ddahl.rscala._


package object R {
  def getConnection() = RClient("R", true)

  def getTempPlotFilename = {
    val file = java.io.File.createTempFile("iscala_plot", ".png")
    val name = file.getPath
    file.delete()
    name
  }

  def plot(R: RClient, code: String, args: Map[String, Any] = Map.empty, width: Int=512, height: Int=256) = {
    val filename = getTempPlotFilename
    R.set("plot_filename", filename)
    args.foreach{case (n,v) => R.set(n, v)}

    val plotCode = s"png(filename=plot_filename, width=${width}, height=${height}) ; ${code} ; dev.off()"
    R.eval(plotCode)

    val content = scala.io.Source.fromFile(filename, "ISO-8859-1").map{_.toByte}.toArray
    new java.io.File(filename).delete()
    com.sun.org.apache.xml.internal.security.utils.Base64.encode(content)
  }
}
